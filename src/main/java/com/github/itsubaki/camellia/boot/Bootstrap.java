package com.github.itsubaki.camellia.boot;

public class Bootstrap {
	private BootableIF boot;

	public Bootstrap(BootableIF boot) {
		this.boot = boot;
	}

	public void start(String[] args) {
		ShutdownHook hook = new ShutdownHook(boot);
		Runtime.getRuntime().addShutdownHook(new Thread(hook));

		try {
			boot.start(args);
		} catch (Exception e) {
			boot.shutdown();
			e.printStackTrace();
		}

	}

}
