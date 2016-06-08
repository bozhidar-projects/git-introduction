package com.spaghettisoft.component.chess.board;

import com.spaghettisoft.component.chess.figures.AbstractFigure;
import com.spaghettisoft.component.chess.figures.IFigures;

public class ChessBoard {
	
	
	public static final int CHESS_BOARD_SIZE = 8;
	public static final String[] columsLabel = { "A", "B", "C", "D", "E", "F", "G", "H" };
	private AbstractFigure[][] board;
	

	public ChessBoard() {
		this.board = new AbstractFigure[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE];
	}
		
	public void drawChessBoard() {
		System.out.println();
		drawFilesLabel();
		drawRowsSeparation();

		for (int i = 0; i < this.board.length; i++) {
			drawRanksLabel(CHESS_BOARD_SIZE-i);
			drawRow(i);
			drawRanksLabel(CHESS_BOARD_SIZE-i);
			drawRowsSeparation();
		}

		drawFilesLabel();
		System.out.println();
	}

	private void drawRow(int i) {
		System.out.print("   | ");
		for (int j = 0; j < this.board[0].length; j++) {
			if (this.board[i][j] != null) {
				System.out.print(this.board[i][j].getName() + " | ");
			} else {
				System.out.print("    | ");
			}
		}
	}

	private void drawRanksLabel(int arrayLenght) {
		System.out.print("	" + arrayLenght);
	}

	private void drawFilesLabel() {
		for (int i = 0; i < columsLabel.length; i++) {
			if (i == 0) {
				System.out.print("  	       " + columsLabel[i]);
			} else {
				System.out.print("     " + columsLabel[i]);
			}
		}
	}

	private void drawRowsSeparation() {
		System.out.println();
		for (int j = 0; j < 49; j++) {
			if (j == 0) {
				System.out.print("	    -");
			} else {
				System.out.print("-");
			}
		}
		System.out.println();
	}

	public AbstractFigure getFigure(int i, int j) {
		return this.board[i][j];
	}
	
	public void addFigure(int iIndex, int jIndex, AbstractFigure figure) {
		 this.board[iIndex][jIndex] = figure;
	}

	public int getBoardLenght() {
		return this.board.length;
	}
	
	
}
