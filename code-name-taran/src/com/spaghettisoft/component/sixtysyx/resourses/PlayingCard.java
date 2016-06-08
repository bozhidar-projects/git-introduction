package com.spaghettisoft.component.sixtysyx.resourses;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.sixtysyx.resourses.constants.Colour;
import com.spaghettisoft.component.sixtysyx.resourses.constants.Rank;

public class PlayingCard implements Component
{
	private Colour color;
	private Rank rank;
	
	public PlayingCard(Rank rank, Colour color)
	{
		this.rank = rank;
		this.color = color;
	}
	
	@Override
	public void show()
	{
		System.out.println(rank.toString() + " of " + color.toString());
	}

	public Colour getColor()
	{
		return color;
	}

	public Rank getRank()
	{
		return rank;
	}

}
