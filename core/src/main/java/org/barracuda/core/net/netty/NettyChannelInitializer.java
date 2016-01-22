package org.barracuda.core.net.netty;

import org.barracuda.core.net.netty.codec.MessageCodec;
import org.barracuda.core.net.netty.codec.PropertyCodec;

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
				 * Decodes the raw data into Message objects ready to be parsed
				 * into entities so they can be distributed to the correct
				 * listener
				 */
				.addLast("message", MessageCodec.INSTANCE)

				/*
				 * Decodes the raw data into Message objects ready to be parsed
				 * into entities so they can be distributed to the correct
				 * listener
				 */
				.addLast("property", PropertyCodec.INSTANCE)

				/*
				 * The handler that distributes the game events to the correct
				 * listeners
				 */
				.addLast("channel", new NettyChannel(channel, service))

				/*
				 * Disconnect channels that have been idle for 30 seconds or
				 * more
				 */
				.addLast("timeout", new IdleStateHandler(30, 0, 0));
	}

}