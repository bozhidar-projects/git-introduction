package com.spaghettisoft.component.sixtysyx.resourses.constants;

public enum Colour
{
	SPADES(3),
	HEARTHS(2),
	DIAMONDS(1),
	CLUBS(0);
	
private int value;
	
	private Colour(int newValue)
	{
		value = newValue;
	}

	public int value()
	{
		return value;
	}
	
	/*@Override
	public String toString() {
		return this.toString().substring(0, 0);
	};*/
}
