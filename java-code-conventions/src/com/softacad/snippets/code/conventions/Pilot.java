/* class Airplane
 *
 * version: 1.0
 *
 * Date: 13.04.2016
 *
 * This file is licensed under MIT
 */
package com.softacad.snippets.code.conventions;

/**
 * This class contains the abstraction of airplane pilot
 *
 */
public class Pilot {

	private QRH qrh;

	public boolean readQRH() {
		boolean isQrhOk = (qrh == null) ? false : qrh.checkAll();
		return isQrhOk;
	}

}
