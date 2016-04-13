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
 * QRH stands for Quick Reference Handbook
 *
 */
public class QRH {

	boolean enginesOk;
	boolean flapsOk;
	boolean problemsWithAilerons;
	boolean coffeeOnTable;

	/**
	 *	check all QRH topics
	 */
	public boolean checkAll() {
		if ((enginesOk==true)
				&& (flapsOk == true)
				&& (problemsWithAilerons == false)
				&& (coffeeOnTable == true)) {
			return true;
		}

		return false;
	}

}
