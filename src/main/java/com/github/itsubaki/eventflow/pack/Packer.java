package com.github.itsubaki.eventflow.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;

public class BinaryPack {
	private static final Charset CHARSET = Charset.forName("UTF-8");

	public static byte[] pack(List<String> header) throws IOException {
		byte[] bytea = new byte[0];
		ByteArrayOutputStream st = new ByteArrayOutputStream();

		try {
			byte[] size = ByteBuffer.allocate(4).putInt(header.size()).array();
			st.write(size);

			for (String h : header) {
				byte[] len = ByteBuffer.allocate(4).putInt(h.length()).array();
				byte[] str = h.getBytes(CHARSET);
				st.write(len);
				st.write(str);
			}

			bytea = st.toByteArray();
		} finally {
			st.close();
		}

		return bytea;
	}

	public static byte[] pack(List<String> header, byte[] body) throws IOException {
		byte[] bytea = pack(header);
		ByteArrayOutputStream st = new ByteArrayOutputStream();

		try {
			st.write(bytea);
			st.write(body);
			bytea = st.toByteArray();
		} finally {
			st.close();
		}

		return bytea;
	}

	public static byte[] pack(PackedMessage message) throws IOException {
		return pack(message.getHeader(), message.getBody());
	}

	public static PackedMessage unpack(byte[] bin) {
		PackedMessage mes = new PackedMessage();

		byte[] buf = new byte[4];
		System.arraycopy(bin, 0, buf, 0, 4);
		int size = ByteBuffer.wrap(buf).getInt();

		int offset = 4;
		for (int i = 0; i < size; i++) {
			buf = new byte[4];
			System.arraycopy(bin, offset, buf, 0, 4);
			int len = ByteBuffer.wrap(buf).getInt();

			buf = new byte[len];
			System.arraycopy(bin, offset + 4, buf, 0, len);
			String h = new String(buf, CHARSET);

			offset = offset + 4 + len;
			mes.addHeader(h);
		}

		int binlen = bin.length - offset;
		byte[] body = new byte[binlen];
		System.arraycopy(bin, offset, body, 0, binlen);
		mes.setBody(body);

		return mes;
	}

}
