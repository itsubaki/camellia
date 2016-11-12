package com.github.itsubaki.eventflow.cache;

public interface CacheIF<K, V> {

	V get(K k);

	void put(K k, V v);

	long getHitCount();

	double getHitRate();

	void setMaxCacheSize(int maxCacheSize);

	int getMaxCacheSize();

	int getCurrentCacheSize();

}
