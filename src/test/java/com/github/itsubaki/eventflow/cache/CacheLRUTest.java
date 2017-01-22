package com.github.itsubaki.eventflow.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class CacheLRUTest {

	@Test
	public void test() {
		CacheLRU<String, String> cache = new CacheLRU<>();
		assertEquals(0, cache.getCurrentCacheSize());

		Optional<String> val = cache.get("foobar");
		assertFalse(val.isPresent());

		cache.put("foobar", "hoge");
		assertEquals(1, cache.getCurrentCacheSize());
		Optional<String> hit = cache.get("foobar");
		assertTrue(hit.isPresent());
		assertEquals("hoge", hit.get());

	}

	@Test
	public void max() throws InterruptedException {
		CacheLRU<String, String> cache = new CacheLRU<>(1);
		cache.put("foobar0", "hoge");
		cache.put("foobar1", "hoge");

		assertEquals(1, cache.getCurrentCacheSize());
		assertFalse(cache.get("foobar0").isPresent());
		assertEquals(cache.get("foobar1").get(), "hoge");
	}

	@Test
	public void rate() {
		CacheLRU<String, String> cache = new CacheLRU<>(3);
		cache.put("foobar", "hoge");
		cache.get("foobar");
		System.out.println(cache.get("hoge"));
		cache.get("piyo");
		assertEquals(0.333, cache.getHitRate(), 0.001);
	}

}
