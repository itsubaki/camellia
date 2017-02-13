package com.github.itsubaki.eventflow.event;

import java.util.UUID;

public abstract class Event implements EventIF {
	private String id = UUID.randomUUID().toString();
	private long timestamp = System.currentTimeMillis();
	private String name;

	public Event(String name) {
		this.name = name;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public long timestamp() {
		return timestamp;
	}

	@Override
	public String name() {
		return name;
	}
}
