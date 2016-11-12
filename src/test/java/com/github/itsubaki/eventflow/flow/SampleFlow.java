package com.github.itsubaki.eventflow.cache.gate;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.gate.GateIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public class SampleGate implements GateIF {
	private RouterIF<GateIF> router;

	@Override
	public void setRouter(RouterIF<GateIF> router) {
		this.router = router;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transfer(EventIF event) {
		router.findAll(event.getName()).forEach(g -> g.recieve(event));
	}

	@Override
	public void recieve(EventIF event) {
		// TODO Auto-generated method stub
	}

}
