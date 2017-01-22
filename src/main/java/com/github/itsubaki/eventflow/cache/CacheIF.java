package com.github.itsubaki.eventflow.cache;

import java.util.Optional;

public interface CacheIF<K, V> {

	Optional<V> get(K k);

	void put(K k, V v);

	boolean containsKey(K k);

	long getHitCount();

	double getHitRate();

	void setMaxCacheSize(int maxCacheSize);

	int getMaxCacheSize();

	int getCurrentCacheSize();

}
