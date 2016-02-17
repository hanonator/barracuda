package org.barracuda.core.net.netty;

import org.barracuda.core.net.netty.codec.MessageDecoder;
import org.barracuda.core.net.netty.codec.MessageEncoder;
import org.barracuda.core.net.netty.codec.PropertyDecoder;
import org.barracuda.core.net.netty.codec.SerializableEncoder;
import org.barracuda.core.net.netty.codec.SerializerEncoder;
import org.barracuda.horvik.Horvik;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

@Sharable
class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

	/**
	 * The service this initializer is bound to
	 */
	private final NettyService service;

	/**
	 * The horvik container
	 */
	private final Horvik horvik;

	/**
	 * Constructor
	 * 
	 * @param service
	 */
	public NettyChannelInitializer(NettyService service, Horvik horvik) {
		this.service = service;
		this.horvik = horvik;
	}

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline()
				/*
				 * Decodes the raw data into Message objects ready to be parsed
				 * into entities so they can be distributed to the correct
				 * listener
				 */
				.addLast("serializable", SerializableEncoder.INSTANCE)

				/*
				 * Decodes the raw data into Message objects ready to be parsed
				 * into entities so they can be distributed to the correct
				 * listener
				 */
				.addLast("intercepter-encode", SerializerEncoder.INSTANCE)

				/*
				 * Decodes the raw data into Message objects ready to be parsed
				 * into entities so they can be distributed to the correct
				 * listener
				 */
				.addLast("message-encode", MessageEncoder.INSTANCE)

				/*
				 * Decodes the raw data into Message objects ready to be parsed
				 * into entities so they can be distributed to the correct
				 * listener
				 */
				.addLast("message-decode", MessageDecoder.INSTANCE)

				/*
				 * Decodes the raw data into Message objects ready to be parsed
				 * into entities so they can be distributed to the correct
				 * listener
				 */
				.addLast("property-decode", PropertyDecoder.INSTANCE)

				/*
				 * The handler that distributes the game events to the correct
				 * listeners
				 */
				.addLast("channel", new NettyChannel(channel, service, horvik))

				/*
				 * Disconnect channels that have been idle for 30 seconds or
				 * more
				 */
				.addLast("timeout", new IdleStateHandler(30, 0, 0));
	}

}