package org.barracuda.core.game.util;

import java.nio.ByteBuffer;

/**
 * utils to do with text lol
 * @author red
 *
 */
public class StringUtil {

	/**
	 * Encode a string
	 * 
	 * @param text
	 * @return
	 */
	public static long hash(String text) {
		long hash = 0L;
		for (int i = 0; i < text.length() && i < 12; i++) {
			char c = text.charAt(i);
			hash *= 37L;
			if (c >= 'A' && c <= 'Z')
				hash += (1 + c) - 65;
			else if (c >= 'a' && c <= 'z')
				hash += (1 + c) - 97;
			else if (c >= '0' && c <= '9')
				hash += (27 + c) - 48;
		}
		while (hash % 37L == 0L && hash != 0L)
			hash /= 37L;
		return hash;
	}

	/**
	 * Reads a string from the buffer with escape char 10
	 * 
	 * @param buffer
	 * @return
	 */
	public static String from(ByteBuffer buffer) {
		StringBuilder builder = new StringBuilder();
		while (buffer.hasRemaining()) {
			char c = (char) buffer.get();
			
			if (c == 10 || c == 0)
				break;
			
			builder.append(c);
		}
		return builder.toString();
	}

}