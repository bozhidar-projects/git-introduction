package com.spaghettisoft.component.chess.figures;

import javax.naming.InvalidNameException;

import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.players.Move;

public class Pawn extends AbstractFigure {

	public static final String NAME = "Pawn";
	private static final int MAX_SKIPED_NODES_FOR_FIRST_ROW_MOVE = 2;
	private static final int MAX_SKIPED_NODES_FOR_NORMAL_ROW_MOVE = 1;
	private static final int MAX_SKIPED_NODES_FOR_NORMAL_COLUM_MOVE = 1;

	public Pawn(Color figureColor) throws InvalidNameException {
		super(figureColor, Pawn.NAME);

	}

	@Override
	public boolean validMove(Move move) {

		boolean result = (this.getColor().equals(Color.WHITE)) ? 
				isValidMoveForWhite(move) : 
				isValidMoveForBlack(move);

		return result;
	}

	private boolean isValidMoveForWhite(Move move) {

		boolean result = false;
		int row = move.getSubtractionbetweenRows();

		if (this.isFirstMove()) {

			if (isSameColumn(move)) {
				result = ((row > 0) && (Math.abs(row) <= MAX_SKIPED_NODES_FOR_FIRST_ROW_MOVE));
			}
			if (isNeighborColumn(move)) {
				result = ((row > 0) && (Math.abs(row) == MAX_SKIPED_NODES_FOR_NORMAL_ROW_MOVE));
			}

		} else {

			if (isNeighborOrSameColumn(move)) {
				result = ((row > 0) && (Math.abs(row) == MAX_SKIPED_NODES_FOR_NORMAL_ROW_MOVE));
			}

		}

		return result;
	}

	private boolean isValidMoveForBlack(Move move) {

		boolean result = false;
		int row = move.getSubtractionbetweenRows();

		if (this.isFirstMove()) {

			if (isSameColumn(move)) {
				result = ((row < 0) && (Math.abs(row) <= MAX_SKIPED_NODES_FOR_FIRST_ROW_MOVE));
			}
			if (isNeighborColumn(move)) {
				result = ((row < 0) && (Math.abs(row) == MAX_SKIPED_NODES_FOR_NORMAL_ROW_MOVE));
			}

		} else {

			if (isNeighborOrSameColumn(move)) {
				result = ((row < 0) && (Math.abs(row) == MAX_SKIPED_NODES_FOR_NORMAL_ROW_MOVE));
			}

		}

		return result;
	}


	public boolean isNeighborOrSameColumn(Move move) {
		boolean currectMoveForColum = isNeighborColumn(move) || isSameColumn(move);
		return currectMoveForColum;
	}
	
	public boolean isNeighborColumn(Move move) {
		boolean currectMoveForColum;
		int colum = move.getSubtractionbetweenColums();
		currectMoveForColum = (Math.abs(colum) == MAX_SKIPED_NODES_FOR_NORMAL_COLUM_MOVE);
		return currectMoveForColum;
	}

	public boolean isSameColumn(Move move) {
		boolean currectMoveForColum;
		int colum = move.getSubtractionbetweenColums();
		currectMoveForColum = (colum == 0);
		return currectMoveForColum;
	}
	

}
