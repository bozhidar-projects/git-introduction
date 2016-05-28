package com.spaghettisoft.component.connectthefour;

import com.spaghettisoft.component.game.AbstractGame;
import com.spaghettisoft.globals.StaticObjects;

public class ConnectTheFour extends AbstractGame {

	private Grid grid;
	private boolean playerChange;

	public ConnectTheFour() {
		initialize();
	}

	@Override
	protected void printEndGameMessage() {
		drawGame();
		if (playerChange) {
			System.out.println("We have a winner! : Player " + 1);
		} else {
			System.out.println("We have a winner! : Player " + 2);
		}
		initialize();
	}

	@Override
	protected void drawGame() {
		for (int i = 0; i < grid.rowsLength(); i++) {
			for (int j = 0; j < grid.columnsLength(); j++) {
				System.out.print(grid.getElement(i, j) + " | ");
			}
			System.out.println();
		}

	}

	@Override
	protected boolean isEnded() {
		boolean checkMessage = grid.check("X");
		return checkMessage;
	}

	@Override
	protected void processGame() {
			if (playerChange) {
				System.out.print("Player 1 - Choose a column: ");
				int column = StaticObjects.scanner.nextInt();
				grid.dropCoin(column, "X");
			} else {
				System.out.print("Player 2 - Choose a column: ");
				int column = StaticObjects.scanner.nextInt();
				grid.dropCoin(column, "O");
			}
			playerChange = !playerChange;
	}

	public void initialize() {
		grid = new Grid();

		String[][] arr = { { " ", " ", " ", " ", " ", " ", " " },
				{ " ", " ", " ", " ", " ", " ", " " },
				{ " ", " ", " ", " ", " ", " ", " " },
				{ " ", " ", " ", " ", " ", " ", " " },
				{ " ", " ", " ", " ", " ", " ", " " },
				{ " ", " ", " ", " ", " ", " ", " " } };

		for (int i = 0; i < grid.rowsLength(); i++) {
			for (int j = 0; j < grid.columnsLength(); j++) {
				grid.setElement(i, j, arr[i][j]);
			}
		}

		playerChange = true;
	}
}
