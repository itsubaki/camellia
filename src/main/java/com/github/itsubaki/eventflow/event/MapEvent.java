package com.github.itsubaki.eventflow.event;

import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

@ToString
public class MapEvent extends Event {
	private Map<String, Object> record = new HashMap<>();

	public MapEvent(String name) {
		super(name);
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
