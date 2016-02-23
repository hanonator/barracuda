package org.barracuda.model.item;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 
 * @author brock
 *
 */
public abstract class Container implements Iterable<Item>, Supplier<Stream<Item>> {

	/**
	 * The size of the container
	 */
	private final int size;

	/**
	 * The items in the container
	 */
	private final Item[] items;

	/**
	 * 
	 * @param capacity
	 */
	public Container(int capacity) {
		this.size = capacity;
		this.items = new Item[capacity];
	}
	
	/**
	 * 
	 * @param items
	 */
	public Container(Item[] items) {
		this.items = Arrays.copyOf(items, items.length);
		this.size = items.length;
	}

	/**
	 * 
	 * @param item
	 */
	public void add(Item item) {
		if (item.getDefinition().isStackable() && count(item.getId()) > 0) {
			add(item, indexOf(item.getId()));
		}
		else if (remaining() > 0) {
			while (item.getAmount() > 0 && remaining() > 0) {
				add(item, indexOf(-1));
			}
		}
	}
	
	/**
	 * 
	 * @param item
	 * @param slot
	 */
	public void add(Item item, int slot) {
		if (items[slot] == null) {
			if (item.getDefinition().isStackable()) {
				items[slot] = new Item(item.getId(), item.getAmount());
				item.setAmount(0);
			} else {
				items[slot] = new Item(item.getId(), 1);
				item.setAmount(item.getAmount() - 1);
			}
		}
		else if(items[slot].getId() == item.getId() && item.getDefinition().isStackable()) {
			items[slot] = new Item(item.getId(), items[slot].getAmount() + item.getAmount());
		}
		else {
			throw new IllegalStateException("item could not be added");
		}
	}
	
	/**
	 * 
	 * @param item
	 */
	public void remove(Item item) {
		while (item.getAmount() > 0 && count(item.getId()) > 0) {
			remove(item, indexOf(item.getId()));
		}
	}
	
	/**
	 * 
	 * @param item
	 * @param slot
	 */
	public void remove(Item item, int slot) {
		if (items[slot] != null && items[slot].getId() == item.getId()) {
			if (items[slot].getAmount() > item.getAmount()) {
				items[slot].setAmount(items[slot].getAmount() - item.getAmount());
			} else {
				int amount = items[slot].getAmount();
				items[slot] = null;
				item.setAmount(item.getAmount() - amount);
			}
		}
	}
	
	/**
	 * Gets the item at the given slot
	 * 
	 * @param slot
	 * @return
	 */
	public Item get(int slot) {
		return items[slot];
	}

	/**
	 * 
	 */
	public void clear() {
		for (int index = 0; index < size; index++) {
			items[index] = null;
		}
	}

	/**
	 * 
	 * @param origin
	 * @param destination
	 */
	public void swap(int origin, int destination) {
		Item cache = items[origin];
		items[origin] = items[destination];
		items[destination] = cache;
	}

	/**
	 * 
	 * @return
	 */
	public long remaining() {
		return Arrays.stream(items).filter(item -> item == null).count();
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public long count(int index) {
		return Arrays.stream(items).filter(item -> item != null && item.getId() == index).mapToInt(item -> item.getAmount()).sum();
	}

	/**
	 * Returns the first occurance of the given id
	 * 
	 * @param id
	 * @return
	 */
	public int indexOf(int id) {
		for (int i = 0; i < size; i++) {
			if (id == -1 && items[i] == null) {
				return i;
			} else if (items[i] != null && items[i].getId() == id) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Checks to see if an item is present
	 * 
	 * @param unidentified_id
	 * @return
	 */
	public boolean contains(int id) {
		return count(id) > 0;
	}

	/**
	 * Capacity of the container
	 * @return
	 */
	public int size() {
		return size;
	}

	@Override
	public Iterator<Item> iterator() {
		return Collections.unmodifiableList(Arrays.asList(items)).iterator();
	}

	@Override
	public Stream<Item> get() {
		return Arrays.stream(items);
	}
	
}