package org.barracuda.core.net.netty.codec;

import java.util.List;

import org.barracuda.core.net.ChannelState;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.resolve.InterceptorDecoder;
import org.barracuda.core.net.message.resolve.MessageDecoder;
import org.barracuda.core.net.message.resolve.MessageDefinition;
import org.barracuda.core.net.message.resolve.MessageRepository;
import org.barracuda.horvik.Horvik;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * TODO: Implement default decoders for the login sequence and JAGGrab protocol
 * 
 * @author brock
 */
@Sharable
public class PropertyDecoder extends MessageToMessageDecoder<Message> {

	/**
	 * The horvik instance
	 */
	private final Horvik horvik;

	/**
	 * Constructor
	 * 
	 * @param horvik
	 */
	public PropertyDecoder(Horvik horvik) {
		this.horvik = horvik;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
		MessageRepository repository = horvik.getContainer().getService(MessageRepository.class);
		
		/*
		 * Check to see if the message that has been received has a message definition that is capable of
		 * decoding it
		 */
		MessageDefinition definition = repository.get(msg.getHeader().opcode());
		MessageDecoder decoder = definition != null ? definition.getDecoder() : null;
		
		/*
		 * If the user is in the Handshake state, use the handshake interceptor
		 */
		if (ChannelState.HANDSHAKE == ctx.attr(ChannelState.ATTRIBUTE_KEY).get()) {
			decoder = new InterceptorDecoder<>(repository.getHandshakeInterceptor());
		}
		
		/*
		 * If the user is in authentication state, use the authentication interceptor
		 */
		else if (ChannelState.AUTHENTICATION == ctx.attr(ChannelState.ATTRIBUTE_KEY).get()) {
			decoder = new InterceptorDecoder<>(repository.getAuthenticationInterceptor());
		}

		/*
		 * If a definition is found, add the decoded object to the stack
		 */
		if (decoder != null) {
			out.add(decoder.decode(msg));
		}
	}

}
