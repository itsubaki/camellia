package com.github.itsubaki.eventflow;

import com.github.itsubaki.eventflow.node.NodeIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface EventflowIF {

	void setRouter(RouterIF<NodeIF> router);

	RouterIF<NodeIF> getRouter();

	void add(NodeIF node);

	void remove(NodeIF node);

	void shutdown();
}
