package org.barracuda.util.net;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class BitChannel {

	/**
	 * The write buffer
	 */
	private final ByteBuf out = Unpooled.buffer(10);
	
	/**
	 * The binary input stream
	 */
	private final BitInputStream input_stream;
	
	/**
	 * The binary output stream
	 */
	private final BitOutputStream output_stream;

	public BitChannel(ByteBuf in) {
		this.input_stream = new BitInputStream(new ByteBufInputStream(in));
		this.output_stream = new BitOutputStream(new ByteBufOutputStream(out));
	}
	
	public BitChannel() {
		this (Unpooled.buffer());
	}

	public ByteBuf collect(ByteBuf buffer) {
		return buffer.writeBytes(out);
	}

	public ByteBuf collect() {
		return out;
	}
	
	public BitChannel flush() {
		output_stream.flush();
		return this;
	}

	public boolean readBoolean() {
		return input_stream.readBoolean();
	}

	public char readChar() {
		return input_stream.readChar();
	}

	public char readChar(int r) {
		return input_stream.readChar(r);
	}

	public String readString() {
		return input_stream.readString();
	}

	public short readShort() {
		return input_stream.readShort();
	}

	public int readInt() {
		return input_stream.readInt();
	}

	public int readInt(int r) {
		return input_stream.readInt(r);
	}

	public long readLong() {
		return input_stream.readLong();
	}

	public double readDouble() {
		return input_stream.readDouble();
	}

	public float readFloat() {
		return input_stream.readFloat();
	}

	public byte readByte() {
		return input_stream.readByte();
	}

	public void write(boolean x) {
		output_stream.write(x);
	}

	public void write(byte x) {
		output_stream.write(x);
	}

	public void write(int x) {
		output_stream.write(x);
	}

	public void write(int x, int r) {
		output_stream.write(x, r);
	}

	public void write(double x) {
		output_stream.write(x);
	}

	public void write(long x) {
		output_stream.write(x);
	}

	public void write(float x) {
		output_stream.write(x);
	}

	public void write(short x) {
		output_stream.write(x);
	}

	public void write(char x) {
		output_stream.write(x);
	}

	public void write(char x, int r) {
		output_stream.write(x, r);
	}

	public void write(String s) {
		output_stream.write(s);
	}

	public void write(String s, int r) {
		output_stream.write(s, r);
	}

	public <T extends Enum<?>> void write(T s, int r) {
		output_stream.write(s.ordinal(), r);
	}
	
}