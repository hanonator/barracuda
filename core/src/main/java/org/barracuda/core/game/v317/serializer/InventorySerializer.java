package org.barracuda.core.game.v317.serializer;

import java.util.List;

import org.barracuda.core.net.message.Message;
import org.barracuda.core.net.message.MessageBuilder;
import org.barracuda.core.net.message.Serializer;
import org.barracuda.core.net.message.Serializes;
import org.barracuda.core.net.message.game.GameHeader.MetaData;
import org.barracuda.model.item.Inventory;
import org.barracuda.model.item.Item;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * TODO: Tidy up this class
 * 
 * @author brock
 */
@Serializes(Inventory.class)
public class InventorySerializer implements Serializer<Inventory> {

	@Override
	public void serialize(Inventory input, ByteBufAllocator allocator, List<Message> out) {
		ByteBuf item_buffer = allocator.buffer();
		input.forEach(item -> addItem(item, item_buffer));
		out.add(new MessageBuilder(allocator).header(53, MetaData.BIG)
				.writeShort(Inventory.INTERFACE).writeShort(Inventory.CAPACITY)
				.writeBytes(item_buffer)
				.build());
	}

	/**
	 * Adds an item to the tail end of the buffer
	 * 
	 * @param item
	 * @param buffer
	 */
	private void addItem(Item item, ByteBuf buffer) {
		if (item != null) {
			if (item.getAmount() > 254) {
				buffer.writeByte(255);
				buffer.writeInt(item.getAmount());
			} else {
				buffer.writeByte(item.getAmount());
			}
			buffer.writeShort(item.getId() + 1);
		}
		else {
			buffer.writeByte(0);
			buffer.writeShort(0);
		}
	}

}