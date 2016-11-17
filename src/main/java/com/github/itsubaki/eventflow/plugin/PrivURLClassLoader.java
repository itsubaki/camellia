package com.github.itsubaki.eventflow.plugin;

import java.net.URL;
import java.net.URLClassLoader;
import java.security.PrivilegedAction;

public class PrivURLClassLoader implements PrivilegedAction<URLClassLoader> {

	private URL[] urls;

	public PrivURLClassLoader(URL[] urls) {
		this.urls = urls;
	}

	@Override
	public URLClassLoader run() {
		return new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
	}

}
