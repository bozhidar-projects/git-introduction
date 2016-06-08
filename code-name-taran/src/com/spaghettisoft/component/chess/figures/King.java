package com.spaghettisoft.component.chess.figures;

import javax.naming.InvalidNameException;

import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.players.Move;

public class King extends AbstractFigure{
	
	public static final String NAME = "King"; 
	private static final int MAX_SKIPPED_NODES_FOR_NORMAL_MOVE = 1;
	private static final int MAX_SKIPPED_NODES_FOR_FIRST_SIDE_DIRECTION_MOVE = 2;

	
	public King(Color figureColor) throws InvalidNameException {
		super(figureColor, King.NAME);
		
	}

	@Override
	public boolean validMove(Move move) {
		int row = move.getSubtractionbetweenRows();
		int colum = move.getSubtractionbetweenColums();
		boolean result;
		if (this.isFirstMove()) {
			 result = (row != 0 || colum != 0) && (Math.abs(row) <= MAX_SKIPPED_NODES_FOR_NORMAL_MOVE && Math.abs(colum) <= MAX_SKIPPED_NODES_FOR_FIRST_SIDE_DIRECTION_MOVE);

		} else {
			 result = (row != 0 || colum != 0) && (Math.abs(row) <= MAX_SKIPPED_NODES_FOR_NORMAL_MOVE && Math.abs(colum) <= MAX_SKIPPED_NODES_FOR_NORMAL_MOVE);

		}

		return result;
	}
}
