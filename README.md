# camellia

[![Build Status](https://travis-ci.org/itsubaki/camellia.svg?branch=master)](https://travis-ci.org/itsubaki/camellia)

# Build

```
$ brew install gradle
$ gradle build
```

# API

## Cache

```java
CacheIF<String, String> cache = CacheFactory.newInstance(CacheStrategy.LRU, 3);
cache.put("foobar", "hoge");

cache.get("foobar"); // -> hoge
cache.get("hoge");   // -> Optional.empty()
cache.get("piyo");   // -> Optional.empty()

assertEquals(0.333, cache.getHitRate(), 0.001);
```


## ObjectPool

```java
ObjectPool<String> pool = new StringObjectPool(size);

PooledObject<String> object = pool.get();
assertEquals("PooledString(0)", object.get());

object.close();
```

## Plugin

```java
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

```java
RuntimeCompiler.compile("./bin","Sample.java");
```

## Unsafe

```java
UnsafeProxy unsafe = new UnsafeProxy();
UnsafeTest target = (UnsafeTest) unsafe.allocateInstance(UnsafeTest.class);
assertNull(target.name());

unsafe.put(target, "name", "foobar");
assertEquals("foobar", target.name());
```
