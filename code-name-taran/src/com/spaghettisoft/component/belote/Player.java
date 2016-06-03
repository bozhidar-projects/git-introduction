package com.spaghettisoft.component.belote;

public class Player implements BelotConstants {

	private int numberCards;
	private Card[] playerCards;
	private String name;
	private int rawGamePoints;
	private int convertedGamePoints;

	Player(String playerName) {
		numberCards = 0;
		playerCards = new Card[CARDS_IN_HAND];
		name = new String(playerName);
		rawGamePoints = 0;
		convertedGamePoints = 0;
	}

	public void dealCards(Cards belotCards, int howMany) {
		for (int i = 0; i < howMany; i++) {
			playerCards[numberCards] = belotCards.getNextCard();
			numberCards++;
		}
	}

	public void print() {
		for (int i = 0; i < numberCards; i++) {
			System.out.print((i + 1) + ". ");
			playerCards[i].print();
		}
	}

	public boolean isCardSuitExistInDeck(int suit) {
		boolean result = true;
		for (int i = 0; i < numberCards; i++) {
			if (playerCards[i].getSuit() == suit) {
				result = false;
			}
		}
		return result;
	}

	public int numberOfCardsInDeck() {
		return numberCards;
	}

	public void discard(Card cardToPlay) {
		int position = 0;
		while (playerCards[position] != cardToPlay)
			position++;
		numberCards--;
		for (int i = position; i < numberCards; i++) {
			playerCards[i] = playerCards[i + 1];
		}
		playerCards[numberCards] = cardToPlay;
	}

	public void sortCards(int trump, int sortField) {
		Card temp;
		boolean swapFlag = false;
		for (int i = 0; i < numberCards - 1; i++) {
			for (int j = i + 1; j < numberCards; j++) {
				if (sortField == USE_TRUMP)
					swapFlag = playerCards[i].comesAfter(playerCards[j], trump);
				else if (sortField == DONT_USE_TRUMP)
					swapFlag = playerCards[i].getSortCode() > playerCards[j]
							.getSortCode();
				if (swapFlag) {
					temp = playerCards[i];
					playerCards[i] = playerCards[j];
					playerCards[j] = temp;
				}
			}
		}
	}

	public boolean findPlay(Card currentCard) {
		boolean result = false;
		for (int i = 0; i < numberCards; i++) {
			if (playerCards[i].getSuit() == currentCard.getSuit())
				return true;
		}
		return result;
	}

	public int findNumberBelots(int trump) {
		int i = 0;
		int numberBelots = 0;
		while (i < numberCards - 1) {
			if ((playerCards[i].getRank() == KING)
					&& ((playerCards[i].getSuit() == trump) || (trump == EVERYTHING))) {
				if (playerCards[i + 1].getRank() == QUEEN
						&& playerCards[i + 1].getSuit() == playerCards[i]
								.getSuit()) {
					numberBelots++;
					i++;
				}
			}
			i++;
		}
		return numberBelots;
	}

	public boolean hasALL_FOUR(int cardName) {
		int number = 0;
		for (int i = 0; i < numberCards; i++) {
			if (playerCards[i].getRank() == cardName)
				number++;
		}
		return (number == 4);
	}

	public Bonuses findRunInfo(int trump) {
		Bonuses info;
		int currentRun = 1;
		int longestRun = 1;
		int longestIndex = 0;

		sortCards(trump, DONT_USE_TRUMP);
		for (int i = 1; i < numberCards; i++) {
			if (playerCards[i].getSortCode() == playerCards[i - 1]
					.getSortCode() + 1)
				currentRun++;
			else
				currentRun = 1;
			int codeOne = playerCards[i].getRank();
			int codeTwo = playerCards[longestIndex].getRank();
			if ((currentRun > longestRun)
					|| ((currentRun == longestRun) && (codeOne > codeTwo))
					|| ((currentRun == longestRun) && (codeOne == codeTwo) && (playerCards[i]
							.getSuit() == trump))) {
				longestRun = currentRun;
				longestIndex = i;
			}
		}
		info = new Bonuses(longestRun, playerCards[longestIndex]);
		sortCards(trump, USE_TRUMP);
		return info;
	}

	public void addToRawGamePoints(Card card) {
		rawGamePoints = rawGamePoints + card.getValue();
	}

	public void addToRawGamePoints(int points) {
		rawGamePoints += points;
	}

	public Card getCard(int whichOne) {
		return playerCards[whichOne];
	}

	public int getRawGamePoints() {
		return rawGamePoints;
	}

	public void setRawGamePoints(int howMany) {
		rawGamePoints = howMany;
	}

	public void addToConvertedGamePoints(int howMany) {
		convertedGamePoints += howMany;
	}

	public int getConvertedGamePoints() {
		return convertedGamePoints;
	}

	public void setConvertedGamePoints(int howMany) {
		convertedGamePoints = howMany;
	}

	public String getName() {
		return name;
	}

}
