package com.github.itsubaki.eventflow.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public abstract class Node implements NodeIF {
	private String name;
	private String route;
	private RouterIF<NodeIF> router;
	private AtomicBoolean closed = new AtomicBoolean(false);

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setRoute(String route) {
		this.route = route;
	}

	@Override
	public String getRoute() {
		return route;
	}

	@Override
	public RouterIF<NodeIF> getRouter() {
		return router;
	}

	@Override
	public void setRouter(RouterIF<NodeIF> router) {
		this.router = router;
	}

	@Override
	public Optional<String> emit(EventIF event) {
		Optional<NodeIF> opt = router.findOne(event.getName());
		if (opt.isPresent()) {
			return opt.get().onEmit(event);
		}
		return Optional.empty();
	}

	@Override
	public List<String> emitAll(EventIF event) {
		List<String> list = new ArrayList<>();
		router.findAll(event.getName()).forEach(node -> {
			Optional<String> opt = node.onEmit(event);
			opt.ifPresent(value -> list.add(value));
		});
		return list;
	}

	@Override
	public Optional<String> onEmit(EventIF event) {
		if (isClosed()) {
			return Optional.empty();
		}

		return onEvent(event);
	}

	@Override
	public void onSetup() {

	}

	@Override
	public void onClose() {

	}

	@Override
	public void close() {
		closed.set(true);
	}

	@Override
	public boolean isClosed() {
		return closed.get();
	}

}
