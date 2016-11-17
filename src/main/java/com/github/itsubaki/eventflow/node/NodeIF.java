package com.github.itsubaki.eventflow.node;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface NodeIF {

	void setName(String name);

	String getName();

	void setRegexp(String regexp);

	String getRegexp();

	RouterIF<NodeIF> getRouter();

	void setRouter(RouterIF<NodeIF> router);

	void transfer(EventIF event);

	void recieve(EventIF event);

	void start();

	void shutdown();
}
