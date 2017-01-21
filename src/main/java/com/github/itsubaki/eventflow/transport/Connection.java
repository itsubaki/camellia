package com.github.itsubaki.eventflow.transport;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connection {
	private static final Logger LOG = LoggerFactory.getLogger(Connection.class);
	private List<Connection> connections = new LinkedList<>();
	private Socket socket;
	private Transport transport;

	public Connection(Socket socket) throws Exception {
		this.socket = socket;
		this.transport = new Transport(socket, this);
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	public void setHandler(HandlerIF handler) {
		transport.setHandler(handler);
	}

	public void pause() {
		transport.pause();
	}

	public void resume() {
		transport.resume2();
	}

	public void open() {
		transport.start();
		LOG.debug(transport.getName());
	}

	public void close() {
		synchronized (connections) {
			connections.remove(this);
		}

		try {
			socket.close();
		} catch (Exception e) {
			LOG.debug("unexpected error. connection: " + this, e);
		}

		LOG.debug(transport.getName());
	}

}
