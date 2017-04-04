package com.github.itsubaki.camellia.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class RouterRegexpTest {

	@Test
	public void find() {
		RouterIF<String> router = new RouterRegexp<>();

		router.put("haskell|scala", "foobar");
		router.put("java|scala", "hoge");

		assertEquals("foobar", router.findOne("haskell").get());
		assertEquals("hoge", router.findOne("java").get());
	}

	@Test
	public void findAll() {
		RouterIF<String> router = new RouterRegexp<>();

		router.put("haskell|scala", "foobar");
		router.put("java|scala", "hoge");

		List<String> result = router.findAll("scala");
		assertEquals(2, result.size());
		assertTrue(result.contains("foobar"));
		assertTrue(result.contains("hoge"));
	}
}
