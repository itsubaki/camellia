package com.github.itsubaki.eventflow.dispatch;

import java.util.concurrent.ConcurrentLinkedQueue;

public class DispatchQueue {
	private final ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

	public void execute(Runnable task) {
		queue.add(task);
	}

}
