package com.github.itsubaki.eventflow.cache;

public class CacheFactory {

	public static <K, V> CacheIF<K, V> newInstance(CacheStrategy strategy,
			int size) {
		if (strategy == CacheStrategy.LRU) {
			CacheIF<K, V> cache = new CacheLRU<>(size);
			return cache;
		}

		if (strategy == CacheStrategy.MRU) {
			CacheIF<K, V> cache = new CacheMRU<>(size);
			return cache;
		}

		throw new IllegalArgumentException("Invalid. arg: " + strategy);
	}

}
