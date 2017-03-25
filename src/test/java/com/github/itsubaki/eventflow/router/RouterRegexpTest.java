package com.github.itsubaki.eventflow.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.node.NodeIF;
import com.github.itsubaki.eventflow.node.SampleNode;

public class RouterRegexpTest {

	@Test
	public void find() {
		RouterIF<NodeIF> router = new RouterRegexp<>();

		NodeIF node1 = new SampleNode("node1", "java") {
			@Override
			public Optional<String> onEvent(EventIF event) {
				System.out.println("[" + name() + "] recieved: " + event.toString());
				return Optional.of("success");
			}

			@Override
			public void start() {
				// noop
			}

			@Override
			public void stop() {
				// noop
			}
		};

		NodeIF node2 = new SampleNode("node2", "java|scala") {
			@Override
			public Optional<String> onEvent(EventIF event) {
				System.out.println("[" + name() + "] recieved: " + event.toString());
				return Optional.of("success");
			}

			@Override
			public void start() {
				// noop
			}

			@Override
			public void stop() {
				// noop
			}
		};

		router.put("haskell|scala", node1);
		router.put("java|scala", node2);

		assertEquals(node1, router.findOne("haskell").get());
		assertEquals(node2, router.findOne("java").get());
	}

	@Test
	public void findAll() {
		RouterIF<NodeIF> router = new RouterRegexp<>();

		NodeIF node1 = new SampleNode("node1", "java") {
			@Override
			public Optional<String> onEvent(EventIF event) {
				System.out.println("[" + name() + "] recieved: " + event.toString());
				return Optional.of("success");
			}

			@Override
			public void start() {
				// noop
			}

			@Override
			public void stop() {
				// noop
			}
		};

		NodeIF node2 = new SampleNode("node2", "java|scala") {
			@Override
			public Optional<String> onEvent(EventIF event) {
				System.out.println("[" + name() + "] recieved: " + event.toString());
				return Optional.of("success");
			}

			@Override
			public void start() {
				// noop
			}

			@Override
			public void stop() {
				// noop
			}
		};
		router.put("haskell|scala", node1);
		router.put("java|scala", node2);

		List<NodeIF> result = router.findAll("scala");
		assertEquals(2, result.size());
		assertTrue(result.contains(node1));
		assertTrue(result.contains(node2));
	}
}
