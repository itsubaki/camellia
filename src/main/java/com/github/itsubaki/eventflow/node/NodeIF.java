package com.github.itsubaki.eventflow.node;

import java.util.List;
import java.util.Optional;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.router.RouterIF;

public interface NodeIF {

	void setName(String name);

	String name();

	void setRoute(String route);

	String route();

	RouterIF<NodeIF> router();

	void setRouter(RouterIF<NodeIF> router);

	Optional<String> emit(EventIF event);

	List<String> emitAll(EventIF event);

	Optional<String> onEvent(EventIF event);

	Optional<String> onEmit(EventIF event);

	void onSetup();

	void onClose();

	void close();

	boolean isClosed();
}
