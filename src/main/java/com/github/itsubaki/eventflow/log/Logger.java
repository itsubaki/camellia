package com.github.itsubaki.eventflow.log;

import org.slf4j.LoggerFactory;

public class Logger {
	private org.slf4j.Logger log;
	private String name;

	public Logger(String name, Class<?> clazz) {
		this.name = name;
		log = LoggerFactory.getLogger(clazz);
	}

	public String name() {
		return name;
	}

	public void trace(String message) {
		if (!log.isTraceEnabled()) {
			return;
		}
		log.trace("[" + name() + "] " + message);
	}

	public void debug(String message) {
		if (!log.isDebugEnabled()) {
			return;
		}
		log.debug("[" + name() + "] " + message);
	}

	public void info(String message) {
		log.info("[" + name() + "] " + message);
	}

	public void warn(String message) {
		log.warn("[" + name() + "] " + message);
	}

	public void warn(String message, Throwable t) {
		log.warn("[" + name() + "] " + message, t);
	}

	public void error(String message) {
		log.error("[" + name() + "] " + message);
	}

	public void error(String message, Throwable t) {
		log.error("[" + name() + "]" + message, t);
	}

}
