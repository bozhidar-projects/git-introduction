package com.spaghettisoft.component.belote;

public interface BelotConstants {

	public final static int NUMBER_OF_PLAYERS = 2;
	public final static int CARDS_IN_DECK = 28;
	public final static int CARDS_IN_HAND = 8;

	public final static int UNKNOWN = 0;
	public final static int CLUBS = 1;
	public final static int DIAMONDS = 2;
	public final static int HEARTS = 3;
	public final static int SPADES = 4;
	public final static int NOTHING = 5;
	public final static int EVERYTHING = 6;

	public final static int EIGHT = 0;
	public final static int NINE = 1;
	public final static int TEN = 2;
	public final static int JACK = 3;
	public final static int QUEEN = 4;
	public final static int KING = 5;
	public final static int ACE = 6;
	public final static int[] VALUES = { 0, 0, 10, 2, 3, 4, 11 };
	public final static String[] TRUMP_NAMES = { "unknown", "clubs",
			"diamonds", "hearts", "spades", "nothing", "everything" };
	public final static String[] RANK_NAMES = { "eight", "nine", "ten", "jack",
			"queen", "king", "ace" };
	public final static int[] ALL_FOUR = { JACK, NINE, ACE, TEN, KING, QUEEN };
	public final static int[] ALL_FOUR_BONUSES = { 200, 150, 100, 100, 100, 100 };

	public final static int BELOT_BONUS = 20;
	public final static int ALL_TRICKS_BONUS = 100;
	public final static int TIERCE_BONUS = 20;
	public final static int QUART_BONUS = 50;
	public final static int QUINT_BONUS = 100;

	public final static int NINE_OF_TRUMP_VALUE = 14;
	public final static int NINE_OF_NONTRUMP_VALUE = 0;
	public final static int JACK_OF_TRUMP_VALUE = 20;
	public final static int JACK_OF_NONTRUMP_VALUE = 2;

	public final static int USE_TRUMP = 10;
	public final static int DONT_USE_TRUMP = 11;
}
