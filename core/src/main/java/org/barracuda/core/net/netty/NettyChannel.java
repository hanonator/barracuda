package org.barracuda.core.net.netty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.TestRequest;
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
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(NettyChannel.class);

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
		logger.debug("channel {} registered", ctx.channel().remoteAddress());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("channel {} read object {}", ctx.channel().remoteAddress(), msg.getClass());
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channel {} unregistered", ctx.channel().remoteAddress());
	}
	
	@Override
	public void read(Object object) {
		
	}

	@Override
	public void write(Object object) {
		channel.write(object);
	}

	@Override
	public void flush() {
		channel.flush();
	}

}