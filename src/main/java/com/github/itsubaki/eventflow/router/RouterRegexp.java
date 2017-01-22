package com.github.itsubaki.eventflow.router;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.itsubaki.eventflow.cache.CacheFactory;
import com.github.itsubaki.eventflow.cache.CacheIF;
import com.github.itsubaki.eventflow.cache.CacheStrategy;
import com.github.itsubaki.eventflow.node.NodeIF;

public class RouterRegexp implements RouterIF<NodeIF> {
	private Map<NodeIF, Pattern> object = new ConcurrentHashMap<>();
	private CacheIF<String, NodeIF> cache;
	private CacheIF<String, List<NodeIF>> cacheAll;

	public RouterRegexp() {
		cache = CacheFactory.newInstance(CacheStrategy.LRU, 1024);
		cacheAll = CacheFactory.newInstance(CacheStrategy.LRU, 1024);
	}

	@Override
	public Set<NodeIF> get() {
		return object.keySet();
	}

	@Override
	public void put(String regexp, NodeIF target) {
		Pattern p = Pattern.compile(regexp);
		object.put(target, p);
	}

	@Override
	public void remove(NodeIF target) {
		Optional<NodeIF> opt = object.keySet().stream()
				.filter(node -> node.getName().equalsIgnoreCase(target.getName())).findFirst();
		opt.ifPresent(node -> object.remove(node));
	}

	@Override
	public Optional<NodeIF> findOne(String name) {
		Optional<NodeIF> cached = cache.get(name);
		if (cached.isPresent()) {
			return cached;
		}

		Stream<NodeIF> stream = object.keySet().stream();
		Optional<NodeIF> opt = stream.filter(n -> object.get(n).matcher(name).find()).findFirst();

		if (opt.isPresent()) {
			NodeIF node = opt.get();
			cache.put(name, node);
			return Optional.of(node);
		}

		return Optional.empty();
	}

	@Override
	public List<NodeIF> findAll(String name) {
		Optional<List<NodeIF>> cached = cacheAll.get(name);
		if (cached.isPresent()) {
			return cached.get();
		}

		Stream<NodeIF> stream = object.keySet().stream();
		List<NodeIF> list = stream.filter(n -> object.get(n).matcher(name).find()).collect(Collectors.toList());
		cacheAll.put(name, list);

		return list;
	}

}
