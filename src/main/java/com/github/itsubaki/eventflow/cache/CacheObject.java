package com.github.itsubaki.eventflow.cache;

import java.util.concurrent.atomic.AtomicLong;

public class CacheObject<V> {
	private V object;
	private long lastUsed = System.currentTimeMillis();
	private AtomicLong hit = new AtomicLong(0);

	public CacheObject(V object) {
		this.object = object;
	}

	public V getObject() {
		hit();
		return object;
	}

	public void setObject(V object) {
		this.object = object;
	}

	public long getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(long lastUsed) {
		this.lastUsed = lastUsed;
	}

	public void hit() {
		hit.incrementAndGet();
		lastUsed = System.currentTimeMillis();
	}

	public long getHitCount() {
		return hit.get();
	}

}
