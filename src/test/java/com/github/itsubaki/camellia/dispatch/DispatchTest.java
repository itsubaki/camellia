package com.github.itsubaki.camellia.dispatch;

import org.junit.Test;

import com.github.itsubaki.camellia.dispatch.Dispatch;
import com.github.itsubaki.camellia.dispatch.DispatchQueue;

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
