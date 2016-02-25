package org.barracuda.model;

import java.lang.reflect.Array;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 
 * @author brock
 *
 * @param <T>
 */
public class EntityList<T extends Entity> implements Iterable<T>, Supplier<Stream<T>> {

	/**
	 * The size of the list
	 */
	private final int size;
	
	/**
	 * The array object
	 */
	private final Object entities;
	
	/**
	 * The pool of index
	 */
	private final IndexPool pool;
	
	/**
	 * The type of entity in this collection
	 */
	private final Class<T> type;

	/**
	 * Constructor
	 * 
	 * @param size
	 */
	public EntityList(int size, Class<T> type) {
		this.size = size;
		this.type = type;
		this.pool = new IndexPool(size);
		this.entities = Array.newInstance(type, size);
	}

	/**
	 * Registers the entity
	 * 
	 * @param entity
	 */
	public EntityList<T> register(T entity) {
		entity.setIndex(pool.claim());
		add(entity);
		return this;
	}

	/**
	 * Unregisters the entity
	 * 
	 * @param player
	 */
	public EntityList<T> unregister(T entity) {
		pool.release(entity.getIndex());
		remove(entity);
		return this;
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void add(T entity) {
		Array.set(entities, entity.getIndex(), entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void remove(T entity) {
		Array.set(entities, entity.getIndex(), null);
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public T get(int index) {
		return type.cast(Array.get(entities, index));
	}

	/**
	 * @return the size
	 */
	public int size() {
		return toSet().size();
	}

	/**
	 * @return the size
	 */
	public int capacity() {
		return size;
	}

	/**
	 * Converts the array object to a set
	 * 
	 * @return
	 */
	private Set<T> toSet() {
		Set<T> set = new HashSet<>();
		for (int i = 0; i < size; i++) {
			if (Array.get(entities, i) != null) {
				set.add(type.cast(Array.get(entities, i)));
			}
		}
		return set;
	}

	/**
	 * @return the pool
	 */
	public boolean full() {
		return pool.size() == 0;
	}

	/**
	 * 
	 * @param other_player
	 * @return
	 */
	public boolean contains(T other) {
		return toSet().stream().anyMatch(entity -> entity.getIndex() == other.getIndex());
	}

	@Override
	public Stream<T> get() {
		return toSet().stream();
	}

	@Override
	public Iterator<T> iterator() {
		return toSet().iterator();
	}

	/**
	 * 
	 * @author brock
	 *
	 */
	private static class IndexPool {
		
		/**
		 * The pool of index
		 */
		private final Deque<Integer> pool;
		
		/**
		 * Constructor
		 * 
		 * @param size
		 */
		public IndexPool(int size) {
			this.pool = new LinkedList<>();
			for (int i = 1; i < size; i++) {
				this.pool.add(i);
			}
		}

		/**
		 * 
		 * @param index
		 */
		public void release(int index) {
			pool.addFirst(index);
		}
		
		/**
		 * 
		 * @return
		 */
		public int claim() {
			return pool.pop();
		}
		
		/**
		 * 
		 * @return
		 */
		public int size() {
			return pool.size();
		}
		
	}

}
