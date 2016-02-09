package org.barracuda.model.realm;

import java.lang.reflect.Array;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.barracuda.model.Entity;

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
	public int getSize() {
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
	public IndexPool getIndexPool() {
		return pool;
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
	public static class IndexPool {
		
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
			for (int i = 0; i < size; i++) {
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
		
	}

}
