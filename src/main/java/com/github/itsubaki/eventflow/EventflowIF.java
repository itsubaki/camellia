package com.github.itsubaki.eventflow;

import com.github.itsubaki.eventflow.flow.FlowIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface EventflowIF {

	void setRouter(RouterIF<FlowIF> router);

	RouterIF<FlowIF> getRouter();

	void add(FlowIF flow);
}
