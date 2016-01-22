package org.horvik;

@SuppressWarnings("serial")
public class HorvikException extends Exception {

	public HorvikException(String message, Throwable cause) {
		super(message, cause);
	}

	public HorvikException(String message) {
		super(message);
	}

	public HorvikException(Throwable cause) {
		super(cause);
	}

}
