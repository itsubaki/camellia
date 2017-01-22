package com.github.itsubaki.eventflow.system;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class RuntimeCompiler {

	/**
	 * ex. Compiler.compile("./bin", "Sample.java");
	 * 
	 * @param outputDirectoryPath
	 *            path
	 * @param javaFilePath
	 *            path
	 * @return 0 for success; nonzero otherwise
	 */
	public static int compile(String outputDirectoryPath, String javaFilePath) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		String[] args = { "-d", outputDirectoryPath, javaFilePath };
		return compiler.run(null, null, null, args);
	}
}
