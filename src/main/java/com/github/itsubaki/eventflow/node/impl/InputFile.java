package com.github.itsubaki.eventflow.node.impl;

import java.util.Optional;

import com.github.itsubaki.eventflow.event.EventIF;
import com.github.itsubaki.eventflow.node.NodeABS;

public class InputFile extends NodeABS {

	public InputFile(String name) {
		super(name);
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	@Override
	public Optional<String> onEvent(EventIF event) {
		return Optional.empty();
	}

}
