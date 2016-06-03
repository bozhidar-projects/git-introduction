package com.spaghettisoft.component.belote;

public class Card implements BelotConstants {

	private int suit;
	private int rank;
	private int value;
	private int sortCode;

	Card(int s, int r, int v, int sc) {
		suit = s;
		rank = r;
		value = v;
		sortCode = sc;
	}

	public boolean comesAfter(Card otherCard, int trump) {
		if ((suit == trump) && (otherCard.suit != trump))
			return false;
		else if ((suit != trump) && (otherCard.suit == trump))
			return true;
		else if ((suit != otherCard.suit) && (suit > otherCard.suit))
			return true;
		else if (suit != otherCard.suit)
			return false;
		else if (otherCard.rank == EIGHT)
			return false;
		else if (rank == EIGHT)
			return true;
		else if (value < otherCard.value)
			return true;
		else
			return false;
	}

	public boolean isHigher(Card otherCard, int trump) {
		if ((suit == trump) && (otherCard.suit != trump))
			return true;
		else if ((suit != trump) && (otherCard.suit == trump))
			return false;
		else if (suit != otherCard.suit)
			return true;
		else if (value > otherCard.value)
			return true;
		else if (otherCard.rank == EIGHT)
			return true;
		else
			return false;
	}

	public void print() {
		System.out.println(RANK_NAMES[rank] + " " + TRUMP_NAMES[suit] + " "
				+ value);
	}

	public void updateValue(int trump) {
		if (rank == NINE) {
			if ((suit == trump) || (trump == EVERYTHING))
				value = NINE_OF_TRUMP_VALUE;
			else
				value = NINE_OF_NONTRUMP_VALUE;
		} else if (rank == JACK) {
			if ((suit == trump) || (trump == EVERYTHING))
				value = JACK_OF_TRUMP_VALUE;
			else
				value = JACK_OF_NONTRUMP_VALUE;
		}
	}

	public int getValue() {
		return value;
	}

	public int getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}

	public int getSortCode() {
		return sortCode;
	}

}