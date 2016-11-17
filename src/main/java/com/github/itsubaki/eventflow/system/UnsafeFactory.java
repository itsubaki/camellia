package com.github.itsubaki.eventflow.system;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeFactory {
	public static Unsafe newInstance() {
		Field declaredField = null;
		boolean accessible = false;
		try {
			declaredField = Unsafe.class.getDeclaredField("theUnsafe");
			accessible = declaredField.isAccessible();
			declaredField.setAccessible(true);
			return (Unsafe) declaredField.get(null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			declaredField.setAccessible(accessible);
		}

		return null;
	}
}
