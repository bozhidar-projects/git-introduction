package com.spaghettisoft.component.sixtysyx;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.sixtysyx.resourses.PlayingCard;
import com.spaghettisoft.component.sixtysyx.resourses.constants.Colour;
import com.spaghettisoft.component.sixtysyx.resourses.constants.Rank;

public class Player implements Component
{
	private String name;
	private PlayingCard[] hand;
	private int wonCardsValue;
	public static final int PLAYER_HAND_START_SIZE = 6;
	private int currentHandSize = PLAYER_HAND_START_SIZE;

	public Player(String name)
	{
		this.name = name;
		hand = new PlayingCard[PLAYER_HAND_START_SIZE];
		wonCardsValue = 0;
	}
	
	public int getCurrentHandSize()
	{
		return currentHandSize;
	}

	@Override
	public void show()
	{
		int index = 0;
		while (index < hand.length)
		{
			if (hand[index] == null)
			{
				index--;
				break;
			}
			System.out.print((index + 1) + ".");
			hand[index].show();
			index++;
		}

		if (isSwitchOptionEnabled())
		{
			index++;
			System.out.println(index + ".Get Power Card!");
		}

		System.out.println();
	}

	private boolean isSwitchOptionEnabled()
	{
		for (int i = 0; i < hand.length; i++)
		{
			if (hand[i].getRank().equals(Rank.NINE) && hand[i].getColor().equals(SixtySix.powerCard.getColor()))
			{
				return true;
			}
		}
		return false;
	}

	public String getName()
	{
		return name;
	}

	/**
	 * Adds card to the player's hand
	 */
	public void addCardtoHand(PlayingCard newCard)
	{
		for (int i = 0; i < hand.length; i++)
		{
			if (hand[i] == null)
			{
				hand[i] = newCard;
				return;
			}
		}
	}

	public PlayingCard selectMenuItem(int index, boolean isSecond)
	{
		if (index < currentHandSize)
		{
			PlayingCard temp = hand[index];
			checkForMarriage(temp);
			removeCard(index);
			currentHandSize--;
			return temp;
		}
		else
		{
			if (!isSecond)
			{
				AditionalOptions(index);
			}
		}

		return null;
	}

	private void checkForMarriage(PlayingCard card)
	{
		
		switch (card.getRank())
		{
			case QUEEN:
				if (handContainsMarriage(card, Rank.KING))
				{
					if (card.getColor().equals(SixtySix.powerCard))
					{
						System.out.println(name + " announces marriage of power!");
						wonCardsValue += 40;
					}
					else {
						System.out.println(name + " announces marriage!");
						wonCardsValue += 20;
					}
					
				}
			case KING:
				if (handContainsMarriage(card, Rank.QUEEN))
				{
					
				}
				break;
			default:
				return;
		}
		
	}

	private boolean handContainsMarriage(PlayingCard card, Rank rank)
	{
		for (int i = 0; i < hand.length; i++)
		{
			if (card.getColor().equals(hand[i].getColor()) && hand[i].getRank().equals(rank))
			{
				return true;
			}
		}
		return false;
	}

	private void AditionalOptions(int index)
	{
		switchCards();
	}

	private void switchCards()
	{
		PlayingCard temp = SixtySix.powerCard;
		for (int i = 0; i < hand.length; i++)
		{
			if (hand[i].getRank().equals(Rank.NINE) && hand[i].getColor().equals(SixtySix.powerCard.getColor()))
			{
				SixtySix.powerCard = hand[i];
				hand[i] = temp;
			}
		}
	}

	private void removeCard(int index)
	{
		int i = index;

		while (i < hand.length - 1 && hand[i + 1] != null)
		{
			PlayingCard temp = hand[i];
			hand[i] = hand[i + 1];
			hand[i + 1] = temp;
			i++;
		}
		hand[i] = null;

	}

	public void setWonHand(PlayingCard first, PlayingCard second)
	{
		wonCardsValue += first.getRank().value() + second.getRank().value();
	}

	public int getWonCardsValue()
	{
		return wonCardsValue;
	}

	public void orderHand()
	{
		sortByColor();
		sortByRank();
	}

	private void sortByColor()
	{
		Colour[] colors = Colour.values();

		// for each colour in ascending order
		for (int j = colors.length - 1; j >= 0; j--)
		{
			// for each card in hand
			for (int i = 0; i < hand.length; i++)
			{
				// if current card (i) isn't null and it's colour is equal to
				// current colour (j)
				if (hand[i] != null && hand[i].getColor().equals(colors[j]))
				{
					// switch current card with the first card with different
					// colour
					for (int j2 = 0; j2 < hand.length; j2++)
					{
						if (hand[j2].getColor().value() < colors[j].value() && hand[i].getColor() != hand[j2].getColor())
						{
							PlayingCard temp = hand[i];
							hand[i] = hand[j2];
							hand[j2] = temp;
						}
					}
				}
			}
		}
	}

	private void sortByRank()
	{
		{
			for (int i = 0; i < hand.length; i++)
			{
				for (int j = 0; j < hand.length - 1; j++)
				{
					if (hand[j].getColor().equals(hand[j + 1].getColor()))
					{
						if (hand[j].getRank().value() > hand[j + 1].getRank().value())
						{
							PlayingCard temp = hand[j];
							hand[j] = hand[j + 1];
							hand[j + 1] = temp;
						}
					}
				}
			}
		}
	}

}
