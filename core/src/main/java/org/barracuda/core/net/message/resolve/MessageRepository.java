package org.barracuda.core.net.message.resolve;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.game.v317.login.AuthenticationInterceptor;
import org.barracuda.core.game.v317.login.HandshakeInterceptor;
import org.barracuda.core.game.v317.login.JagGrabInterceptor;
import org.barracuda.core.game.v317.login.model.Authentication;
import org.barracuda.core.game.v317.login.model.Handshake;
import org.barracuda.core.game.v317.login.model.JagGrabFileRequest;
import org.barracuda.core.net.interceptor.Interceptor;
import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.definition.Definition;
import org.barracuda.horvik.HorvikContainer;
import org.barracuda.horvik.context.Service;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;

@Service
public class MessageRepository {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(MessageRepository.class);

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
		
		/*
		 * FIXME: Load from configuration files
		 */
		handshakeInterceptor.set(new HandshakeInterceptor());
		authenticationInterceptor.set(new AuthenticationInterceptor());
		jaggrabInterceptor.set(new JagGrabInterceptor());
	}

	/**
	 * 
	 * @param opcode
	 */
	public MessageDefinition get(int opcode) {
		return definitions.get(opcode);
	}

	public Interceptor<Message, Handshake> getHandshakeInterceptor() {
		return handshakeInterceptor.get();
	}

	public Interceptor<Message, Authentication> getAuthenticationInterceptor() {
		return authenticationInterceptor.get();
	}

	public Interceptor<Message, JagGrabFileRequest> getJaggrabInterceptor() {
		return jaggrabInterceptor.get();
	}

}
