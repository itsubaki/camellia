package com.github.itsubaki.eventflow.cache;

public class CacheFactory {

	public static <K, V> CacheIF<K, V> newInstance(CacheStrategy strategy,
			int size) {
		if (strategy == CacheStrategy.LRU) {
			return new CacheLRU<>(size);
		}

		if (strategy == CacheStrategy.MRU) {
			return new CacheMRU<>(size);
		}

		throw new IllegalArgumentException("strategy: " + strategy);
	}

}
