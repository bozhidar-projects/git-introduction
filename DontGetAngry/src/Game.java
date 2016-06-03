import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	DontGetAngryMenu menu;
	static List<Player> players;
	static Scanner input;
	static Dice dice;
	
	public Game() {
		menu = new DontGetAngryMenu();
		input = new Scanner(System.in);
		dice = new Dice(6);
	}

	public void play() {

		setPlayerPositions();

		while (true) {

			for (int k = 0; k < players.size(); k++) {
				Player player = players.get(k);
				int squares = dice.roll(player);
				turn(player, squares);
				if(player.hasWon()){
					printVictory(player);
					return;
				}
			}
		}

	}

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
			int index = menu.getIntegerOption();
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
		String choice = menu.getStringOption();

		if (choice.equalsIgnoreCase("y")) {
			startNewToken(player);
		} else {
			chooseTokenToMove(player,squares);
		}
	}

	public void turn(Player player, int squares) {
		if (squares == 6) {
			if (player.hasATokenInGame()) {
				giveMoreOptionsToPlayer(player,squares);
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
		Board.print();
	}

	public static void pause() {
		input.nextLine();
	}

	public void start() {
		menu.show();
		int choice;
		do {
			choice = menu.getIntegerOption();
			switch (choice) {
			case 1:
				play();
				break;
			case 2:
				printRules();
				break;
			default:
				System.out.println("Incorrect option.");
				break;
			}
		} while (choice != 0);

	}
	
	public void printVictory(Player player){
		System.out.println("Congratulations "+ player.name+"!!!");
		System.out.println("You have won the game!");
	}

	public static void printRules() {
		String rules = "To enter a token into play from its staging area to its starting square, a player must roll a 6.\n"
				+ "If the player has no tokens yet in play and does not roll a 6, the turn passes to the next player. \n"
				+ "Once a player has one or more tokens in play, he selects a token and moves it forward along the track the number of squares indicated by the die roll. \n"
				+ "When a player rolls a 6 he may choose to advance a token already in play, or alternatively, he may enter another staged token to its starting square. \n"
				+ "The rolling of a 6 earns the player an additional (\"bonus\") roll in that turn. \n"
				+ " If the third roll is also a 6, the player may not move a token and the turn immediately passes to the next player. \n"
				+ " If the advance of a token ends on a square occupied by an opponent's token, the opponent token is returned to its owner's yard. \n"
				+ "In order to win a player must have all of his pieces in the finish zone.";
		System.out.println(rules);
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
		int numberOfPlayers = input.nextInt();
		input.nextLine();
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
			name = input.nextLine();
			player = new Player(name);
			players.add(player);
		}
		return players;
	}

}
