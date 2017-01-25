package com.github.itsubaki.eventflow.pool;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ObjectPoolTest {

	@Test
	public void test() {
		int size = 10;
		ObjectPool<String> pool = new SampleObjectPool(size);
		try {
			for (int i = 0; i < size; i++) {
				PooledObject<String> pooled = pool.get();
				assertEquals("PooledString(0)", pooled.get());
				pooled.close();
			}
			assertEquals(1, pool.getIdleSize());
			assertEquals(1, pool.getCreatedSize());
			assertEquals(0, pool.getBusySize());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.close();
		}
	}

	@Test
	public void test2() {
		int size = 10;
		ObjectPool<String> pool = new SampleObjectPool(size);
		try {
			for (int i = 0; i < size; i++) {
				PooledObject<String> pooled = pool.get();
				assertEquals("PooledString(" + i + ")", pooled.get());
			}
			assertEquals(0, pool.getIdleSize());
			assertEquals(size, pool.getCreatedSize());
			assertEquals(size, pool.getBusySize());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.close();
		}
	}
}
