package com.github.itsubaki.eventflow.dispatch;

import org.junit.Test;

public class DispatchTest {

	@Test
	public void test() {
		DispatchQueue q = Dispatch.getQueue();
		q.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello");
			}
		});

		Dispatch.shutdown();
	}
}
