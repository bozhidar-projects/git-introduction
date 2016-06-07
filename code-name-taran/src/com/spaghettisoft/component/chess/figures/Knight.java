package com.spaghettisoft.component.chess.figures;

import javax.naming.InvalidNameException;

import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.players.Move;


public class Knight extends AbstractFigure{
	
	private static final int MAX_SKIPPED_NODES_FOR_NORMAL_STRAIGHT_MOVE = 2;
	private static final int MAX_SKIPED_NODES_FOR_NORMAL_SIDE_MOVE = 1;
	
	
	public static final String NAME = "Knight"; 
	public Knight(Color figureColor) throws InvalidNameException {
		super(figureColor, Knight.NAME);
		
	}

	@Override
	public boolean validMove(Move move) {
		int row = move.getSubtractionbetweenRows();
		int colum = move.getSubtractionbetweenColums();
		
		boolean isMovedColumns = (Math.abs(row) == MAX_SKIPPED_NODES_FOR_NORMAL_STRAIGHT_MOVE) && (Math.abs(colum) == MAX_SKIPED_NODES_FOR_NORMAL_SIDE_MOVE);
		boolean isMovedRows = (Math.abs(colum) == MAX_SKIPPED_NODES_FOR_NORMAL_STRAIGHT_MOVE) && (Math.abs(row) == MAX_SKIPED_NODES_FOR_NORMAL_SIDE_MOVE);

		boolean result = isMovedColumns || isMovedRows;
		return result;
	}
}
