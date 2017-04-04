package com.github.itsubaki.camellia.pool;

public class PooledObject<T> {
	private T object;
	private ObjectPool<T> pool;

	public PooledObject(T object, ObjectPool<T> pool) {
		this.object = object;
		this.pool = pool;
	}

	public T get() {
		return object;
	}

	public void close() {
		pool.close(object);
	}
}
