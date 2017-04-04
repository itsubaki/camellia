package com.github.itsubaki.camellia.cache;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

public class CacheMRU<K, V> extends CacheLRU<K, V> {

	public CacheMRU() {
		setMaxCacheSize(1024);
	}

	public CacheMRU(int maxCacheSize) {
		setMaxCacheSize(maxCacheSize);
	}

	@Override
	public Optional<Entry<K, CachedObject<V>>> select(Stream<Entry<K, CachedObject<V>>> stream) {
		return stream.max((e1, e2) -> new CacheComparator<K, V>().compare(e1, e2));
	}
}
