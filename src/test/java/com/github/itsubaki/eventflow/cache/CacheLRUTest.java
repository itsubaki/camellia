package com.github.itsubaki.eventflow.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CacheLRUTest {

	@Test
	public void test() {
		CacheLRU<String, String> cache = new CacheLRU<>();
		assertEquals(0, cache.getCurrentCacheSize());

		String val = cache.get("foobar");
		assertNull(val);

		cache.put("foobar", "hoge");
		assertEquals(1, cache.getCurrentCacheSize());
		String hit = cache.get("foobar");
		assertNotNull(hit);
		assertEquals("hoge", hit);

	}

	@Test
	public void max() throws InterruptedException {
		CacheLRU<String, String> cache = new CacheLRU<>(1);
		cache.put("foobar0", "hoge");
		cache.put("foobar1", "hoge");

		assertEquals(1, cache.getCurrentCacheSize());
		assertNull(cache.get("foobar0"));
		assertEquals(cache.get("foobar1"), "hoge");
	}

	@Test
	public void rate() {
		CacheLRU<String, String> cache = new CacheLRU<>(3);
		cache.put("foobar", "hoge");
		cache.get("foobar");
		cache.get("hoge");
		cache.get("piyo");
		assertEquals(0.333, cache.getHitRate(), 0.001);
	}

}
