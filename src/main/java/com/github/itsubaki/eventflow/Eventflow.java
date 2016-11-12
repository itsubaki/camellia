package com.github.itsubaki.eventflow;

import com.github.itsubaki.eventflow.flow.FlowIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public class Eventflow implements EventflowIF {
	private RouterIF<FlowIF> router;

	@Override
	public void setRouter(RouterIF<FlowIF> router) {
		this.router = router;
	}

	@Override
	public RouterIF<FlowIF> getRouter() {
		return router;
	}

	@Override
	public void add(FlowIF gate) {
		router.put(gate.getName(), gate);
	}

}
