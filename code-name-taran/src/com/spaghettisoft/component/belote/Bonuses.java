package com.spaghettisoft.component.belote;

public class Bonuses implements BelotConstants {

	private int howMany;
	private Card topCard;

	Bonuses() {
		howMany = 0;
	}

	Bonuses(int h, Card c) {
		howMany = h;
		topCard = c;
	}

	public int calculateValue() {
		if (howMany == 3)
			return TIERCE_BONUS;
		else if (howMany == 4)
			return QUART_BONUS;
		else if (howMany >= 5)
			return QUINT_BONUS;
		else
			return 0;
	}

	public boolean betterThan(Bonuses otherRun, int trump) {
		if (howMany >= 3) {
			if (howMany > otherRun.howMany)
				return true;
			else if (howMany == otherRun.howMany) {
				if (topCard.getRank() > otherRun.topCard.getRank())
					return true;
				else if ((topCard.getRank() == otherRun.topCard.getRank())
						&& (topCard.getSuit() == trump)
						&& (otherRun.topCard.getSuit() != trump))
					return true;
			}
		}
		return false;
	}

	public void print() {
		System.out.println(" a run of " + howMany + " worth "
				+ calculateValue());
	}

}
