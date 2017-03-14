package com.github.itsubaki.eventflow.transport;

import java.io.IOException;
import java.net.Socket;

public interface SocketHandlerIF {

	void handle(Socket socket) throws IOException;

	void close();

	boolean isClosed();

}
