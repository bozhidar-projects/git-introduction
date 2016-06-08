package com.spaghettisoft.component.sixtysyx.resourses;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.spaghettisoft.component.sixtysyx.resourses.constants.Colour;
import com.spaghettisoft.component.sixtysyx.resourses.constants.Rank;

public class Deck
{
	Queue<PlayingCard> deck;
	public final int DECK_SIZE = 24;

	public Deck()
	{
		newDeck();
	}

	private void newDeck()
	{
		deck = new LinkedList<>();
		generateDeck();
	}

	private void shuffle(PlayingCard[] deckCopy)
	{
		Random rnd = ThreadLocalRandom.current();
		
		for (int i = deckCopy.length-1; i > 0; i--)
		{
			int index = rnd.nextInt(i+1);
			PlayingCard temp = deckCopy[index];
			deckCopy[index] = deckCopy[i];
			deckCopy[i] = temp;
		}
		arrayToQueue(deckCopy);
		

	}

	private void arrayToQueue(PlayingCard[] deckCopy)
	{
		for (int i = 0; i < deckCopy.length; i++)
		{
			deck.add(deckCopy[i]);
		}
		
	}

	// Generates Card for every Rank and Colour and adds it in the deck
	private void generateDeck()
	{
		Rank[] rank = Rank.values();
		Colour[] colour = Colour.values();
		PlayingCard[] deckCopy = new PlayingCard[DECK_SIZE];

		for (int i = 0, k = 0; i < Rank.values().length; i++, k++)
		{
			for (int j = 0; j < Colour.values().length; j++, k++)
			{
				PlayingCard newCard = new PlayingCard(rank[i], colour[j]);
				deckCopy[k] = newCard;
			}
			k--;
		}
		shuffle(deckCopy);
	}
	
	public PlayingCard getCard()
	{
		PlayingCard temp = deck.poll();
		return temp;
	}
	
	public int size()
	{
		return deck.size();
	}
}
