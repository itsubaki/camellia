package com.github.itsubaki.eventflow.dispatch;

public class DispatchQueue {
	private SimplePool pool;

	public DispatchQueue(int size) {
		pool = new SimplePool(size);
	}

	public void execute(Runnable task) {
		pool.execute(task);
	}

}
