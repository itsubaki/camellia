package com.github.itsubaki.camellia.socket;

import java.io.IOException;
import java.net.Socket;

public interface SocketHandlerIF {

	void handle(Socket socket) throws IOException;

	void close();

	boolean isClosed();

}
