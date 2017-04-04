package com.github.itsubaki.camellia.boot;

public class ShutdownHook implements Runnable {
	private BootableIF boot;

	public ShutdownHook(BootableIF boot) {
		this.boot = boot;
	}

	@Override
	public void run() {
		boot.shutdown();
	}
}
