package com.github.itsubaki.eventflow.node;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public abstract class NodeABS implements NodeIF {
	private String name;
	private String regexp;
	private RouterIF<NodeIF> router;

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setRegexp(String regexp) {
		this.regexp = regexp;
	}

	@Override
	public String getRegexp() {
		return regexp;
	}

	@Override
	public RouterIF<NodeIF> getRouter() {
		return router;
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
