package org.barracuda.core.game.contract.login;

import org.barracuda.core.net.message.serialize.Serializable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class AuthenticationResponse implements Serializable {

	@Override
	public ByteBuf serialize(ByteBufAllocator allocator) {
		return allocator.buffer(3).writeByte(2).writeBytes(new byte[2]);
	}

}
