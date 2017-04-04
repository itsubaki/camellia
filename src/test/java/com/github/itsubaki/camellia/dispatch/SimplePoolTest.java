package com.github.itsubaki.camellia.dispatch;

import org.junit.Test;

import com.github.itsubaki.camellia.dispatch.SimplePool;

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
