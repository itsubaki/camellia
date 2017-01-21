package com.github.itsubaki.eventflow.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface HandlerIF {

	int read(InputStream in) throws IOException;

	boolean write(OutputStream out) throws IOException;
}
