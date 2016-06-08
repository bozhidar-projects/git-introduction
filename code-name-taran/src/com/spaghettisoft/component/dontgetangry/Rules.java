package com.spaghettisoft.component.dontgetangry;

import com.spaghettisoft.component.Component;

public class Rules implements Component{

	@Override
	public void show() {
		String rules = "To enter a token into play from its staging area to its starting square, a player must roll a 6.\n"
				+ "If the player has no tokens yet in play and does not roll a 6, the turn passes to the next player. \n"
				+ "Once a player has one or more tokens in play, he selects a token and moves it forward along the track the number of squares indicated by the die roll. \n"
				+ "When a player rolls a 6 he may choose to advance a token already in play, or alternatively, he may enter another staged token to its starting square. \n"
				+ "The rolling of a 6 earns the player an additional (\"bonus\") roll in that turn. \n"
				+ " If the third roll is also a 6, the player may not move a token and the turn immediately passes to the next player. \n"
				+ " If the advance of a token ends on a square occupied by an opponent's token, the opponent token is returned to its owner's yard. \n"
				+ "In order to win a player must have all of his pieces in the finish zone.";
		System.out.println(rules);
	}
	
}
