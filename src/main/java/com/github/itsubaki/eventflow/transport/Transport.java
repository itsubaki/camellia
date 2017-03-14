package com.github.itsubaki.eventflow.transport;

import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transport extends Thread {
	private static final Logger LOG = LoggerFactory.getLogger(Transport.class);

	private AtomicReference<CountDownLatch> pause = new AtomicReference<>();
	private Socket socket;
	private Connection connection;
	private SocketHandlerIF handler = new SocketHandlerEcho();

	public Transport(Socket socket, Connection connection) {
		super.setName(socket.getRemoteSocketAddress().toString());

		this.socket = socket;
		this.connection = connection;
		this.pause.set(new CountDownLatch(0));

	}

	public void setHandler(SocketHandlerIF handler) {
		this.handler = handler;
	}

	public void pause() {
		pause.set(new CountDownLatch(1));
	}

	public void resume2() {
		pause.get().countDown();
	}

	public void await() throws InterruptedException {
		pause.get().await();
	}

	@Override
	public void run() {
		try {
			while (!handler.isClosed()) {
				await();
				handler.handle(socket);
			}
		} catch (Exception e) {
			LOG.debug("read/write failed, reason: " + e.getMessage());
		} finally {
			connection.close();
		}
	}

}
