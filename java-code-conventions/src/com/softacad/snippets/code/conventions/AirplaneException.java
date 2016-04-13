/* class QRH
 * 
 * version: 1.0
 * 
 * Date: 13.04.2016
 * 
 * This file is licensed under MIT
 */
package com.softacad.snippets.code.conventions;

/**
 * Airplane exception
 *
 */
public class AirplaneException extends Exception {
	private static final long serialVersionUID = 1L;

	public AirplaneException(String errorMessage) {
		super(errorMessage);
	}
}
