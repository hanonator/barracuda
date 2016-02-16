package org.barracuda.core.game.v317.sync;

import org.barracuda.core.game.util.StringUtil;
import org.barracuda.model.actor.sync.attribute.Appearance;
import org.barracuda.model.actor.sync.attribute.Attribute;

import io.netty.buffer.ByteBuf;

public class AppearanceAttribute implements Attribute {

	/**
	 * 
	 */
	private static final int BITMAP_ID = 0x10;

	/**
	 * The appearance attribute
	 */
	private final Appearance appearance;

	/**
	 * Constructor
	 * 
	 * @param appearance
	 */
	public AppearanceAttribute(Appearance appearance) {
		this.appearance = appearance;
	}

	@Override
	public void serialize(ByteBuf buffer) {
		buffer.writeByte(appearance.getGender());
		buffer.writeByte(1);
		
		buffer.writeByte(0);
		buffer.writeByte(0);
		buffer.writeByte(0);
		buffer.writeByte(0);
		
		buffer.writeShort(0x100 + appearance.getTorso());
		buffer.writeShort(0x100 + appearance.getLegs());
		buffer.writeShort(0x100 + 7);
		buffer.writeShort(0x100 + appearance.getLegs());
		buffer.writeShort(0x100 + 29);
		buffer.writeShort(0x100 + appearance.getHands());
		buffer.writeShort(0x100 + appearance.getFeet());
		buffer.writeShort(0x100 + 14);
		
        buffer.writeByte(7);
        buffer.writeByte(8);
        buffer.writeByte(9);
        buffer.writeByte(5);
        buffer.writeByte(0);
		
        buffer.writeShort(0x328);
        buffer.writeShort(0x337);
        buffer.writeShort(0x333);
        buffer.writeShort(0x334);
        buffer.writeShort(0x335);
        buffer.writeShort(0x336);
        buffer.writeShort(0x338);

		buffer.writeLong(StringUtil.hash("hannes"));
		
		/*
		 * The general info
		 */
		buffer.writeByte(3);
		buffer.writeShort(10);
	}

	@Override
	public int getIdentifier() {
		return AppearanceAttribute.BITMAP_ID;
	}
	
	@Override
	public int hashCode() {
		return getIdentifier();
	}
	
	public boolean equals(Object other) {
		return other.hashCode() == hashCode();
	}

}
