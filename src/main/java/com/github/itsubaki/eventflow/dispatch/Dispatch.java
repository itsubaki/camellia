package com.github.itsubaki.eventflow.dispatch;

public class Dispatch {
	private static final DispatchQueue QUEUE = new DispatchQueue();

	public static DispatchQueue getQueue() {
		return QUEUE;
	}

}
