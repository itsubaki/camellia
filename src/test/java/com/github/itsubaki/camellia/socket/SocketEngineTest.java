package com.github.itsubaki.camellia.socket;

import org.junit.Test;

import com.github.itsubaki.camellia.socket.SocketEngine;
import com.github.itsubaki.camellia.util.UtilTest;

public class SocketEngineTest {

	@Test
	public void test() {
		UtilTest.log();

		SocketEngine engine = new SocketEngine(8080);
		try {
			engine.open();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			engine.close();
		}
	}
}