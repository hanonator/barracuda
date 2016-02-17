package org.barracuda.core.net.netty.codec;

import java.util.List;

import org.barracuda.core.net.message.Serializable;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

@Sharable
public class SerializableEncoder extends MessageToMessageEncoder<Serializable> {

	/**
	 * The static instance of this class
	 */
	public static final ChannelHandler INSTANCE = new SerializableEncoder();

	@Override
	protected void encode(ChannelHandlerContext ctx, Serializable msg, List<Object> out) throws Exception {
		out.add(msg.serialize(ctx.alloc()));
	}

}
