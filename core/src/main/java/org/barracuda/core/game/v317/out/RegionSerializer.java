package org.barracuda.core.game.v317.out;

import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.MessageBuilder;
import org.barracuda.core.net.message.Serializer;
import org.barracuda.core.net.message.Serializes;
import org.barracuda.model.location.Region;

import io.netty.buffer.ByteBufAllocator;

@Serializes(Region.class)
public class RegionSerializer implements Serializer<Region> {

	@Override
	public Message serialize(Region input, ByteBufAllocator allocator) {
		return new MessageBuilder(allocator).opcode(73).writeShort(input.getX()).writeShort(input.getY()).build();
	}

}
