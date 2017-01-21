package com.github.itsubaki.eventflow.transport;

import java.io.InputStream;
import java.io.OutputStream;
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
	private HandlerIF handler = new EchoHandler();

	public Transport(Socket socket, Connection connection) {
		super.setName(socket.getRemoteSocketAddress().toString());

		this.socket = socket;
		this.connection = connection;
		this.pause.set(new CountDownLatch(0));

	}

	public void setHandler(HandlerIF handler) {
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
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			while (!handler.isClosed()) {
				await();
				handler.handle(in, out);
			}
		} catch (Exception e) {
			LOG.debug("read/write failed, reason: " + e.getMessage());
		} finally {
			connection.close();
		}
	}

}
