package com.github.itsubaki.eventflow.cache.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.itsubaki.eventflow.router.RouterIF;
import com.github.itsubaki.eventflow.router.RouterRegexp;

public class RouterRegexpTest {

	@Test
	public void find() {
		RouterIF<SampleIF> m = new RouterRegexp<>();

		SampleFunctional sf = new SampleFunctional();
		SampleObjectOriented soo = new SampleObjectOriented();

		m.put("haskell|scala", sf);
		m.put("java|scala", soo);

		assertEquals(sf, m.findOne("haskell").get());
		assertEquals(soo, m.findOne("java").get());
	}

	@Test
	public void findAll() {
		RouterIF<SampleIF> m = new RouterRegexp<>();

		SampleFunctional sf = new SampleFunctional();
		SampleObjectOriented soo = new SampleObjectOriented();

		m.put("haskell|scala", sf);
		m.put("java|scala", soo);

		List<SampleIF> result = m.findAll("scala");
		assertEquals(2, result.size());
		assertTrue(result.contains(sf));
		assertTrue(result.contains(soo));
	}
}
