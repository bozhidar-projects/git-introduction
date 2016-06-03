package com.spaghettisoft.component.belote;

import com.spaghettisoft.component.Component;

public class BeloteGame implements Component {

	@Override
	public void show() {
		Game belotGame = new Game();
		belotGame.printStatus(true);
		belotGame.callTrump();
		belotGame.finishDealing();
		belotGame.printStatus(true);
		belotGame.scoreBonuses();
		belotGame.playCards();
		belotGame.calculateScores();
		belotGame.printStatus(false);
	}

}
