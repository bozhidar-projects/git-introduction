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
 */
public class QRH {
	private boolean enginesOk;
	boolean flapsOk;
	boolean problemsWithAilerons;
	private boolean coffeeOnTable;

	/**
	 *	check all QRH topics
	 */
	public boolean checkAll() {
		boolean result = (areEnginesOk()==true)
				&& (flapsOk == true)
				&& (problemsWithAilerons == false)
				&& (isCoffeeOnTable() == true);

		return result;
	}

	public boolean isTechnicalStuffOk() {
		boolean technicalStuffOk = areEnginesOk() && flapsOk;
		return technicalStuffOk;
	}

	public boolean areEnginesOk() {
		return enginesOk;
	}

	public void setEnginesOk(boolean enginesOk) {
		this.enginesOk = enginesOk;
	}

	public boolean isCoffeeOnTable() {
		return coffeeOnTable;
	}

	public void setCoffeeOnTable(boolean coffeeOnTable) {
		this.coffeeOnTable = coffeeOnTable;
	}

}
