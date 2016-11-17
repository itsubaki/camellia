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
				String str = pool.get();
				assertEquals("PooledString(0)", str);
				pool.close(str);
			}
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
				String str = pool.get();
				assertEquals("PooledString(" + i + ")", str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.close();
		}
	}
}
