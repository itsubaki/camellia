package com.github.itsubaki.eventflow.plugin;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {

	public static Plugin load(File file)
			throws IOException, ClassNotFoundException {
		Plugin plugin = new Plugin();
		JarFile jar = null;
		try {
			jar = new JarFile(file);
			plugin.setManifest(jar.getManifest());
			plugin.setName(jar.getName());
			plugin.setComment(jar.getComment());

			URL url = file.getCanonicalFile().toURI().toURL();
			URL[] urls = { url };
			URLClassLoader loader = AccessController
					.doPrivileged(new PrivURLClassLoader(urls));
			Enumeration<JarEntry> entries = jar.entries();

			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				if (entry.isDirectory()) {
					continue;
				}

				String fn = entry.getName();
				if (!fn.endsWith(".class")) {
					continue;
				}

				if (fn.contains("package-info")) {
					continue;
				}

				String cn = fn.replace(".class", "").replace("/", ".");
				Class<?> clazz = loader.loadClass(cn);
				plugin.addClazz(clazz);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(jar);
		}

		return plugin;
	}

	public static void close(Closeable closeable) {
		if (closeable == null) {
			return;
		}

		try {
			closeable.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
