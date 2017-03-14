package com.github.itsubaki.eventflow.dispatch;

public class Dispatch {
	private static final DispatchQueue QUEUE;

	static {
		QUEUE = new DispatchQueue(128);
		QUEUE.start();
	}

	public static DispatchQueue getQueue() {
		return QUEUE;
	}

}
