package com.github.itsubaki.eventflow.node;

public abstract class SampleNode extends NodeABS {

	public SampleNode(String name, String route) {
		super(name);
		setRoute(route);
	}

}
