package com.spaghettisoft.component.sixtysyx.resourses.constants;

public enum Rank
{
	NINE(0),
	TEN(10),
	JACK(2),
	QUEEN(3),
	KING(4),
	ACE(11);
	
	private int value;
	
	private Rank(int newValue)
	{
		value = newValue;
	}

	/*@Override
	public String toString()
	{
		if (this.equals(NINE))
		{
			return "9";
		}
		
		if (this.equals(TEN))
		{
			return "10";
		}
		
		return this.toString().substring(0,0);
	}*/
	
	public int value()
	{
		return value;
	}
}
