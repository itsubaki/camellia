package com.github.itsubaki.eventflow.transport;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.itsubaki.eventflow.util.Util;

public class Acceptor implements Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(Acceptor.class);

	private AtomicReference<CountDownLatch> pause = new AtomicReference<>();
	private List<Connection> connections = new LinkedList<>();
	private int maxConnectionSize = 128;
	private ServerSocket serverSocket;
	private HandlerIF handler;

	public Acceptor(ServerSocket socket, HandlerIF handler) {
		this.serverSocket = socket;
		this.handler = handler;
		this.pause.set(new CountDownLatch(0));

		try {
			socket.setSoTimeout(1000);
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public List<Connection> getConnection() {
		return connections;
	}

	public void pause() {
		pause.set(new CountDownLatch(1));
	}

	public void resume() {
		pause.get().countDown();
	}

	public void await() throws InterruptedException {
		pause.get().await();
	}

	public void close() {
		Util.close(serverSocket);
		resume();
	}

	public void accept() throws Exception {
		try {
			Socket socket = serverSocket.accept();
			LOG.info("accepted. socket: " + socket);
			synchronized (connections) {
				Connection con = new Connection(socket);
				con.setConnections(connections);
				con.setHandler(handler);
				con.open();

				if (connections.size() >= maxConnectionSize) {
					con.close();
					return;
				}

				connections.add(con);
			}
		} catch (SocketTimeoutException e) {
			// noop
		}
	}

	@Override
	public void run() {
		try {
			while (!serverSocket.isClosed()) {
				await();
				accept();
			}
		} catch (Exception e) {
			LOG.debug("finihesd. reason: " + e.getMessage());
		}
	}

}
