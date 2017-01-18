package com.github.itsubaki.eventflow.router;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.github.itsubaki.eventflow.node.NodeIF;

public interface RouterIF<V> {

	Set<NodeIF> get();

	Optional<V> findOne(String find);

	List<V> findAll(String find);

	void put(String key, V target);

	void remove(V target);

}
