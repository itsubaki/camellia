package com.github.itsubaki.eventflow;

import com.github.itsubaki.eventflow.api.WebApiService;
import com.github.itsubaki.eventflow.boot.BootableIF;
import com.github.itsubaki.eventflow.boot.Bootstrap;

public class Application implements BootableIF {
	private Eventflow f = new Eventflow();
	private WebApiService s = new WebApiService();

	public static void main(String[] args) {
		Bootstrap b = new Bootstrap(new Application());
		b.start(args);
	}

	@Override
	public void start(String[] args) {
		s.start();
	}

	@Override
	public void shutdown() {
		f.shutdown();

	}

}
