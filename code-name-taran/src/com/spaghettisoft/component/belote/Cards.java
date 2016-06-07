package com.spaghettisoft.component.belote;

public class Cards implements BelotConstants {

	private Card[] deck;
	private int currentPosition;

	Cards() {
		currentPosition = 0;
		deck = new Card[CARDS_IN_DECK];
		int cardNumber = 0;
		int cardIndex = 0;
		for (int s = CLUBS; s <= SPADES; s++) {
			for (int r = EIGHT; r <= ACE; r++) {
				deck[cardNumber] = new Card(s, r, VALUES[r], cardIndex);
				cardIndex++;
				cardNumber++;
			}
			cardIndex = cardIndex + 3;
		}
	}

	public void assignValues(int trump) {
		for (int i = 0; i < deck.length; i++)
			deck[i].updateValue(trump);
	}

	public Card getNextCard() {
		Card temp = deck[currentPosition];
		currentPosition++;
		return temp;
	}

	public void shuffle() {
		Card card;
		int swapPosition;
		for (int currentPosition = 0; currentPosition < deck.length; currentPosition++) {
			swapPosition = (int) (Math.random() * deck.length);
			card = deck[currentPosition];
			deck[currentPosition] = deck[swapPosition];
			deck[swapPosition] = card;
		}
	}

	public void print() {
		System.out.println("The deck of cards");
		System.out.println("=================");
		for (int i = 0; i < deck.length; i++) {
			System.out.print((i + 1) + ". ");
			deck[i].print();
		}
	}

}
