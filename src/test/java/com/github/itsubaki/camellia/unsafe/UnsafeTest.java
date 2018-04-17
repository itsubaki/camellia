package com.github.itsubaki.camellia.unsafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.github.itsubaki.camellia.unsafe.UnsafeFactory;
import com.github.itsubaki.camellia.unsafe.UnsafeProxy;

import sun.misc.Unsafe;

public class UnsafeTest {
	private String name = "UnsafeTest";

	public String name() {
		return name;
	}

	@Test
	public void test() {
		try {
			Unsafe unsafe = UnsafeFactory.newInstance();
			UnsafeTest target = (UnsafeTest) unsafe.allocateInstance(UnsafeTest.class);
			assertNull(target.name());

			unsafe.putObject(target, unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("name")), "foobar");
			assertEquals("foobar", target.name());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void wrapper() {
		try {
			UnsafeProxy unsafe = new UnsafeProxy();
			UnsafeTest target = (UnsafeTest) unsafe.allocateInstance(UnsafeTest.class);
			assertNull(target.name());

			unsafe.put(target, "name", "foobar");
			assertEquals("foobar", target.name());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
