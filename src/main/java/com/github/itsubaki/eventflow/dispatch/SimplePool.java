package com.github.itsubaki.eventflow.dispatch;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimplePool {
	protected final ConcurrentLinkedQueue<Runnable> tasks = new ConcurrentLinkedQueue<>();
	private SimpleThread[] thread;
	private AtomicBoolean close = new AtomicBoolean(false);

	public SimplePool(int size) {
		thread = new SimpleThread[size];
		for (int i = 0; i < size; i++) {
			thread[i] = new SimpleThread(this);
			thread[i].start();
		}
	}

	public void execute(Runnable task) {
		tasks.offer(task);
	}

	public void close() {
		close.set(true);
	}

	public boolean isClosed() {
		return close.get();
	}

}
