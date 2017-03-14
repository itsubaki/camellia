package com.github.itsubaki.eventflow.transport;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketEngine {
	private static final Logger LOG = LoggerFactory.getLogger(SocketEngine.class);
	private int port;
	private int maxConnectionSize;
	private SocketHandlerIF handler;
	private Acceptor acceptor;
	private ServerSocket serverSocket;

	public SocketEngine(int port) {
		this(port, new SocketHandlerEcho());
	}

	public SocketEngine(int port, SocketHandlerIF handler) {
		this(port, handler, 128);
	}

	public SocketEngine(int port, SocketHandlerIF handler, int maxConnectionSize) {
		this.port = port;
		this.handler = handler;
		this.maxConnectionSize = maxConnectionSize;
	}

	public void open() throws Exception {
		serverSocket = new ServerSocket(port);

		acceptor = new Acceptor(serverSocket);
		acceptor.setHandler(handler);
		acceptor.setConnectionSize(maxConnectionSize);

		new Thread(null, acceptor, this.getClass().getSimpleName() + ": " + serverSocket.getLocalPort()).start();

		LOG.info("opend. port: " + serverSocket.getLocalPort());
	}

	public void close() {
		List<Connection> connections;
		synchronized (acceptor.getConnection()) {
			connections = new ArrayList<>(acceptor.getConnection());
		}
		connections.forEach(c -> c.close());
		acceptor.close();

		LOG.info("closed. connection.size: " + connections.size());
	}

	public void pause() {
		List<Connection> connections = acceptor.getConnection();
		synchronized (connections) {
			acceptor.pause();
			connections.forEach(c -> c.pause());
		}

		LOG.info("paused. connections.size: " + connections.size());
	}

	public void resume() {
		List<Connection> connections = acceptor.getConnection();
		synchronized (connections) {
			connections.forEach(c -> c.resume());
		}
		acceptor.resume();

		LOG.info("resumed. connections.size: " + connections.size());
	}

}
