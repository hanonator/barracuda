package org.barracuda.core.net.netty.codec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.game.GameSession;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.Serializes;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.util.ReflectionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class InterceptorEncoder extends MessageToMessageEncoder<Object> {

	/**
	 * The static instance of this class
	 */
	public static final ChannelHandler INSTANCE = new InterceptorEncoder();
	
	/**
	 * The static logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(InterceptorEncoder.class);

	/**
	 * The collection of interceptors
	 */
	private static final Map<Class<?>, Interceptor<?, Message>> interceptors = new HashMap<>();

	/**
	 * Initializes the interceptors
	 * 
	 * @param event
	 * @param container
	 */
	@SuppressWarnings("unchecked")
	public static void initializeIntercepters(@Observes ContainerInitialized event, HorvikContainer container) {
		container.getTypesAnnotatedWith(Serializes.class).forEach(type -> {
			interceptors.put(type.getAnnotation(Serializes.class).value(), ReflectionUtil.createForcedType(type, Interceptor.class));
			logger.info("Interceptor {} listening to {}", type.getName(), type.getAnnotation(Serializes.class).value());
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
		Interceptor<Object, Message> interceptor = (Interceptor<Object, Message>) interceptors.get(msg.getClass());
		 if (interceptor != null) {
			 out.add(interceptor.intercept(msg, ctx.attr(GameSession.ATTRIBUTE_KEY).get()));
		 }
	}

}
