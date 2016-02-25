package org.barracuda.core.net.message.resolve;

import static org.joox.JOOX.$;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.event.Authentication;
import org.barracuda.core.net.event.Handshake;
import org.barracuda.core.net.event.JagGrabFileRequest;
import org.barracuda.core.net.interceptor.Authenticator;
import org.barracuda.core.net.interceptor.Handshaker;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.interceptor.Intercepts;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.definition.AttributeDefinition;
import org.barracuda.core.net.message.definition.AttributeType;
import org.barracuda.core.net.message.definition.Definition;
import org.barracuda.core.net.message.game.GameHeader.MetaData;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.context.Service;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.horvik.util.ReflectionUtil;

@Service
public class MessageRepository {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(MessageRepository.class);

	/**
	 * The system classloader
	 */
	private static final ClassLoader class_loader = ClassLoader.getSystemClassLoader();

	/**
	 * The collection of definitions of mappers for bean properties 
	 */
	private final Map<Integer, MessageDefinition> definitions = new HashMap<>();

	/**
	 * The intercepter responsible for handling the handshake protocol
	 */
	private final AtomicReference<Interceptor<Message, Handshake>> handshakeInterceptor = new AtomicReference<>();

	/**
	 * The intercepter responsible for handling the authentication protocol
	 */
	private final AtomicReference<Interceptor<Message, Authentication>> authenticationInterceptor = new AtomicReference<>();

	/**
	 * The intercepter responsible for handling the JAGGRAB protocol
	 */
	private final AtomicReference<Interceptor<Message, JagGrabFileRequest>> jaggrabInterceptor = new AtomicReference<>();

	/**
	 * 
	 * @param container
	 */
	@SuppressWarnings("unchecked")
	public void initialize(@Observes ContainerInitialized initialized, HorvikContainer container) throws Exception {
		container.getTypesAnnotatedWith(Definition.class).forEach(type -> {
			Definition definition = type.getAnnotation(Definition.class);
			
			if (!definitions.containsKey(definition.opcode())) {
				definitions.put(definition.opcode(), new MessageDefinition(definition.opcode(), definition.length(),
						definition.meta(), new ReflectionDecoder(definition.attributes(), type)));
				logger.info("Message mapping: '{}' to '{}'", type.getSimpleName(), definition.opcode());
			}
			else {
				logger.warn("duplicate key {}" + definition.opcode());
			}
		});
		
		container.getTypesAnnotatedWith(Intercepts.class).forEach(type -> {
			Intercepts definition = type.getAnnotation(Intercepts.class);
			
			if (!definitions.containsKey(definition.opcode())) {
				definitions.put(definition.opcode(), new MessageDefinition(definition.opcode(), definition.length(),
						definition.meta(), new InterceptorDecoder<>(ReflectionUtil.createForcedType(type, Interceptor.class))));
				logger.info("Interceptor mapping: '{}' to '{}'", type.getName(), definition.opcode());
			}
			else {
				logger.warn("duplicate key {}" + definition.opcode());
			}
		});
		
		/*
		 * Load the decorators from the xml file
		 */
		$($(ClassLoader.getSystemResourceAsStream("static/protocol/decorators.xml")).document()).find("decorator").forEach(node -> {
			try {
				String target = node.getAttribute("target");
				int opcode = Integer.parseInt(node.getAttribute("opcode"));
				int length = node.hasAttribute("length") ? Integer.parseInt(node.getAttribute("length")) : 0;
				MetaData meta = node.hasAttribute("meta") ? MetaData.valueOf(node.getAttribute("meta")) : MetaData.EMPTY;
				
				Queue<AttributeDefinition> attributes = new LinkedList<>();
				$(node).find("attribute").forEach(inner_node -> {
					String attribute_name = inner_node.getTextContent();
					String attribute_type = inner_node.getAttribute("type");
					
					attributes.add(new AttributeDefinition(attribute_name, AttributeType.valueOf(attribute_type)));
				});
				definitions.put(opcode, new MessageDefinition(opcode, length, meta,
						new ReflectionDecoder(attributes.toArray(new AttributeDefinition[0]), class_loader.loadClass(target))));
				logger.info("Message mapping: '{}' to '{}'", target, opcode);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		/*
		 * FIXME: Load from configuration files
		 */
		handshakeInterceptor.set(ReflectionUtil.createForcedType(container.getTypeAnnotatedWith(Handshaker.class), Interceptor.class));
		authenticationInterceptor.set(ReflectionUtil.createForcedType(container.getTypeAnnotatedWith(Authenticator.class), Interceptor.class));
	}

	/**
	 * Gets the definition for the opcode
	 * @param opcode 
	 * @return the definition
	 */
	public MessageDefinition get(int opcode) {
		return definitions.get(opcode);
	}

	/**
	 * @return the handshake intercepter
	 */
	public Interceptor<Message, Handshake> getHandshakeInterceptor() {
		return handshakeInterceptor.get();
	}

	/**
	 * @return the authentication intercepter
	 */
	public Interceptor<Message, Authentication> getAuthenticationInterceptor() {
		return authenticationInterceptor.get();
	}

	/**
	 * @return the jaggrab interceptor
	 */
	public Interceptor<Message, JagGrabFileRequest> getJaggrabInterceptor() {
		return jaggrabInterceptor.get();
	}

}
