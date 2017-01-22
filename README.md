# eventflow
n-direction event flow engine

[![Build Status](https://travis-ci.org/itsubaki/eventflow.svg?branch=master)](https://travis-ci.org/itsubaki/eventflow)


```
NodeIF node1 = new SampleNode("node1");
node1.setRoute("java");

NodeIF node2 = new SampleNode("node2");
node2.setRoute("java|scala");

EventflowIF flow = new Eventflow();
flow.setRouter(new RouterRegexp());
flow.add(node1);
flow.add(node2);

echo(node1.transferAll(new MapEvent("java")));  // -> Event are transferred to node1
echo(node2.transferAll(new MapEvent("scala"))); // -> Event are transferred to node1 and node2

flow.shutdown();
```

# technique

## Router

```
RouterIF<NodeIF> router = new RouterRegexp();

SampleNode node1 = new SampleNode();
SampleNode node2 = new SampleNode();

router.put("haskell|scala", node1);
router.put("java|scala", node2);

assertEquals(node1, router.findOne("haskell").get());
assertEquals(node2, router.findOne("java").get());

List<NodeIF> result = router.findAll("scala");
assertEquals(2, result.size());
assertTrue(result.contains(node1));
assertTrue(result.contains(node2));
```

## Cache

```
CacheLRU<String, String> cache = new CacheLRU<>(3);
cache.put("foobar", "hoge");
cache.get("foobar"); // -> hoge
cache.get("hoge");   // -> Optional.empty()
cache.get("piyo");   // -> Optional.empty()
assertEquals(0.333, cache.getHitRate(), 0.001);
```


## ObjectPool

```
ObjectPool<String> pool = new StringObjectPool(size);
for (int i = 0; i < size; i++) {
	PooledObject<String> object = pool.get();
	assertEquals("PooledString(0)", object.get());
	object.close();
}
```




## Plugin

```
File f = new File("./build/libs/eventflow.jar");

Plugin p = PluginLoader.load(f);
System.out.println(p.getName());
for (Class<?> clazz : p.getClazz()) {
	System.out.println(clazz.getName());
}

for (Entry<Object, Object> entry : p.getManifest().getMainAttributes().entrySet()) {
	System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

## RuntimeCompiler

```
RuntimeCompiler.compile("./bin","Sample.java");
```

## Unsafe

```
UnsafeProxy unsafe = new UnsafeProxy();
UnsafeTest target = (UnsafeTest) unsafe.allocateInstance(UnsafeTest.class);
assertNull(target.name());

unsafe.put(target, "name", "foobar");
assertEquals("foobar", target.name());
```
