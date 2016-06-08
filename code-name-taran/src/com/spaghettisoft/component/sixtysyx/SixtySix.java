package com.spaghettisoft.component.sixtysyx;

import com.spaghettisoft.component.game.SixtySixAbstractGame;
import com.spaghettisoft.component.sixtysyx.resourses.Deck;
import com.spaghettisoft.component.sixtysyx.resourses.PlayingCard;
import com.spaghettisoft.globals.StaticObjects;

public class SixtySix extends SixtySixAbstractGame
{
	private Player playerOne;
	private Player playerTwo;
	private Deck deck;
	public static PlayingCard powerCard;
	private boolean isDeckClosed;
	private boolean isFirstPlayerHandWinner;
	private boolean isFirstHand;

	public SixtySix()
	{
		isDeckClosed = false;
		isFirstPlayerHandWinner = true;
		isFirstHand = true;
	}

	@Override
	protected void initialize()
	{
		System.out.println("============================");
		getPlayerNames();
		deck = new Deck();
		dealCards();
		drawPowerCard();
		System.out.println("============================");
	}

	private void getPlayerNames()
	{
		showTitle();
		System.out.print("Player One Name: ");
		String firstPlayerName = StaticObjects.scanner.nextLine();
		System.out.print("Player Two Name: ");
		String secondPlayerName = StaticObjects.scanner.nextLine();

		playerOne = new Player(firstPlayerName);
		playerTwo = new Player(secondPlayerName);
	}

	private void dealCards()
	{
		deal();
		deal();
		playerOne.orderHand();
		playerTwo.orderHand();
	}

	private void deal()
	{
		for (int i = 0; i < Player.PLAYER_HAND_START_SIZE; i++)
		{
			if (i < (Player.PLAYER_HAND_START_SIZE / 2))
			{
				PlayingCard temp = deck.getCard();
				playerOne.addCardtoHand(temp);
			}
			else
			{
				PlayingCard temp2 = deck.getCard();
				playerTwo.addCardtoHand(temp2);
			}
		}
	}

	private void showTitle()
	{
		System.out.println("SIXTY SIX!");
	}

	private void drawPowerCard()
	{
		powerCard = deck.getCard();
	}

	@Override
	protected void printEndGameMessage()
	{
		System.out.println("Thank you for playing Sixty Six!");
	}

	@Override
	protected void drawGame()
	{
		System.out.println("============================");
		System.out.print("POWER CARD -> ");
		powerCard.show();
		if (isFirstPlayerHandWinner)
		{
			showPlayerOptions(playerOne, playerTwo);
		}
		else
		{
			showPlayerOptions(playerTwo, playerOne);
		}
		System.out.println("============================");
	}

	private void showPlayerOptions(Player firstPlayer, Player secondPlayer)
	{
		System.out.println(firstPlayer.getName() + " options:");
		showCloseOption();
		firstPlayer.show();
		System.out.println(secondPlayer.getName() + " options:");
		showCloseOption();
		secondPlayer.show();
		isFirstHand = false;
	}

	private void showCloseOption()
	{
		if (isDeckClosed || isFirstHand)
		{
			return;
		}
		System.out.println("0.Close Deck");
	}

	@Override
	protected boolean isEnded()
	{
		if (playerOne.getWonCardsValue() >= 66)
		{
			System.out.println(playerOne.getName() + " wins!");
			return true;
		}
		
		if (playerTwo.getWonCardsValue() >= 66)
		{
			System.out.println(playerTwo.getName() + " wins!");
			return true;
		}
		
		if (playerOne.getCurrentHandSize() < 1 || playerTwo.getCurrentHandSize() < 1)
		{
			if (isFirstPlayerHandWinner)
			{
				System.out.println(playerOne.getName() + " wins!");
				return true;
			}
			else {
				System.out.println(playerTwo.getName() + " wins!");
				return true;
			}
		}
		return false;
	}

	@Override
	protected void processGame()
	{
		System.out.println("============================");
		PlayingCard firstCard;
		PlayingCard secondCard;

		if (isFirstPlayerHandWinner)
		{
			firstCard = getCard(playerOne, false);
			if (firstCard == null)
			{
				return;
			}

			secondCard = getCard(playerTwo, true);
		}
		else
		{
			secondCard = getCard(playerTwo, false);
			
			if (secondCard == null)
			{
				return;
			}
			
			firstCard = getCard(playerOne, true);
		}
		
		if (deck.size() < 0 && isDeckClosed)
		{
			isFirstPlayerHandWinner = checkHandWinnerSpecial(firstCard, secondCard);
		}
		else
		{
			isFirstPlayerHandWinner = checkHandWinner(firstCard, secondCard);
			pickCard();
		}
		
		if (isFirstPlayerHandWinner)
		{
			playerOne.setWonHand(firstCard, secondCard);
			System.out.println(playerOne.getName() + " wins the hand!");
		}
		else {
			playerTwo.setWonHand(firstCard, secondCard);
			System.out.println(playerTwo.getName() + " wins the hand!");
		}
		
		System.out.println(playerOne.getName() + " cards won value: " + playerOne.getWonCardsValue());
		System.out.println(playerTwo.getName() + " cards won value: " + playerTwo.getWonCardsValue());
		
		System.out.println("============================");
	}
	
	// I know this method should not do more than two things, but I don't have time to rework the whole program
	private PlayingCard getCard(Player player, boolean isSecond)
	{
		System.out.print(player.getName() + " enter valid option:");
		int index = StaticObjects.scanner.nextInt();
		closeDeck(player, isSecond, index);
		System.out.println();
		PlayingCard playerCard = player.selectMenuItem(index-1, isSecond);
		if (playerCard != null)
		{
			System.out.println(player.getName() + " plays ");
			playerCard.show();
		}
		return playerCard;
	}

	private void closeDeck(Player player, boolean isSecond, int index)
	{
		if (index == 0 && !isDeckClosed)
		
		{
			isDeckClosed = true;
			getCard(player, isSecond);
		}
	}

	private boolean checkHandWinner(PlayingCard firstCard, PlayingCard secondCard)
	{
		if (firstCard.getColor().equals(secondCard.getColor()))
		{
			if (firstCard.getRank().value() < secondCard.getRank().value())
			{
				return false;
			}
		}
		else
		{
			if (secondCard.getColor().equals(powerCard.getColor()))
			{
				return false;
			}
		}
		return true;
	}

	private void pickCard()
	{
		if (isFirstPlayerHandWinner)
		{
			playerOne.addCardtoHand(deck.getCard());
			playerTwo.addCardtoHand(deck.getCard());
		}
		else
		{
			playerTwo.addCardtoHand(deck.getCard());
			playerOne.addCardtoHand(deck.getCard());
		}
	}

	private boolean checkHandWinnerSpecial(PlayingCard firstCard, PlayingCard secondCard)
	{
		if (firstCard.getRank().value() < secondCard.getRank().value())
		{
			return false;
		}
		return true;
	}

}
