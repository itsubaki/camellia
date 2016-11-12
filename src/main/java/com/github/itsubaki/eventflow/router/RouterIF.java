package com.github.itsubaki.eventflow.router;

import java.util.List;
import java.util.Optional;

public interface RouterIF<V> {

	Optional<V> findOne(String find);

	List<V> findAll(String find);

	void put(String key, V target);

}
