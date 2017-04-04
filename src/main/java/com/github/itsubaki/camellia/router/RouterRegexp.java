package com.github.itsubaki.camellia.router;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.itsubaki.camellia.cache.CacheFactory;
import com.github.itsubaki.camellia.cache.CacheIF;
import com.github.itsubaki.camellia.cache.CacheStrategy;

public class RouterRegexp<V> implements RouterIF<V> {
	private Map<V, Pattern> object = new ConcurrentHashMap<>();
	private CacheIF<String, V> cache;
	private CacheIF<String, List<V>> cacheAll;

	public RouterRegexp() {
		cache = CacheFactory.newInstance(CacheStrategy.LRU, 1024);
		cacheAll = CacheFactory.newInstance(CacheStrategy.LRU, 1024);
	}

	@Override
	public Set<V> get() {
		return object.keySet();
	}

	@Override
	public void put(String regexp, V target) {
		Pattern p = Pattern.compile(regexp);
		object.put(target, p);
	}

	@Override
	public void remove(V target) {
		Optional<V> opt = object.keySet().stream().filter(v -> v == target).findFirst();
		opt.ifPresent(v -> object.remove(v));
	}

	@Override
	public Optional<V> findOne(String name) {
		Optional<V> cached = cache.get(name);
		if (cached.isPresent()) {
			return cached;
		}

		Stream<V> stream = object.keySet().stream();
		Optional<V> opt = stream.filter(n -> object.get(n).matcher(name).find()).findFirst();

		if (opt.isPresent()) {
			V v = opt.get();
			cache.put(name, v);
			return Optional.of(v);
		}

		return Optional.empty();
	}

	@Override
	public List<V> findAll(String name) {
		Optional<List<V>> cached = cacheAll.get(name);
		if (cached.isPresent()) {
			return cached.get();
		}

		Stream<V> stream = object.keySet().stream();
		List<V> list = stream.filter(n -> object.get(n).matcher(name).find()).collect(Collectors.toList());
		cacheAll.put(name, list);

		return list;
	}

}
