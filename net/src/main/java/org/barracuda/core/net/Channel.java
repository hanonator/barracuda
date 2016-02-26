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
	<T> T read(T object);
	
	/**
	 * Writes an object to the channel
	 * 
	 * @param object
	 */
	<T> T write(T object);

	/**
	 * Flushes the channel
	 */
	void flush();
	
	/**
	 * Closes the channel
	 */
	void close();
	
	/**
	 * Proxy method. TODO: This is implementation dependent on JBoss Netty
	 * @return
	 */
	<T> Attribute<T> attr(AttributeKey<T> key);

	/**
	 * Writes an object to the channel and immediately flushes
	 * 
	 * @param object
	 */
	default <T> T writeAndFlush(T object) {
		write(object);
		flush();
		return object;
	}

}
