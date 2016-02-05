package org.barracuda.core.net;

import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.SessionScoped;

import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

@Discoverable
@SessionScoped
public interface Channel {

	/**
	 * Pushes an object to the read stack of the channel
	 * 
	 * @param object
	 */
	void read(Object object);
	
	/**
	 * Writes an object to the channel
	 * 
	 * @param object
	 */
	void write(Object object);

	/**
	 * Flushes the channel
	 */
	void flush();
	
	/**
	 * Closes the channel
	 */
	void close();
	
	/**
	 * Proxy method. TODO: This is implementation dependant on JBoss Netty
	 * @return
	 */
	<T> Attribute<T> attr(AttributeKey<T> key);

	/**
	 * Writes an object to the channel and immediately flushes
	 * 
	 * @param object
	 */
	default void writeAndFlush(Object object) {
		write(object);
		flush();
	}

}
