package com.github.itsubaki.eventflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.github.itsubaki.eventflow.event.MapEvent;
import com.github.itsubaki.eventflow.node.NodeIF;
import com.github.itsubaki.eventflow.node.SampleNode;
import com.github.itsubaki.eventflow.router.RouterRegexp;

public class EventflowTest {

	@Test
	public void test() {
		NodeIF node1 = new SampleNode();
		node1.setName("node-java");
		node1.setRoute("java");

		NodeIF node2 = new SampleNode();
		node2.setName("node-all");
		node2.setRoute("java|scala");

		EventflowIF flow = new Eventflow();
		flow.setRouter(new RouterRegexp());
		flow.add(node1);
		flow.add(node2);

		echo(node1.transferAll(new MapEvent("java")));
		echo(node2.transferAll(new MapEvent("scala")));

		assertEquals(2, node1.transferAll(new MapEvent("java")).size());
		assertEquals(1, node2.transferAll(new MapEvent("scala")).size());
		assertFalse(node1.transfer(new MapEvent("foobar")).isPresent());
		assertFalse(node2.transfer(new MapEvent("foobar")).isPresent());
	}

	public void echo(Object obj) {
		System.out.println(obj.toString());
	}
}
