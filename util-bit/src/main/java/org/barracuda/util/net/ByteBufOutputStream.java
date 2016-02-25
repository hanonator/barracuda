package org.barracuda.util.net;


import java.io.IOException;
import java.io.OutputStream;

import io.netty.buffer.ByteBuf;

public class ByteBufOutputStream extends OutputStream {
	
	/**
	 * The netty ByteBuf
	 */
	private ByteBuf byteBuffer;

	public ByteBufOutputStream(ByteBuf byteBuffer) {
		this.byteBuffer = byteBuffer;
	}

	public ByteBuf getByteBuffer() {
		return byteBuffer;
	}

	public void setByteBuffer(ByteBuf byteBuffer) {
		this.byteBuffer = byteBuffer;
	}

	public void write(int b) throws IOException {
		byteBuffer.writeByte(b);
	}

	public void write(byte[] bytes, int offset, int length) throws IOException {
		byteBuffer.writeBytes(bytes, offset, length);
	}

}
