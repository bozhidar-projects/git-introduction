package com.spaghettisoft.component.dontgetangry;

import java.util.List;
import java.util.ArrayList;

import com.spaghettisoft.component.game.AbstractGame;
import com.spaghettisoft.globals.StaticObjects;

public class DontGetAngry extends AbstractGame{

	private State currentState = State.STARTING;
	static List<Player> players;
	static Dice dice;
	Player player;
	private boolean ended;
	
	
	@Override
	public void show() {
		initialize();
		super.show();
	}
	
	private void initialize() {
		ended = false;
	}

	public DontGetAngry() {
		dice = new Dice(6);
		ended = false;
	}

	@Override
	protected void drawGame() {
		if(currentState == State.STARTING){
			setPlayerPositions();
			currentState = State.PLAYING;
		}
		Board.print();
	}
	
	@Override
	protected boolean isEnded() {
		return ended;
	}
	
	@Override
	protected void processGame() {
		
		for (int k = 0; k < players.size(); k++) {
			player = players.get(k);
			int squares = dice.roll(player);
			turn(player, squares);
			if(player.hasWon()){
				gameEnds();
				break;
			}
		}
	}
	
	@Override
	protected void printEndGameMessage() {
		System.out.println("Congratulations "+ player.name+"!!!");
		System.out.println("You have won the game!");
	}
	/*
	public void play() {
		
		setPlayerPositions();
		drawGame();
		
		while (!isEnded()) {
			processGame();
			
		}
		printEndGameMessage();

	}
	/*
	public void start(){
		Rules rules = new Rules();
		
		int choice;
		do {
			choice = game.getIntegerOption();
			switch (choice) {
			case 1:
				game.play();
				break;
			case 2:
				rules.show();
				break;
			case 0:
				break;
			default:
				System.out.println("Incorrect option.");
				break;
			}
		} while (choice != 0);
	}

	*/
	public void updatePlayerSquares(Token token){
		if(token.hasFinished()){
			token.player.diagSquaresLeft--;
			token.player.inGameTokens.remove(token);
		}
	}
	
	public void checkForCollisionsWithOtherTokens(Token token) {
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player != token.player) {
				for (int j = 0; j < player.inGameTokens.size(); j++) {
					Token anotherToken = player.inGameTokens.get(j);
					if (token.i == anotherToken.i && token.j == anotherToken.j) {
						player.remove(anotherToken);
						System.out.println(token.player.name + "'s token has overtaken "+anotherToken.player.name+"'s!");
					}
				}
			}
		}
	}

	public void startNewToken(Player player) {
		Token token = player.notInGameTokens.peek();
		try {
			token = player.getNewTokenInGame();
		} catch (NoPiecesLeftException e) {
			e.getMessage();
		}
		token.move(0);
		checkForCollisionsWithOtherTokens(token);
	}

	public void chooseTokenToMove(Player player,int squares) {
		Token token;
		if (player.hasMoreThanOneTokenInGame()) {
			System.out.println("Which token do you wish to move?(index)");
			int index = getIntegerOption();
			token = player.inGameTokens.get(index - 1);
			token.move(squares);
		} else {
			token = player.inGameTokens.get(0);
			token.move(squares);
		}
		checkForCollisionsWithOtherTokens(token);
		updatePlayerSquares(token);
		
	}

	public void giveMoreOptionsToPlayer(Player player,int squares) {

		System.out.println("Do you want to start another token?(Y/N)");
		String choice = getStringOption();

		if (choice.equalsIgnoreCase("y")) {
			startNewToken(player);
		} else {
			chooseTokenToMove(player,squares);
		}
	}

	public void turn(Player player, int squares) {
		if (squares == 6) {
			if (player.hasATokenInGame()){
				if(player.hasTokensToStart()) {
					giveMoreOptionsToPlayer(player,squares);
				}else{
					chooseTokenToMove(player,squares);
				}
			} else {
				startNewToken(player);
			}
			
			int squares2 = dice.roll(player);
			turn(player, squares2);
		} else {
			if (player.hasATokenInGame()) {
				chooseTokenToMove(player,squares);
			}
		}
	}

	public void setPlayerPositions() {
		players = getPlayerNames();
		Board.fill();
		for (int k = 0; k < players.size(); k++) {
			Player player = players.get(k);
			player.setTurn(k + 1);
		}
	}

	public static void pause() {
		StaticObjects.scanner.nextLine();
	}

	

	public int getIntegerOption(){
		int result = StaticObjects.scanner.nextInt();
		StaticObjects.scanner.nextLine();
		return result;
	}
	
	public String getStringOption(){
		String result = StaticObjects.scanner.nextLine();
		return result;
	}
	
	
	public static void printPlayers() {
		List<Player> players = getPlayerNames();

		System.out.println("Players: ");
		for (Player player : players) {
			System.out.println(player.name);
		}
	}

	public static int getNumberOfPlayers() {
		System.out.println("Enter the amount of players (2-4): ");
		int numberOfPlayers = StaticObjects.scanner.nextInt();
		StaticObjects.scanner.nextLine();
		if (numberOfPlayers != 2 && numberOfPlayers != 3 && numberOfPlayers != 4) {
			System.out.println("Incorrect value..");
			numberOfPlayers = getNumberOfPlayers();
		}
		return numberOfPlayers;
	}

	public static List<Player> getPlayerNames() {
		int num = getNumberOfPlayers();
		List<Player> players = new ArrayList<>();
		String name;
		Player player;
		for (int i = 0; i < num; i++) {
			System.out.println("Player #" + (i + 1) + ": ");
			name = StaticObjects.scanner.nextLine();
			player = new Player(name);
			players.add(player);
		}
		return players;
	}

	
	protected void gameEnds(){
		this.ended = true;
	}

	

}