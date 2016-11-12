package com.github.itsubaki.eventflow.gate;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface GateIF {

	String getName();

	void setRouter(RouterIF<GateIF> router);

	void transfer(EventIF event);

	void recieve(EventIF event);
}
