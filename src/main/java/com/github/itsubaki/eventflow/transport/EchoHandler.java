package com.github.itsubaki.eventflow.transport;

public class EchoHandler implements HandlerIF {

	@Override
	public byte[] handle(byte[] in) {
		return in;
	}
}
