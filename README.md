# camellia


# Build

```
$ brew install gradle
$ gradle build
```

# API

## ObjectPool

```java
ObjectPool<String> pool = new StringObjectPool(size);

PooledObject<String> o = pool.get();
assertEquals("PooledString(0)", o.get());

o.close();
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
