package com.spaghettisoft.component.dontgetangry;

import com.spaghettisoft.globals.StaticObjects;

public class Dice {
	public int sides;
	
	public Dice(int sides) {
		this.sides=sides;
	}
	
	public int roll(Player player) {
		int squares = 0;
		System.out.println(player.name + " throwing dice..");
		DontGetAngry.pause();
		squares = getRandomNumber();
		System.out.println(player.name + " throws " + squares);
		DontGetAngry.pause();
		return squares;
	}
	
	public int getRandomNumber() {
		int result = StaticObjects.rand.nextInt(sides+1);
		if (result == 0) {
			result = getRandomNumber();
		}
		return result;
	}
	
	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}
}