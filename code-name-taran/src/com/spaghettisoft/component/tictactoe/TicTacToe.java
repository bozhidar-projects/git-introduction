package com.spaghettisoft.component.tictactoe;

import com.spaghettisoft.component.game.AbstractGame;
import com.spaghettisoft.globals.StaticObjects;

public class TicTacToe extends AbstractGame {
	private static final int COLUMN_COUNT = 3;
	private static final int ROW_COUNT = 3;
	private String[][] board;

	private String currentSymbol = "X";

	public TicTacToe() {
		board = new String[ROW_COUNT][COLUMN_COUNT];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = " ";
			}
		}
	}

	@Override
	protected void printEndGameMessage() {

	}

	@Override
	protected void drawGame() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("|" + board[i][j]);
			}
			System.out.println("|");
		}
	}

	@Override
	protected boolean isEnded() {
		return checkGame();
	}

	@Override
	protected void processGame() {
		System.out.println("Enter move: ");
		int row = StaticObjects.scanner.nextInt();
		int column = StaticObjects.scanner.nextInt();

		board[row][column] = currentSymbol;

		currentSymbol = (currentSymbol == "X" ? "O" : "X");
	}

	private boolean checkGame() {
		return false;
	}
}
