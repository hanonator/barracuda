package org.barracuda.core.net.netty.codec;

import java.util.List;

import org.barracuda.core.net.message.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class MessageEncoder extends MessageToMessageEncoder<Message> {

	/**
	 * The static instance of this class
	 */
	public static final ChannelHandler INSTANCE = new MessageEncoder();

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
		ByteBuf buffer = ctx.alloc().buffer();
		buffer.writeBytes(msg.getHeader().serialize(ctx.alloc()));
		buffer.writeBytes(msg.getPayload().getBuffer());
		out.add(buffer);
	}

}
