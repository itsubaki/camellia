package com.github.itsubaki.eventflow.pack;

import java.util.LinkedList;
import java.util.List;

public class UnPacked {
	private List<String> header = new LinkedList<>();
	private byte[] body = new byte[0];

	public List<String> getHeader() {
		return header;
	}

	public void addHeader(String header) {
		this.header.add(header);
	}

	public byte[] getBody() {
		return body.clone();
	}

	public void setBody(byte[] body) {
		this.body = body.clone();
	}
}
