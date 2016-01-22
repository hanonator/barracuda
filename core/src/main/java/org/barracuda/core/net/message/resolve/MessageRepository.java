package org.barracuda.core.net.message.resolve;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.event.Observes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.core.net.message.definition.Definition;
import org.horvik.HorvikContainer;
import org.horvik.bean.service.Service;
import org.horvik.environment.ContainerInitialized;

@Service
public class MessageRepository {
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(MessageRepository.class);

	/**
	 * 
	 */
	private final Map<Integer, MessageDefinition> definitions = new HashMap<>();

	/**
	 * 
	 * @param container
	 */
	public void initialize(@Observes ContainerInitialized initialized, HorvikContainer container) throws Exception {
		container.getTypesAnnotatedWith(Definition.class).forEach(type -> {
			Definition definition = type.getAnnotation(Definition.class);
			definitions.put(definition.opcode(), new MessageDefinition(definition.opcode(), definition.length(),
					definition.meta(), new ReflectionDecoder(definition.attributes(), type)));
			logger.info("Message mapping: '{}' to '{}'", type.getSimpleName(), definition.opcode());
		});
	}

	/**
	 * 
	 * @param opcode
	 */
	public MessageDefinition get(int opcode) {
		return definitions.get(opcode);
	}

}
