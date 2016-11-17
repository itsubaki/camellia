package com.github.itsubaki.eventflow;

import org.junit.Test;

import com.github.itsubaki.eventflow.node.NodeIF;
import com.github.itsubaki.eventflow.node.SampleNode;
import com.github.itsubaki.eventflow.router.RouterIF;
import com.github.itsubaki.eventflow.router.RouterRegexp;

public class EventflowTest {

	@Test
	public void test() {
		RouterIF<NodeIF> router = new RouterRegexp<>();

		EventflowIF flow = new Eventflow();
		flow.setRouter(router);

		NodeIF node = new SampleNode();
		node.setName("name");
		node.setRegexp("regexp");

		flow.add(node);
	}
}
