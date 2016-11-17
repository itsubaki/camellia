package com.github.itsubaki.eventflow;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.itsubaki.eventflow.event.MapEvent;
import com.github.itsubaki.eventflow.node.NodeIF;
import com.github.itsubaki.eventflow.node.SampleNode;
import com.github.itsubaki.eventflow.router.RouterRegexp;

public class EventflowTest {

	@Test
	public void test() {
		NodeIF node1 = new SampleNode();
		node1.setName("node1");
		node1.setRegexp("sample");

		NodeIF node2 = new SampleNode();
		node2.setName("node2");
		node2.setRegexp("example");

		EventflowIF flow = new Eventflow();
		flow.setRouter(new RouterRegexp<>());
		flow.add(node1);
		flow.add(node2);

		assertTrue(node1.transfer(new MapEvent("sample")).isPresent());
		assertTrue(node1.transfer(new MapEvent("example")).isPresent());
		assertFalse(node1.transfer(new MapEvent("foobar")).isPresent());
		System.out.println(node1.transfer(new MapEvent("example")).get());
	}
}
