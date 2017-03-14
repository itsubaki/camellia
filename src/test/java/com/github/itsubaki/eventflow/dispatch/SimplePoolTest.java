package com.github.itsubaki.eventflow.dispatch;

import org.junit.Test;

public class SimplePoolTest {

	@Test
	public void test() {
		SimplePool pool = new SimplePool(3);
		pool.execute(new Runnable() {

			@Override
			public void run() {
				System.out.println("hello");
			}

		});
		pool.shutdown();
	}
}
