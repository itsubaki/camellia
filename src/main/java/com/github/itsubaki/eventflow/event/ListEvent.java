package com.github.itsubaki.eventflow.event;

import java.util.ArrayList;
import java.util.List;

public class ListEvent extends Event {
	private List<Object> list = new ArrayList<>();

	public ListEvent(String name) {
		super(name);
	}

	public void add(Object obj) {
		list.add(obj);
	}

	public List<Object> get() {
		return list;
	}

}
