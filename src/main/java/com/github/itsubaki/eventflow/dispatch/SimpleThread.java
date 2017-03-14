package com.github.itsubaki.eventflow.dispatch;

public class SimpleThread extends Thread {
	private SimplePool pool;

	public SimpleThread(SimplePool pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		while (!pool.isClosed()) {
			Runnable task = pool.tasks.poll();
			if (task == null) {
				continue;
			}

			task.run();
		}
	}

}
