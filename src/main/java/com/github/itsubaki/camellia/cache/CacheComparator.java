package com.github.itsubaki.camellia.cache;

import java.util.Comparator;
import java.util.Map.Entry;

public class CacheComparator<K, V> implements Comparator<Entry<K, CachedObject<V>>> {

	@Override
	public int compare(Entry<K, CachedObject<V>> o1, Entry<K, CachedObject<V>> o2) {
		long l1 = o1.getValue().getLastUsed();
		long l2 = o2.getValue().getLastUsed();

		if (l1 > l2) {
			return 1;
		} else if (l1 == l2) {
			return 0;
		} else {
			return -1;
		}
	}
}
