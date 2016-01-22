package org.barracuda.core.net.netty.codec;

import java.util.List;

import org.barracuda.core.Application;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.resolve.MessageDefinition;
import org.barracuda.core.net.message.resolve.MessageRepository;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

@Sharable
public class PropertyCodec extends MessageToMessageCodec<Message, Object> {

	/**
	 * The static instance of this class
	 */
	public static final ChannelHandler INSTANCE = new PropertyCodec();

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
		
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
		MessageDefinition definition = Application.getContainer().getService(MessageRepository.class).get(msg.getHeader().opcode());
		if (definition != null && msg.getHeader().opcode() == definition.getOpcode()) {
			System.out.println(definition.getDecoder().decode(msg));
		}
	}

}
