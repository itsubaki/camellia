package com.github.itsubaki.eventflow.dispatch;

public class Dispatch {
	private static final DispatchQueue QUEUE = new DispatchQueue(128);

	public static DispatchQueue getQueue() {
		return QUEUE;
	}

}
