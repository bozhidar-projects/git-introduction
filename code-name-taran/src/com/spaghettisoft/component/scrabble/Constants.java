package com.spaghettisoft.component.scrabble;

public class Constants {

	public static void printHelp() {
		System.out.println();
		System.out.println("How to read the board:");
		System.out.println("\".\" in an empty square means word points are multiplied by 2 ");
		System.out.println("\"*\" in an empty square means word points are multiplied by 3 ");
		System.out.println("\"^\" in an empty square means letter points are multiplied by 2 ");
		System.out.println("\"~\" in an empty square means letter points are multiplied by 3 ");
		System.out.println("\"@\" means this is the start tile and word points are multiplied by 2");
		System.out.println("When the player see in his letters the sign ? that means he has an empty tile "
				       + "\n   and he could use it for any letter. Empty tile gives 0 letter points.");
		System.out.println();
		System.out.println("Rules:");
		System.out.println("A words on the board should contain only letters. No hifens(-) or apostrophs(') allowed.");
		System.out.println("First word has to be placed on top of the tile with the sign \"@\"");
		System.out.println("Every next word should cross another word.");
		System.out.println("During one turn player can place only one word.");
		System.out.println();
		System.out.println("During one tur player can:");
		System.out.println("- place a word");
		System.out.println("- change some letters with different letters from the bag");
		System.out.println("- pass his turn");
		System.out.println();
		System.out.println("Letter points:");
		System.out.println("1 point: A, E, I, L, N, O, R, S, T, U");
		System.out.println("2 point: D, G");
		System.out.println("3 point: B, C, M, P");
		System.out.println("4 point: F, H, V, W, Y");
		System.out.println("8 point: J, K, Q, X, Z");


	}

	public static Letter[] alphabet = {new Letter('A', 1, 9), new Letter('B', 3, 2), new Letter('C', 3, 2),
									   new Letter('D', 2, 4), new Letter('E', 1, 12),new Letter('F', 4, 2),
									   new Letter('G', 2, 3), new Letter('H', 4, 2), new Letter('I', 1, 9),
									   new Letter('J', 8, 1), new Letter('K', 8, 1), new Letter('L', 1, 4),
									   new Letter('M', 3, 2), new Letter('N', 1, 6), new Letter('O', 1, 8),
									   new Letter('P', 3, 2), new Letter('Q', 8, 1), new Letter('R', 1, 6),
									   new Letter('S', 1, 4), new Letter('T', 1, 6), new Letter('U', 1, 4),
									   new Letter('V', 4, 2), new Letter('W', 4, 2), new Letter('X', 8, 1),
									   new Letter('Y', 4, 2), new Letter('Z', 8, 1), new Letter('?', 0, 2)};


	public static int checkPointsForLetter(char ch) {
		for (int i = 0; i < alphabet.length; i++) {
			char l = alphabet[i].getCh();
			if(l == ch) {
				return alphabet[i].getPoints();
			}
		}
		return 0;
	}

	public static String lowercaseLettersInAlphabet = "abcdefghijklmnopqrstuvwxyz";
	public static String uppercaseLettersInAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static char makeTheLowercaseBecomeUppercase(char wordChar) {
		for (int j = 0; j < Constants.lowercaseLettersInAlphabet.length(); j++) {
			if(wordChar == Constants.lowercaseLettersInAlphabet.charAt(j)) {
				wordChar = Constants.uppercaseLettersInAlphabet.charAt(j);
				break;
			}
		}
		return wordChar;
	}

	public static String makeLowercasesCharsInWordUppercases(String word) {
		String uppercasesWord = "";
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			for (int j = 0; j < Constants.lowercaseLettersInAlphabet.length(); j++) {
				if(ch == lowercaseLettersInAlphabet.charAt(j)) {
					ch = makeTheLowercaseBecomeUppercase(ch);
				}
			}
			uppercasesWord += ch;
		}
		return uppercasesWord;
	}

	public static String copyStringToAnotherString(String string) {
		String newString = "";
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			newString += ch;
		}
		return newString;
	}

	public static String printGameOver(Player[] players, Bag bag) {
		String gameOverMessage ="\n !!! GAME OVER !!!\n";
		for (int i = 1; i > players.length; i++) {
			int j = i;
			while (j > 0 && players[j].getPoints() < players[j-1].getPoints()) {
				Player tempPlayer1 = players[j];
				Player tempPlayer2 = players[j-1];
				players[j-1] = tempPlayer1;
				players[j] = tempPlayer2;
				j--;
			}
		}
		for (int i = 0; i < players.length; i++) {
			gameOverMessage += players[i].getName() + " has " +  players[i].getPoints() + " points.\n";
		}
		System.out.println();

		if(players[0].getPoints() != players[1].getPoints()) {
			gameOverMessage += "Winner is: " + players[0].getName() +"\n";
		} else {
			if(players.length == 2) {
				gameOverMessage += "No winner. Both players have equal points.\n";
			} else {
				if(players[1].getPoints() != players[2].getPoints()) {
					gameOverMessage += "Winners are: " + players[0].getName() + " and " + players[1].getName() +"\n";
				} else {
					if(players.length == 3) {
						gameOverMessage += "No winner. All the players have equal points.\n";
					} else {
						if(players[2].getPoints() != players[3].getPoints()) {
							gameOverMessage += "Winners are: " + players[0].getName() + ", " + players[1].getName() + " and " + players[2].getName() + "\n";
						} else {
							gameOverMessage += "No winner. All the players have equal points.\n";
						}
					}
				}
			}
		}
		return gameOverMessage;
	}



}
