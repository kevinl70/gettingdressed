/**
 * 
 */
package com.exceptions;

/**
 * @author Kevin
 *
 */
public class DressingCommandException extends Exception {

	private static final long serialVersionUID = 1L;
	private String mesage;
	
	/**
	 * Default empty constructor.
	 */
	public DressingCommandException() {}
	
	/**
	 * Class constructor takes a message and sets the message attribute
	 * @param mesage A String representing the message of the exception.
	 */
	public DressingCommandException(String mesage) {
		this.mesage = mesage;
	}

	/**
	 * @return A string representing the error message
	 */
	public String getMesage() {
		return this.mesage;
	}
}
