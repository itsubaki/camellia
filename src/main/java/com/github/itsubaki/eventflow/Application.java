package com.github.itsubaki.eventflow;

import com.github.itsubaki.eventflow.api.WebApiService;
import com.github.itsubaki.eventflow.boot.BootableIF;
import com.github.itsubaki.eventflow.boot.Bootstrap;
import com.github.itsubaki.eventflow.config.ConfigSet;
import com.github.itsubaki.eventflow.node.NodeSet;

public class Application implements BootableIF {
	private WebApiService s = new WebApiService();
	private Eventflow f = new Eventflow();
	private ConfigSet config = new ConfigSet();
	private NodeSet node = new NodeSet();

	public static void main(String[] args) {
		Bootstrap b = new Bootstrap(new Application());
		b.start(args);
	}

	@Override
	public void start(String[] args) {
		config.load(args);
		node.load(config);
		node.get().forEach(n -> f.add(n));

		s.start();
	}

	@Override
	public void shutdown() {
		f.shutdown();
	}

}
