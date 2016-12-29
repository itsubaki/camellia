package com.github.itsubaki.eventflow.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class BinaryPackTest {

	@Test
	public void pack() {
		List<String> header = new LinkedList<>();
		header.add("foobar");
		header.add("piyo");
		byte[] body = "hoge".getBytes(Charset.forName("UTF-8"));
		try {
			byte[] b = BinaryPack.pack(header, body);

			PackedMessage mes = BinaryPack.unpack(b);
			assertEquals("foobar", mes.getHeader().get(0));
			assertEquals("piyo", mes.getHeader().get(1));
			assertEquals("hoge", new String(mes.getBody(), Charset.forName("UTF-8")));

		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

	}
}
