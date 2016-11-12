package com.github.itsubaki.eventflow.node;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public abstract class NodeABS implements NodeIF {
	private String name;
	private RouterIF<NodeIF> router;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setRouter(RouterIF<NodeIF> router) {
		this.router = router;
	}

	@Override
	public void transfer(EventIF event) {
		router.findAll(event.getName()).forEach(node -> node.recieve(event));
	}

}
