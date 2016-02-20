package org.barracuda.core.net.message.serialize;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.MessageBuilder;
import org.barracuda.core.net.message.definition.AttributeDefinition;
import org.barracuda.core.net.message.game.GameHeader.MetaData;

import io.netty.buffer.ByteBufAllocator;

public class ReflectionSerializer implements Serializer<Object> {
	
	/**
	 * The type of object being serialized
	 */
	private final Class<?> type;
	
	/**
	 * The meta data of the serialized object
	 */
	private final MetaData meta;
	
	/**
	 * The opcode of the serialized object
	 */
	private final int opcode;

	/**
	 * The collection of decoders for the given object
	 */
	private final Queue<AttributeDefinition> definitions;

	/**
	 * Constructor
	 * 
	 * @param type
	 * @param meta
	 * @param opcode
	 */
	public ReflectionSerializer(Class<?> type, MetaData meta, int opcode, Queue<AttributeDefinition> definitions) {
		this.type = type;
		this.meta = meta;
		this.opcode = opcode;
		this.definitions = definitions;
	}

	@Override
	public void serialize(Object input, ByteBufAllocator allocator, List<Message> out) throws Exception {
		MessageBuilder builder = new MessageBuilder(allocator).header(opcode, meta);
		
		for (Iterator<AttributeDefinition> iterator = definitions.iterator(); iterator.hasNext(); ) {try{
			AttributeDefinition definition = iterator.next();
			Field field = type.getDeclaredField(definition.getField());
			field.setAccessible(true);
			definition.getType().insert(field.get(input), builder);}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		out.add(builder.build());
	}

	/**
	 * 
	 * @author koga
	 *
	 */
	public static class ReflectionSerializerDefinition {
		
	}

}
