package com.github.itsubaki.eventflow.transport;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
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
		super.setName(this.getClass().getSimpleName() + ": " + socket.getPort());

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

	@Override
	public void run() {
		byte[] buf = new byte[128];
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			while (true) {
				int len = in.read(buf);
				if (len == -1) {
					break;
				}

				if (LOG.isTraceEnabled()) {
					LOG.trace("recv: " + Arrays.toString(buf));
				}

				byte[] bytes = handler.handle(buf);

				pause.get().await();
				out.write(bytes, 0, len);

				if (LOG.isTraceEnabled()) {
					LOG.trace("writ: " + Arrays.toString(buf));
				}
			}
		} catch (Exception e) {
			LOG.debug("read/write failed, reason: " + e.getMessage());
		} finally {
			connection.close();
		}
	}

}
