package com.github.itsubaki.eventflow.flow;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface FlowIF {

	String getName();

	void setRouter(RouterIF<FlowIF> router);

	void transfer(EventIF event);

	void recieve(EventIF event);
}
