package org.barracuda.core.game.v317.serializer;

import java.util.List;

import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.MessageBuilder;
import org.barracuda.core.net.message.Serializer;
import org.barracuda.core.net.message.Serializes;
import org.barracuda.model.location.Region;

import io.netty.buffer.ByteBufAllocator;

@Serializes(Region.class)
public class RegionSerializer implements Serializer<Region> {

	@Override
	public void serialize(Region input, ByteBufAllocator allocator, List<Message> out) {
		out.add(new MessageBuilder(allocator).opcode(73).writeShort(input.getX()).writeShort(input.getY()).build());
	}

}
