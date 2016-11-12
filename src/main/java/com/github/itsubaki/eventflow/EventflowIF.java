package com.github.itsubaki.eventflow;

import com.github.itsubaki.eventflow.gate.GateIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface EventflowIF {

	void setRouter(RouterIF<GateIF> router);

	RouterIF<GateIF> getRouter();

	void add(GateIF gate);
}
