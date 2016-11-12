package com.github.itsubaki.eventflow;

import com.github.itsubaki.eventflow.gate.GateIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public class Eventflow implements EventflowIF {
	private RouterIF<GateIF> router;

	@Override
	public void setRouter(RouterIF<GateIF> router) {
		this.router = router;
	}

	@Override
	public RouterIF<GateIF> getRouter() {
		return router;
	}

	@Override
	public void add(GateIF gate) {
		router.put(gate.getName(), gate);
	}

}
