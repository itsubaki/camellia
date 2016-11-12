package com.github.itsubaki.eventflow;

import org.junit.Test;

import com.github.itsubaki.eventflow.flow.FlowIF;
import com.github.itsubaki.eventflow.flow.SampleFlow;
import com.github.itsubaki.eventflow.router.RouterIF;
import com.github.itsubaki.eventflow.router.RouterRegexp;

public class EventflowTest {

	@Test
	public void test() {
		RouterIF<FlowIF> router = new RouterRegexp<>();

		EventflowIF flow = new Eventflow();
		flow.setRouter(router);
		flow.add(new SampleFlow());

	}
}
