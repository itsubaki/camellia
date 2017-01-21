package com.github.itsubaki.eventflow.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface HandlerIF {

	void handle(InputStream in, OutputStream out) throws IOException;

	void close();

	boolean isClosed();

}
