package com.github.itsubaki.eventflow.flow;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public abstract class FlowABS implements FlowIF {
	private String name;
	private RouterIF<FlowIF> router;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setRouter(RouterIF<FlowIF> router) {
		this.router = router;
	}

	@Override
	public void transfer(EventIF event) {
		router.findAll(event.getName()).forEach(g -> g.recieve(event));
	}

}
