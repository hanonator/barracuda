package org.barracuda.core.net;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;

public class ByteBufferUtil {

	/**
	 * Reads an ASCII string that is terminated with character 10 ('\\n')
	 * 
	 * @param buffer
	 * @return
	 */
	public static String readString(ByteBuffer buffer) {
		StringBuilder builder = new StringBuilder();
		for (byte b = buffer.get(); b != 10 && buffer.hasRemaining(); b = buffer.get()) {
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
	public static void writeString(ByteBuffer buffer, String string) {
		buffer.put(string.getBytes(Charset.forName("UTF-8"))).put((byte) 10);
	}

	/**
	 * Reads an ASCII string that is terminated with character 10 ('\\n')
	 * 
	 * @param buffer
	 * @return
	 */
	public static String readString(ByteBuf buffer) {
		return ByteBufferUtil.readString(buffer.nioBuffer());
	}

	/**
	 * Reads an ASCII string that is terminated with character 10 ('\\n')
	 * 
	 * @param buffer
	 * @return
	 */
	public static void writeString(ByteBuf buffer, String string) {
		ByteBufferUtil.writeString(buffer.nioBuffer(), string);
	}

}