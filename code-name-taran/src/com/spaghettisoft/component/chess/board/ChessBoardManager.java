package com.spaghettisoft.component.chess.board;

import javax.naming.InvalidNameException;

import com.spaghettisoft.component.chess.figures.AbstractFigure;
import com.spaghettisoft.component.chess.figures.Bishop;
import com.spaghettisoft.component.chess.figures.IFigures;
import com.spaghettisoft.component.chess.figures.King;
import com.spaghettisoft.component.chess.figures.Knight;
import com.spaghettisoft.component.chess.figures.Pawn;
import com.spaghettisoft.component.chess.figures.Queen;
import com.spaghettisoft.component.chess.figures.Rook;
import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;

public class ChessBoardManager {

	ChessBoard chessBoard;

	public ChessBoardManager(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}

	public void fillChessBoardWithFigures() {
		try {
			setFirstRowFigures(1, Color.BLACK);
			setFirstRowFigures(8, Color.WHITE);
			for (int i = 0; i < this.chessBoard.getBoardLenght(); i++) {
				for (int j = 0; j < this.chessBoard.getBoardLenght(); j++) {

					if (i == 1) {
						this.chessBoard.addFigure(i, j,  setSecondRowFigures(Color.BLACK)); 
					}
					if (i == 6) {
						this.chessBoard.addFigure(i, j,  setSecondRowFigures(Color.WHITE));
					}
					
				}
			}


		} catch (InvalidNameException e) {
			System.out.println("Figure with that name does not exist");
		}

	}

	private AbstractFigure setSecondRowFigures(Color color) throws InvalidNameException {
			return new Pawn(color);
	}

	private void setFirstRowFigures(int row, Color color) throws InvalidNameException {
		int row1 = row - 1;
		this.chessBoard.addFigure(row1, 0, new Rook(color));
		this.chessBoard.addFigure(row1, 1, new Knight(color));
		this.chessBoard.addFigure(row1, 2, new Bishop(color));
		this.chessBoard.addFigure(row1, 3, new Queen(color));
		this.chessBoard.addFigure(row1, 4, new King(color));
		this.chessBoard.addFigure(row1, 5, new Bishop(color));
		this.chessBoard.addFigure(row1, 6, new Knight(color));
		this.chessBoard.addFigure(row1, 7, new Rook(color));

	}


}
