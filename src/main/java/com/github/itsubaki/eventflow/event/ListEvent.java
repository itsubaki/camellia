package com.github.itsubaki.eventflow.event;

import java.util.ArrayList;
import java.util.List;

public class ListEvent extends Event {
	private List<Object> record = new ArrayList<>();

	public ListEvent(String name) {
		super(name);
	}

	public void add(Object obj) {
		record.add(obj);
	}

	public List<Object> get() {
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
