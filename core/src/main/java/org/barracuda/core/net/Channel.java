package org.barracuda.core.net;

public interface Channel {

	/**
	 * 
	 * @param object
	 */
	void read(Object object);
	
	/**
	 * 
	 * @param object
	 */
	void write(Object object);

	/**
	 * 
	 */
	void flush();

	/**
	 * 
	 * @param object
	 */
	default void writeAndFlush(Object object) {
		write(object);
		flush();
	}

}
