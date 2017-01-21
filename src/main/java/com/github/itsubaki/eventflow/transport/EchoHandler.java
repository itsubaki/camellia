package com.github.itsubaki.eventflow.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoHandler implements HandlerIF {
	private static final Logger LOG = LoggerFactory.getLogger(EchoHandler.class);
	private byte[] buf = new byte[128];

	@Override
	public int read(InputStream in) throws IOException {

		int len = in.read(buf);

		if (LOG.isTraceEnabled()) {
			LOG.trace(Arrays.toString(buf));
		}

		return len;
	}

	@Override
	public boolean write(OutputStream out) throws IOException {
		out.write(buf);

		if (LOG.isTraceEnabled()) {
			LOG.trace(Arrays.toString(buf));
		}

		return false;
	}
}
