package com.github.itsubaki.eventflow.pool;

import java.util.concurrent.atomic.AtomicLong;

public class SampleObjectPool extends ObjectPool<String> {

	public SampleObjectPool(int maxSize) {
		super(maxSize);
	}

	private AtomicLong counter = new AtomicLong(0);

	@Override
	public String generate() throws Exception {
		return "PooledString(" + counter.getAndIncrement() + ")";
	}

	@Override
	public void destroy(String obj) {
		obj = "";
	}

	@Override
	boolean isValid(String obj) {
		return true;
	}

}
