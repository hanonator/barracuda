package org.barracuda.core.game.contract.login;

import org.barracuda.core.net.message.Serializable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class HandshakeResponse implements Serializable {

	/**
	 * The server key used to synchronize the ISAAC keys between the client and the server
	 */
	private final long serverKey;

	/**
	 * Constructor
	 * 
	 * @param serverKey
	 */
	public HandshakeResponse(long serverKey) {
		this.serverKey = serverKey;
	}

	@Override
	public ByteBuf serialize(ByteBufAllocator allocator) {
		return allocator.buffer().writeBytes(new byte[9]).writeLong(serverKey);
	}

}
