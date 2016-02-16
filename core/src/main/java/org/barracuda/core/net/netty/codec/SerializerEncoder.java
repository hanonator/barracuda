package org.barracuda.core.net.netty.codec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.message.Serializer;
import org.barracuda.core.net.message.Serializes;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.util.ReflectionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class SerializerEncoder extends MessageToMessageEncoder<Object> {

	/**
	 * The static instance of this class
	 */
	public static final ChannelHandler INSTANCE = new SerializerEncoder();
	
	/**
	 * The static logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(SerializerEncoder.class);

	/**
	 * The collection of serializer
	 */
	private static final Map<Class<?>, Serializer<?>> serializers = new HashMap<>();

	/**
	 * Initializes the serializer
	 * 
	 * @param event
	 * @param container
	 */
	public static void initializeSerializers(@Observes ContainerInitialized event, HorvikContainer container) {
		container.getTypesAnnotatedWith(Serializes.class).forEach(type -> {
			serializers.put(type.getAnnotation(Serializes.class).value(), ReflectionUtil.createForcedType(type, Serializer.class));
			logger.info("Serializer {} listening to {}", type.getName(), type.getAnnotation(Serializes.class).value());
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
		Serializer<Object> serializer = (Serializer<Object>) serializers.get(msg.getClass());
		 if (serializer != null) {
			 out.add(serializer.serialize(msg, ctx.alloc()));
		 }
	}

}
