package com.github.itsubaki.camellia.plugin;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import org.junit.Ignore;
import org.junit.Test;

public class PluginLoaderTest {

	@Ignore
	@Test
	public void test() {
		File f = new File("./build/libs/camellia.jar");
		try {
			Plugin p = PluginLoader.load(f);
			System.out.println(p.getName());
			for (Class<?> clazz : p.getClazz()) {
				System.out.println(clazz.getName());
			}
			for (Entry<Object, Object> entry : p.getManifest().getMainAttributes().entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
