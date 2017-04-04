package com.github.itsubaki.camellia.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketHandlerEcho implements SocketHandlerIF {
	private static final Logger LOG = LoggerFactory.getLogger(SocketHandlerEcho.class);
	private boolean closed = false;

	@Override
	public void handle(Socket socket) throws IOException {
		byte[] buf = new byte[128];

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		int len = -1;
		if ((len = in.read(buf)) == -1) {
			close();
			return;
		}

		ByteBuffer bb = ByteBuffer.allocate(len).put(buf, 0, len);
		byte[] bytes = bb.array();
		if (LOG.isTraceEnabled()) {
			LOG.trace(Arrays.toString(bytes));
		}

		out.write(bytes, 0, len);

		if (LOG.isTraceEnabled()) {
			LOG.trace(Arrays.toString(bytes));
		}

	}

	@Override
	public void close() {
		closed = true;
	}

	@Override
	public boolean isClosed() {
		return closed;
	}

}
