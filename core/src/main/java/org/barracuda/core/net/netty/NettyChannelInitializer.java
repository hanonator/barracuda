package org.barracuda.core.net.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

	/**
	 * 
	 */
	private final NettyService service;

	public NettyChannelInitializer(NettyService service) {
		this.service = service;
	}

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline()

				/*
				 * The handler that distributes the game events to the correct
				 * listeners
				 */
				.addLast("session", new NettyChannel(channel, service))

				/*
				 * Disconnect channels that have been idle for 30 seconds or
				 * more
				 */
				.addLast("timeout", new IdleStateHandler(30, 0, 0));
	}

}