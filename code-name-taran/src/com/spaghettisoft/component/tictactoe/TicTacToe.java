package com.spaghettisoft.component.tictactoe;

import com.spaghettisoft.component.game.AbstractGame;
import com.spaghettisoft.globals.StaticObjects;

public class TicTacToe extends AbstractGame {
	private static final int ROW_COUT = 3;
	private static final int COLUMN_COUNT = 3;

	private String[][] board;
	private boolean hasGameFinished;
	private String currentSymbol = "X";

	public TicTacToe() {
		board = new String[ROW_COUT][COLUMN_COUNT];
		hasGameFinished = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = " ";
			}
		}

	}

	@Override
	protected void printEndGameMessage() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processGame() {
		System.out.println("Enter move: ");
		int row = StaticObjects.scanner.nextInt();
		int column = StaticObjects.scanner.nextInt();

		board[row][column] = currentSymbol;
		currentSymbol = (currentSymbol == "X" ? "O" : "X");

	}

	@Override
	protected void drawGame() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.println("|" + board[i][j]);
			}
			System.out.println("|");
		}

	}

	@Override
	protected boolean isEnded() {

		return hasGameFinished;
	}

}
