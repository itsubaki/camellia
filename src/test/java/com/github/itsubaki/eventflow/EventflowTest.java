package com.github.itsubaki.eventflow;

import org.junit.Test;

import com.github.itsubaki.eventflow.cache.gate.SampleGate;
import com.github.itsubaki.eventflow.gate.GateIF;
import com.github.itsubaki.eventflow.router.RouterIF;
import com.github.itsubaki.eventflow.router.RouterRegexp;

public class EventflowTest {

	@Test
	public void test() {
		RouterIF<GateIF> router = new RouterRegexp<>();

		EventflowIF flow = new Eventflow();
		flow.setRouter(router);
		flow.add(new SampleGate());

	}
}
