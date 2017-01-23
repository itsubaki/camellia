package com.github.itsubaki.eventflow.node;

import java.util.Optional;

import com.github.itsubaki.eventflow.event.EventIF;

public class SampleNode extends Node {

	public SampleNode(String name) {
		setName(name);
	}

	@Override
	public Optional<String> onEvent(EventIF event) {
		if (isClosed()) {
			return Optional.empty();
		}

		return Optional.of("name: " + getName() + ", recieved: " + event.toString());
	}

}
