package org.barracuda.core.net.netty;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.Channel;
import org.barracuda.core.net.ChannelState;
import org.barracuda.core.net.event.PlayerDisconnected;
import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.Session;
import org.barracuda.horvik.context.session.SessionScoped;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * Represents a networking channel
 * 
 * @author brock
 *
 */
@SessionScoped
@Discoverable
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
	@SuppressWarnings("unused")
	private final NettyService service;

	/**
	 * The session for this channel
	 */
	private final Session session = new Session(UUID.randomUUID().toString());
	
	/**
	 * The container
	 */
	private final Horvik horvik;

	/**
	 * Creates a new session for the given channel. It is assumed the given
	 * channel is the channel this instance is a handler for
	 * 
	 * @param channel
	 */
	public NettyChannel(SocketChannel channel, NettyService service, Horvik horvik) {
		this.horvik = horvik;
		this.channel = channel;
		this.service = service;
		this.session.associate(horvik.getContainer().getBean(Session.class), session);
		this.session.associate(horvik.getContainer().getBean(Channel.class), this);
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channel {} - connection opened", ctx.channel().remoteAddress());
		ctx.attr(ChannelState.ATTRIBUTE_KEY).set(ChannelState.HANDSHAKE);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("channel {} - dispatching object {}", ctx.channel().remoteAddress(), msg.getClass());
		read(msg);
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		if (ChannelState.GAME == ctx.attr(ChannelState.ATTRIBUTE_KEY).get()) {
			read(new PlayerDisconnected());
		}
		logger.debug("channel {} - connection closed", ctx.channel().remoteAddress());
		ctx.attr(ChannelState.ATTRIBUTE_KEY).set(ChannelState.DISCONNECTED);
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warn("channel {} - error occured: {}", ctx.channel().remoteAddress(), cause.getMessage());
		ctx.attr(ChannelState.ATTRIBUTE_KEY).set(ChannelState.DISCONNECTED);
		cause.printStackTrace();
	}
	@Override
	public <T> Attribute<T> attr(AttributeKey<T> key) {
		return channel.attr(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void read(Object object) {
		horvik.getEvent().select((Class<? super Object>) object.getClass()).fire(object, session);
	}

	@Override
	public void write(Object object) {
		channel.write(object);
	}

	@Override
	public void flush() {
		channel.flush();
	}
	
	@Override
	public void close() {
		channel.close();
	}
}