package com.github.itsubaki.eventflow.event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.ToString;

@ToString
public class MapEvent implements EventIF {
	private String id = UUID.randomUUID().toString();
	private long timestamp = System.currentTimeMillis();

	private String name;
	private Map<String, Object> record = new HashMap<>();

	public MapEvent(String name) {
		this.name = name;
	}

	public String getEventId() {
		return id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String getName() {
		return name;
	}

	public void put(String key, Object value) {
		record.put(key, value);
	}

	public Object get(String key) {
		return record.get(key);
	}

	public Map<String, Object> get() {
		return record;
	}

}
