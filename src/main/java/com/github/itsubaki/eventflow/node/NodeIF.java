package com.github.itsubaki.eventflow.node;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface NodeIF {

	String getName();

	String getRegexp();

	void setRouter(RouterIF<NodeIF> router);

	void transfer(EventIF event);

	void recieve(EventIF event);
}
