package com.github.itsubaki.eventflow.node;

import java.util.Optional;

import com.github.itsubaki.eventflow.event.EventIF;

public class SampleNode extends NodeABS {

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdownHook() {

	}

	@Override
	public Optional<String> recieve(EventIF event) {
		if (isClosed()) {
			return Optional.empty();
		}

		return Optional.of(getName() + ": " + event.toString());
	}

}
