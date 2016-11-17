package com.github.itsubaki.eventflow.node;

import java.util.Optional;

import com.github.itsubaki.eventflow.event.EventIF;

public class SampleNode extends NodeABS {

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<String> recieve(EventIF event) {
		return Optional.of(getName() + ": " + event.toString());
	}

}
