package org.barracuda.core.net.netty.codec;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.Application;
import org.barracuda.core.net.ChannelState;
import org.barracuda.core.net.message.ByteBufferPayload;
import org.barracuda.core.net.message.Header;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.Payload;
import org.barracuda.core.net.message.game.GameHeader;
import org.barracuda.core.net.message.game.GameHeader.MetaData;
import org.barracuda.core.net.message.game.GameMessage;
import org.barracuda.core.net.message.resolve.MessageDefinition;
import org.barracuda.core.net.message.resolve.MessageRepository;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

/**
 * Decodes the raw byte buffer into messages
 * 
 * @author brock
 *
 */
@Sharable
public class MessageCodec extends MessageToMessageCodec<ByteBuf, Message> {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(MessageCodec.class);

	/**
	 * The static instance of this class
	 */
	public static final ChannelHandler INSTANCE = new MessageCodec();

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
		
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		if (ChannelState.GAME != ctx.attr(ChannelState.ATTRIBUTE_KEY).get()) {
			Payload payload = new ByteBufferPayload(msg.readBytes(msg.readableBytes()).nioBuffer());
			Header header = new GameHeader(-1, msg.readableBytes(), MetaData.EMPTY);
			out.add(new GameMessage(header, payload));
		} else {
			while (msg.isReadable()) {
				int opcode = msg.readUnsignedByte();
				MessageDefinition definition = Application.getContainer().getService(MessageRepository.class).get(opcode);
				MetaData meta = definition == null ? MetaData.EMPTY : definition.getMeta();
				int length = 0;
				
				switch (meta) {
				case EMPTY:
					length = definition == null ? 0 : definition.getLength();
					break;
				case SMALL:
					length = msg.readUnsignedByte();
					break;
				case BIG:
					length = msg.readUnsignedShort();
					break;
				}
	
				Payload payload = new ByteBufferPayload(msg.readBytes(length).nioBuffer());
				Header header = new GameHeader(opcode, length, MetaData.EMPTY);
				out.add(new GameMessage(header, payload));
				logger.debug("channel {} received message with opcode {}", ctx.channel().remoteAddress(), opcode);
			}
		}
	}

}
