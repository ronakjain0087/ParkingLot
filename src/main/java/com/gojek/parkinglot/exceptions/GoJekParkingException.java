/**
 * 
 */
package com.gojek.parkinglot.exceptions;

/**
 * @author rkala
 *
 */
public class GoJekParkingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param throwable
	 */
	public GoJekParkingException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * @param message
	 */
	public GoJekParkingException(String message) {
		super(message);
	}

	/**
	 * @param throwable
	 */
	public GoJekParkingException(Throwable throwable) {
		super(throwable);
	}

}
