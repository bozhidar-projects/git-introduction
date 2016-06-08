package com.spaghettisoft.component.scrabble;

public class Board {
	private Tile[][] tiles;

	Board() {
		tiles = createEmptyBoard();
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	private Tile[][] createEmptyBoard() {
		Tile[][] tiles = new Tile[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				Tile tile = new Tile("   ", true, 1, 1);
				tiles[i][j] = tile;
			}
		}
		setLetterMultipliers(tiles);
		setWordMultipliers(tiles);
		return tiles;
	}

	private void setLetterMultipliers(Tile[][] tiles) {

		int[] letterMultipliersCoordinates2 = {0,3, 0,11, 14,3, 14,11, 3,0, 3,14, 11,0, 11,14,
				6,6, 8,8, 6,8, 8,6,
				2,6, 3,7, 2,8,   12,6, 11,7, 12,8,   6,2, 7,3, 8,2,   6,12, 7,11, 8,12};
		for (int i = 0; i < letterMultipliersCoordinates2.length/2; i++) {
			tiles[letterMultipliersCoordinates2[i*2]][letterMultipliersCoordinates2[i*2+1]].setMultiplierLetter(2);
			tiles[letterMultipliersCoordinates2[i*2]][letterMultipliersCoordinates2[i*2+1]].setContent(" ^ ");
		}
		int[] letterMultipliersCoordinates3 = {1,5, 1,9, 5,5, 5,9, 9,5, 9,9, 13,5, 13,9, 5,1, 9,1, 5,13, 9,13};
		for (int i = 0; i < letterMultipliersCoordinates3.length/2; i++) {
			tiles[letterMultipliersCoordinates3[i*2]][letterMultipliersCoordinates3[i*2+1]].setMultiplierLetter(3);
			tiles[letterMultipliersCoordinates3[i*2]][letterMultipliersCoordinates3[i*2+1]].setContent(" ~ ");
		}
	}

	private void setWordMultipliers(Tile[][] tiles) {
		tiles[7][7].setMultiplierWord(2); 	tiles[7][7].setContent(" @ ");
		for (int i = 0; i < 4; i++) {
			tiles[i+1][i+1].setMultiplierWord(2); 	tiles[i+1][i+1].setContent(" . ");
			tiles[i+10][i+10].setMultiplierWord(2);	tiles[i+10][i+10].setContent(" . ");
			tiles[i+1][13-i].setMultiplierWord(2);	tiles[i+1][13-i].setContent(" . ");
			tiles[i+10][4-i].setMultiplierWord(2);	tiles[i+10][4-i].setContent(" . ");

		}
		int[] wordMultipliersCoordinates3 = {0, 0, 0, 7, 0, 14, 7, 0, 7, 14, 14, 0, 14, 7, 14, 14};
		for (int i = 0; i < wordMultipliersCoordinates3.length/2; i++) {
			tiles[wordMultipliersCoordinates3[i*2]][wordMultipliersCoordinates3[i*2+1]].setMultiplierWord(3);
			tiles[wordMultipliersCoordinates3[i*2]][wordMultipliersCoordinates3[i*2+1]].setContent(" * ");
		}
	}



	public void drawBoard() {
		System.out.println("        ++---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");
		System.out.println("        || 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |11 |12 |13 |14 |15 |");
		System.out.println("   +====++===+===+===+===+===+===+===+===+===+===+===+===+===+===+===+");
			for (int i = 0; i < 15; i++) {
				System.out.print("   | ");
				if(i<9) {
					System.out.print(" ");
				}
				System.out.print((i+1) + " ||");
				for (int j = 0; j < 15; j++) {
					String content = tiles[i][j].getContent();
					System.out.print(content + "|");
				}
				System.out.println();
				System.out.println("   +----++---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");
			}
			System.out.println();
	}


}
