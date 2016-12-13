package com.github.itsubaki.eventflow.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * generalization of tomcat jdbc-pool
 * 
 * @author tsubaki
 *
 * @param <T>
 *            PooledObject
 */
public abstract class ObjectPool<T> {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private BlockingQueue<T> idle;
	private BlockingQueue<T> busy;
	private int maxSize = 128;
	private AtomicInteger created = new AtomicInteger(0);
	private AtomicBoolean closed = new AtomicBoolean(false);

	public ObjectPool(int maxSize) {
		this.maxSize = maxSize;
		idle = new ArrayBlockingQueue<>(maxSize);
		busy = new ArrayBlockingQueue<>(maxSize, false);
	}

	public int getIdleSize() {
		return idle.size();
	}

	public int getBusySize() {
		return busy.size();
	}

	public int getCreatedSize() {
		return created.get();
	}

	public boolean isClosed() {
		return closed.get();
	}

	public int getMaxSize() {
		return maxSize;
	}

	public Future<PooledObject<T>> getAsync() throws Exception {
		if (isClosed()) {
			throw new Exception("already closed.");
		}

		return null;
	}

	public PooledObject<T> get() throws Exception {
		return get(0);
	}

	public PooledObject<T> get(long timeout) throws Exception {
		if (isClosed()) {
			throw new Exception("already closed.");
		}

		long now = System.currentTimeMillis();

		for (;;) {
			T obj = idle.poll();

			if (obj != null) {
				if (isValid(obj)) {
					if (!busy.offer(obj)) {
						log.debug("polled object that can not trace.");
					}
					return new PooledObject<>(obj, this);
				} else {
					destroy(obj);
				}
			}

			if (created.get() < maxSize) {
				if (created.addAndGet(1) > maxSize) {
					created.decrementAndGet();
				} else {
					T newObj = newObject();

					if (!busy.offer(newObj)) {
						log.debug("generated object that can not trace.");
					}

					return new PooledObject<>(newObj, this);

				}
			}

			if (System.currentTimeMillis() - now > timeout) {
				throw new Exception("timeout.");
			}

		}

	}

	public void close(T obj) {
		if (obj == null) {
			return;
		}

		if (isClosed()) {
			release(obj);
			return;
		}

		if (!busy.remove(obj)) {
			release(obj);
		}

		if (!idle.offer(obj)) {
			release(obj);
		}

	}

	public void release(T obj) {
		if (obj == null) {
			return;
		}

		created.addAndGet(-1);
		busy.remove(obj);

		destroy(obj);
	}

	public void close() {
		if (closed.getAndSet(true)) {
			log.trace("already closed.");
			return;
		}

		BlockingQueue<T> queue = idle;
		if (idle.size() == 0) {
			queue = busy;
		}

		while (queue.size() > 0) {
			try {
				T obj = queue.poll(1000, TimeUnit.MILLISECONDS);
				while (obj != null) {
					release(obj);

					if (queue.size() > 0) {
						obj = queue.poll(1000, TimeUnit.MILLISECONDS);
					} else {
						break;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (queue.size() == 0 && queue != busy) {
				queue = busy;
			}
		}
	}

	abstract T newObject() throws Exception;

	abstract void destroy(T obj);

	abstract boolean isValid(T obj);

}
