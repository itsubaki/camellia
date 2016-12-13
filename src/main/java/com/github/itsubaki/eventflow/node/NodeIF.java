package com.github.itsubaki.eventflow.node;

import java.util.List;
import java.util.Optional;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface NodeIF {

	void setName(String name);

	String getName();

	void setRoute(String route);

	String getRoute();

	RouterIF<NodeIF> getRouter();

	void setRouter(RouterIF<NodeIF> router);

	Optional<String> transfer(EventIF event);

	List<String> transferAll(EventIF event);

	Optional<String> recieve(EventIF event);

	void start();

	void shutdown();

	void shutdownHook();

	boolean isClosed();
}
