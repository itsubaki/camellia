package com.github.itsubaki.eventflow.cache;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class CacheLRU<K, V> implements CacheIF<K, V> {
	private int maxCacheSize = 1024;
	private AtomicInteger currentCacheSize = new AtomicInteger(0);
	private AtomicLong getCount = new AtomicLong(0);
	private Map<K, CacheObject<V>> cache;

	public CacheLRU() {
		setMaxCacheSize(1024);
	}

	public CacheLRU(int maxCacheSize) {
		setMaxCacheSize(maxCacheSize);
	}

	public Map<K, CacheObject<V>> getCache() {
		return cache;
	}

	@Override
	public boolean containsKey(K k) {
		return cache.containsKey(k);
	}

	@Override
	public void setMaxCacheSize(int maxCacheSize) {
		this.maxCacheSize = maxCacheSize;
		cache = new ConcurrentHashMap<>(maxCacheSize);
	}

	@Override
	public int getMaxCacheSize() {
		return maxCacheSize;
	}

	@Override
	public int getCurrentCacheSize() {
		return currentCacheSize.get();
	}

	@Override
	public long getHitCount() {
		Stream<CacheObject<V>> stream = cache.values().stream();
		return stream.mapToLong(obj -> obj.getHitCount()).sum();
	}

	@Override
	public double getHitRate() {
		double div = (double) getHitCount() / getCount.get();
		BigDecimal rate = new BigDecimal(div);
		return rate.setScale(3, BigDecimal.ROUND_FLOOR).doubleValue();
	}

	@Override
	public V get(K k) {
		getCount.incrementAndGet();

		CacheObject<V> v = cache.get(k);
		if (v == null) {
			return null;
		}

		return v.get();
	}

	@Override
	public void put(K k, V v) {
		if (cache.containsKey(k)) {
			return;
		}

		if (currentCacheSize.incrementAndGet() <= maxCacheSize) {
			cache.put(k, new CacheObject<>(v));
			return;
		}

		// size.incrementAndGet() > maxSize
		Stream<Entry<K, CacheObject<V>>> stream = cache.entrySet().stream();
		Optional<Entry<K, CacheObject<V>>> candidate = candidate(stream);
		if (!candidate.isPresent()) {
			return;
		}

		// candidate have CacheObject<V>
		K key = candidate.get().getKey();
		if (cache.remove(key) != null) {
			currentCacheSize.decrementAndGet();
		}
		cache.put(k, new CacheObject<>(v));
	}

	public Optional<Entry<K, CacheObject<V>>> candidate(Stream<Entry<K, CacheObject<V>>> stream) {
		return stream.min((e1, e2) -> new CacheComparator<K, V>().compare(e1, e2));
	}
}
