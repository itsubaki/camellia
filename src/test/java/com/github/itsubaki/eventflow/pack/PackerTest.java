package com.github.itsubaki.eventflow.pack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.github.itsubaki.eventflow.pack.PackedData;
import com.github.itsubaki.eventflow.pack.Packer;

public class PackerTest {

	@Test
	public void pack() {
		List<String> header = new LinkedList<>();
		header.add("foobar");
		header.add("piyo");
		byte[] body = "hoge".getBytes(Charset.forName("UTF-8"));
		try {
			byte[] b = Packer.pack(header, body);

			PackedData mes = Packer.unpack(b);
			assertEquals("foobar", mes.getHeader().get(0));
			assertEquals("piyo", mes.getHeader().get(1));
			assertEquals("hoge", new String(mes.getBody(), Charset.forName("UTF-8")));

		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

	}
}
