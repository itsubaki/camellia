package com.github.itsubaki.eventflow.system;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

public class MemoryMetrics {
	private static MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
	private static List<MemoryPoolMXBean> pbean = ManagementFactory.getMemoryPoolMXBeans();

	public static MemoryUsage getHeapMemoryUsage() {
		return mbean.getHeapMemoryUsage();
	}

	public static int getObjectPendingFinalizationCount() {
		return mbean.getObjectPendingFinalizationCount();
	}

	public static MemoryUsage getMaxMetaspaceUsage() {
		return pbean.stream().filter(p -> p.getName().equalsIgnoreCase("Metaspace")).findFirst().get().getUsage();
	}
}
