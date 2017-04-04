package com.github.itsubaki.camellia.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class Plugin {

	private String name;
	private List<Class<?>> clazz = new ArrayList<>();
	private Manifest manifest;
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Class<?>> getClazz() {
		return clazz;
	}

	public void addClazz(Class<?> clazz) {
		this.clazz.add(clazz);
	}

	public Manifest getManifest() {
		return manifest;
	}

	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
