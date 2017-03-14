package com.github.itsubaki.eventflow.dispatch;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimplePool {
	protected final Queue<Runnable> tasks = new ConcurrentLinkedQueue<>();
	private SimpleThread[] thread;
	private AtomicBoolean shutdown = new AtomicBoolean(false);

	public SimplePool(int size) {
		thread = new SimpleThread[size];
	}

	public void execute(Runnable task) {
		tasks.offer(task);
	}

	public void start() {
		for (int i = 0; i < thread.length; i++) {
			thread[i] = new SimpleThread();
			thread[i].start();
		}
	}

	public void shutdown() {
		shutdown.set(true);
	}

	public class SimpleThread extends Thread {

		@Override
		public void run() {
			while (!shutdown.get()) {
				Runnable task = tasks.poll();
				if (task == null) {
					continue;
				}

				task.run();
			}
		}

	}

}
