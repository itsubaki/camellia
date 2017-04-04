package com.github.itsubaki.camellia.cache;

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
	private Map<K, CachedObject<V>> cache;

	public CacheLRU() {
		setMaxCacheSize(1024);
	}

	public CacheLRU(int maxCacheSize) {
		setMaxCacheSize(maxCacheSize);
	}

	public Map<K, CachedObject<V>> getCache() {
		return cache;
	}

	@Override
	public boolean containsKey(K key) {
		return cache.containsKey(key);
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
		Stream<CachedObject<V>> stream = cache.values().stream();
		return stream.mapToLong(obj -> obj.getHitCount()).sum();
	}

	@Override
	public double getHitRate() {
		double div = (double) getHitCount() / getCount.get();
		BigDecimal rate = new BigDecimal(div);
		return rate.setScale(3, BigDecimal.ROUND_FLOOR).doubleValue();
	}

	@Override
	public Optional<V> get(K k) {
		getCount.incrementAndGet();

		CachedObject<V> v = cache.get(k);
		if (v == null) {
			return Optional.empty();
		}

		return Optional.of(v.get());
	}

	@Override
	public void put(K k, V v) {
		if (cache.containsKey(k)) {
			cache.put(k, new CachedObject<>(v));
			return;
		}

		if (currentCacheSize.incrementAndGet() <= maxCacheSize) {
			cache.put(k, new CachedObject<>(v));
			return;
		}

		// size.incrementAndGet() > maxSize
		Stream<Entry<K, CachedObject<V>>> stream = cache.entrySet().stream();
		Optional<Entry<K, CachedObject<V>>> selected = select(stream);
		if (!selected.isPresent()) {
			return;
		}

		// candidate have CacheObject<V>
		K key = selected.get().getKey();
		if (cache.remove(key) != null) {
			currentCacheSize.decrementAndGet();
		}
		cache.put(k, new CachedObject<>(v));
	}

	public Optional<Entry<K, CachedObject<V>>> select(Stream<Entry<K, CachedObject<V>>> stream) {
		return stream.min((e1, e2) -> new CacheComparator<K, V>().compare(e1, e2));
	}
}
