package org.barracuda.core.net.netty.codec;

import static org.joox.JOOX.$;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.definition.AttributeDefinition;
import org.barracuda.core.net.message.definition.AttributeType;
import org.barracuda.core.net.message.game.GameHeader.MetaData;
import org.barracuda.core.net.message.serialize.ReflectionSerializer;
import org.barracuda.core.net.message.serialize.Serializer;
import org.barracuda.core.net.message.serialize.Serializes;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.util.ReflectionUtil;
import org.xml.sax.SAXException;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

@Sharable
public class SerializerEncoder extends MessageToMessageEncoder<Object> {

	/**
	 * The static instance of this class
	 */
	public static final ChannelHandler INSTANCE = new SerializerEncoder();

	/**
	 * The system classloader
	 */
	private static final ClassLoader class_loader = ClassLoader.getSystemClassLoader();
	
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
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void initializeSerializers(@Observes ContainerInitialized event, HorvikContainer container) throws SAXException, IOException {
		container.getTypesAnnotatedWith(Serializes.class).forEach(type -> {
			serializers.put(type.getAnnotation(Serializes.class).value(), ReflectionUtil.createForcedType(type, Serializer.class));
			logger.info("Serializer {} listening to {}", type.getName(), type.getAnnotation(Serializes.class).value());
		});
		
		/*
		 * Load the serializers from the xml file
		 */
		$($(ClassLoader.getSystemResourceAsStream("static/protocol/serializers.xml")).document()).find("serializer").forEach(node -> {
			try {
				Class<?> type = class_loader.loadClass(node.getAttribute("type"));
				int opcode = Integer.parseInt(node.getAttribute("opcode"));
				MetaData meta = node.hasAttribute("meta") ? MetaData.valueOf(node.getAttribute("meta")) : MetaData.EMPTY;
				
				Queue<AttributeDefinition> attributes = new LinkedList<>();
				$(node).find("attribute").forEach(inner_node -> {
					String attribute_name = inner_node.getTextContent();
					String attribute_type = inner_node.getAttribute("type");
					
					attributes.add(new AttributeDefinition(attribute_name, AttributeType.valueOf(attribute_type)));
				});
				serializers.put(type, new ReflectionSerializer(type, meta, opcode, attributes));
				logger.info("Serializer -> '{}' to '{}'", type, opcode);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
		Serializer<Object> serializer = (Serializer<Object>) serializers.get(msg.getClass());
		List<Message> messages = new ArrayList<>();
		if (serializer != null) {
			serializer.serialize(msg, ctx.alloc(), messages);
			out.addAll(messages);
		}
		else {
			out.add(msg);
		}
	}

}
