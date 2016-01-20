package org.barracuda.core.net.netty;

import org.barracuda.core.net.Channel;
import org.horvik.session.Session;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

/**
 * Represents a networking channel
 * 
 * @author brock
 *
 */
public class NettyChannel extends ChannelHandlerAdapter implements Channel {

	/**
	 * The netty channel for this class. Handles the networking
	 */
	private final SocketChannel channel;
	
	/**
	 * The service which this channel is bound to
	 */
	private final NettyService service;

	/**
	 * The session for this channel
	 */
	private final Session session;

	/**
	 * Creates a new session for the given channel. It is assumed the given
	 * channel is the channel this instance is a handler for
	 * 
	 * @param channel
	 */
	public NettyChannel(SocketChannel channel, NettyService service) {
		this.channel = channel;
		this.service = service;
		this.session = new Session();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void read(Object object) {
		
	}

	@Override
	public void write(Object object) {
		
	}

	@Override
	public void flush() {
		
	}

}