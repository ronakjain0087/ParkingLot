/**
 * 
 */
package com.gojek.parkinglot.exceptions;

/**
 * @author rkala
 *
 */
public class ParkingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param throwable
	 */
	public ParkingException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * @param message
	 */
	public ParkingException(String message) {
		super(message);
	}

	/**
	 * @param throwable
	 */
	public ParkingException(Throwable throwable) {
		super(throwable);
	}

}
