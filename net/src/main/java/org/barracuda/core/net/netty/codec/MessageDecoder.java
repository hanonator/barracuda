package org.barracuda.core.net.netty.codec;

import java.util.List;

import org.barracuda.core.net.ChannelState;
import org.barracuda.core.net.message.ByteBufPayload;
import org.barracuda.core.net.message.Header;
import org.barracuda.core.net.message.Payload;
import org.barracuda.core.net.message.game.GameHeader;
import org.barracuda.core.net.message.game.GameHeader.MetaData;
import org.barracuda.core.net.message.game.GameMessage;
import org.barracuda.core.net.message.resolve.MessageDefinition;
import org.barracuda.core.net.message.resolve.MessageRepository;
import org.barracuda.horvik.Horvik;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * Decodes the raw byte buffer into messages
 * 
 * @author brock
 *
 */
@Sharable
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

	/**
	 * The horvik instance
	 */
	private final Horvik horvik;

	/**
	 * Constructor
	 * 
	 * @param horvik
	 */
	public MessageDecoder(Horvik horvik) {
		this.horvik = horvik;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		if (ChannelState.GAME != ctx.attr(ChannelState.ATTRIBUTE_KEY).get()) {
			Payload payload = new ByteBufPayload(msg.readBytes(msg.readableBytes()));
			Header header = new GameHeader(-1, msg.readableBytes(), MetaData.EMPTY);
			out.add(new GameMessage(header, payload));
		} else {
			while (msg.isReadable()) {
				int opcode = msg.readUnsignedByte();
				MessageDefinition definition = horvik.getContainer().getService(MessageRepository.class).get(opcode);
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
	
				Payload payload = new ByteBufPayload(msg.readBytes(length));
				Header header = new GameHeader(opcode, length, meta);
				out.add(new GameMessage(header, payload));
//				System.out.print(opcode == 0 ? "" : "message: " + opcode + "\n");
			}
		}
	}

}
