package com.github.itsubaki.eventflow;

import java.util.Optional;

import com.github.itsubaki.eventflow.node.NodeIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public class Eventflow implements EventflowIF {
	private RouterIF<NodeIF> router;

	@Override
	public void setRouter(RouterIF<NodeIF> router) {
		this.router = router;
	}

	@Override
	public RouterIF<NodeIF> getRouter() {
		return router;
	}

	@Override
	public void add(NodeIF node) {
		node.setRouter(router);
		node.start();
		router.put(node.getRoute(), node);
	}

	@Override
	public void remove(NodeIF node) {
		router.remove(node);
		node.destroy();
		node.shutdown();
	}

	@Override
	public void shutdown() {
		router.get().stream().forEach(n -> n.destroy());
		router.get().stream().forEach(n -> n.shutdown());
	}

	@Override
	public Optional<NodeIF> getNode(String name) {
		return router.get().stream().filter(n -> n.getName().equalsIgnoreCase(name)).findFirst();
	}

}
