package com.spaghettisoft.component.scrabble;

import java.util.Scanner;

public class TurnLetterChange {

	public String changeLetters(Scanner scanner, Player player) {
		System.out.println("Please, insert the letters you would like to change. Example: ACZ");
		String input = scanner.nextLine();

		String lettersThePlayerLikesToChange = extractLettersForChange(input);
	//	System.out.println("The letters you want to change are: " + lettersThePlayerLikesToChange);

		String lettersPlayerHasInHand = Checks.takeLettersPlayerHasInHand(player);
		String lettersThatShalldBeChanged = checkLettersThatShallBeChanged(player, lettersThePlayerLikesToChange, lettersPlayerHasInHand);
	//	System.out.println("The letters that shall be changed are: " + lettersThatShalldBeChanged);
		return lettersThatShalldBeChanged;
	}

	private String extractLettersForChange(String input) {
		String alphabetLetters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
		String lettersThePlayerLikesToChange = "";
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			for (int j = 0; j < 26; j++) {
				if(ch == alphabetLetters.charAt(2*j) || ch == alphabetLetters.charAt(2*j+1)) {
					lettersThePlayerLikesToChange += alphabetLetters.charAt(2*j);
					break;
				}
			}
		}
		return lettersThePlayerLikesToChange;
	}

	private String checkLettersThatShallBeChanged(Player player, String lettersThePlayerLikesToChange, String lettersPlayerHasInHand) {
		String lettersThatShalldBeChanged = "";
		for (int i = 0; i < lettersThePlayerLikesToChange.length(); i++) {
			char ch = lettersThePlayerLikesToChange.charAt(i);
			for (int j = 0; j < lettersPlayerHasInHand.length(); j++) {
				if(ch == lettersPlayerHasInHand.charAt(j)) {
					lettersThatShalldBeChanged += ch;
					for (int k = 0; k < player.getLetters().size(); k++) {
						if(ch == player.getLetters().get(k).getCh()) {
							player.getLetters().remove(k);
							break;
						}
					}
					break;
				}
			}
		}
		return lettersThatShalldBeChanged;
	}


}
