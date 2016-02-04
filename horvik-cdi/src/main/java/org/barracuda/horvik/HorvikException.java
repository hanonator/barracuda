package org.barracuda.horvik;

@SuppressWarnings("serial")
public class HorvikException extends RuntimeException {

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
