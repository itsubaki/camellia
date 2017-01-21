package com.github.itsubaki.eventflow.event;

import java.util.UUID;

public class Event implements EventIF {
	private String id = UUID.randomUUID().toString();
	private long timestamp = System.currentTimeMillis();
	private String name;

	public Event(String name) {
		this.name = name;
	}

	@Override
	public String getEventId() {
		return id;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String getName() {
		return name;
	}
}
