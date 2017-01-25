package com.github.itsubaki.eventflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.event.MapEvent;
import com.github.itsubaki.eventflow.node.NodeIF;
import com.github.itsubaki.eventflow.node.SampleNode;
import com.github.itsubaki.eventflow.router.RouterRegexp;

public class EventflowTest {

	@Test
	public void test() {
		EventflowIF flow = new Eventflow();
		flow.setRouter(new RouterRegexp());

		NodeIF node1 = new SampleNode("node1", "java") {
			@Override
			public Optional<String> onEvent(EventIF event) {
				System.out.println("[" + getName() + "] recieved: " + event.toString());
				return Optional.of("success");
			}
		};

		NodeIF node2 = new SampleNode("node2", "java|scala") {
			@Override
			public Optional<String> onEvent(EventIF event) {
				System.out.println("[" + getName() + "] recieved: " + event.toString());
				return Optional.of("success");
			}
		};

		flow.add(node1);
		flow.add(node2);

		echo(node1.emitAll(new MapEvent("java")));
		echo(node2.emitAll(new MapEvent("scala")));
		echo("-");

		assertEquals(2, node1.emitAll(new MapEvent("java")).size());
		assertEquals(1, node2.emitAll(new MapEvent("scala")).size());
		assertFalse(node1.emit(new MapEvent("foobar")).isPresent());
		assertFalse(node2.emit(new MapEvent("foobar")).isPresent());

		flow.shutdown();
	}

	public void echo(Object obj) {
		System.out.println(obj.toString());
	}
}