package com.github.itsubaki.camellia.pool;

import java.util.concurrent.atomic.AtomicLong;

import com.github.itsubaki.camellia.pool.ObjectPool;

public class SampleObjectPool extends ObjectPool<String> {

	public SampleObjectPool(int maxSize) {
		super(maxSize);
	}

	private AtomicLong counter = new AtomicLong(0);

	@Override
	public String newObject() throws Exception {
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
