package com.spaghettisoft.component.chess.figures;

import javax.naming.InvalidNameException;

import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.players.Move;

public class Bishop  extends AbstractFigure{
	
	public static final String NAME = "Bishop"; 
	
	
	public Bishop(Color figureColor) throws InvalidNameException {
		super(figureColor, Bishop.NAME);
		
	}

	@Override
	public boolean validMove(Move move) {

		int row = move.getSubtractionbetweenRows();
		int colum = move.getSubtractionbetweenColums();
		
		boolean result = (row != 0 && colum != 0) && (Math.abs(row) == Math.abs(colum));
		return result;
		
	}
	
}
