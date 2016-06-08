package com.spaghettisoft.component.chess.figures;

import javax.naming.InvalidNameException;

import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.players.Move;


public class Rook  extends AbstractFigure{
	
	public static final String NAME = "Rook"; 
	public Rook(Color figureColor) throws InvalidNameException {
		super(figureColor, Rook.NAME);
		
	}

	@Override
	public boolean validMove(Move move) {
		int row = move.getSubtractionbetweenRows();
		int colum = move.getSubtractionbetweenColums();
		
//		boolean result = (row == 0 || colum == 0) && (row != 0 || colum != 0);
		boolean result = (row == 0 && colum != 0) || (row != 0 && colum == 0);
		return result;
	}

}
