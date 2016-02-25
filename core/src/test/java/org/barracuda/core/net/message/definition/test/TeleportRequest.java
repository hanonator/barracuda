package org.barracuda.core.net.message.definition.test;

import org.barracuda.core.net.message.definition.Attribute;
import org.barracuda.core.net.message.definition.AttributeType;
import org.barracuda.core.net.message.definition.Definition;

/**
 * Packet layout:
 * opcode:	71
 * payload:	x - SHORT
 * 			y - SHORT
 * 
 * @author brock
 *
 */
@Definition(opcode = 71, length = 4, attributes = {
		@Attribute(field="x", type=AttributeType.SHORT),
		@Attribute(field="y", type=AttributeType.SHORT),
})
public class TeleportRequest {

	/**
	 * The x coordinate
	 */
	private int x;
	
	/**
	 * The y coordinates
	 */
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
