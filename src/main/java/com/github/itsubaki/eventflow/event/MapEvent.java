package com.github.itsubaki.eventflow.event;

import java.util.HashMap;
import java.util.Map;

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

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(this.getClass().getSimpleName() + "(");
		buf.append("id=" + id() + ", ");
		buf.append("timestamp=" + timestamp() + ", ");
		buf.append("name=" + name() + ", ");
		buf.append("record=" + get());
		buf.append(")");
		return buf.toString();
	}

}
