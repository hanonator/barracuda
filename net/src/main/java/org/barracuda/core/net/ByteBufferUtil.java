package org.barracuda.core.net;

import io.netty.buffer.ByteBuf;

public class ByteBufferUtil {

	/**
	 * Reads an ASCII string that is terminated with character 10 ('\\n')
	 * 
	 * @param buffer
	 * @return
	 */
	public static String readString(ByteBuf buffer) {
		StringBuilder builder = new StringBuilder();
		for (byte b = buffer.readByte(); b != 10 && buffer.isReadable(); b = buffer.readByte()) {
			builder.append((char) b);
		}
		return builder.toString();
	}

	/**
	 * Reads an ASCII string that is terminated with character 10 ('\\n')
	 * 
	 * @param buffer
	 * @return
	 */
	public static void writeString(ByteBuf buffer, String string) {
		buffer.writeBytes(string.getBytes()).writeByte(10);
	}

}