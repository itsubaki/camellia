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
		CacheLRU<String, String> cache = new CacheLRU<>(3);
		cache.put("foobar0", "hoge");
		Thread.sleep(10);
		cache.put("foobar1", "hoge");
		Thread.sleep(10);
		cache.put("foobar2", "hoge");
		Thread.sleep(10);
		cache.put("foobar3", "hoge");
		Thread.sleep(10);
		cache.put("foobar4", "hoge");

		assertEquals(3, cache.getCurrentCacheSize());
		assertNull(cache.get("foobar0"));
		assertNull(cache.get("foobar1"));
		assertEquals(cache.get("foobar2"), "hoge");
		assertEquals(cache.get("foobar3"), "hoge");
		assertEquals(cache.get("foobar4"), "hoge");
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
