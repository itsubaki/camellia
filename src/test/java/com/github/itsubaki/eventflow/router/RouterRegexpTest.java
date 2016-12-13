package com.github.itsubaki.eventflow.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.itsubaki.eventflow.node.NodeIF;
import com.github.itsubaki.eventflow.node.SampleNode;

public class RouterRegexpTest {

	@Test
	public void find() {
		RouterIF<NodeIF> m = new RouterRegexp();

		SampleNode node1 = new SampleNode();
		SampleNode node2 = new SampleNode();

		m.put("haskell|scala", node1);
		m.put("java|scala", node2);

		assertEquals(node1, m.findOne("haskell").get());
		assertEquals(node2, m.findOne("java").get());
	}

	@Test
	public void findAll() {
		RouterIF<NodeIF> m = new RouterRegexp();

		SampleNode node1 = new SampleNode();
		SampleNode node2 = new SampleNode();

		m.put("haskell|scala", node1);
		m.put("java|scala", node2);

		List<NodeIF> result = m.findAll("scala");
		assertEquals(2, result.size());
		assertTrue(result.contains(node1));
		assertTrue(result.contains(node2));
	}
}
