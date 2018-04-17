package com.github.itsubaki.camellia.unsafe;

import sun.misc.Unsafe;

public class UnsafeProxy {
	private Unsafe unsafe = null;

	public UnsafeProxy() {
		this.unsafe = UnsafeFactory.newInstance();
	}

	public Unsafe get() {
		return unsafe;
	}

	public Object allocateInstance(Class<?> clazz) {
		if (unsafe == null) {
			return null;
		}
		try {
			return unsafe.allocateInstance(clazz);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void put(Object target, String field, Object value) throws NoSuchFieldException {
		try {
			unsafe.putObject(target, unsafe.objectFieldOffset(target.getClass().getDeclaredField(field)), value);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			throw e;
		} catch (SecurityException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public long allocateMemory(long bytes) {
		return unsafe.allocateMemory(bytes);
	}

	public void freeMemory(long address) {
		unsafe.freeMemory(address);
	}

	public void copyMemory(long srcAddress, long destAddress, long bytes) {
		unsafe.copyMemory(srcAddress, destAddress, bytes);
	}

}
