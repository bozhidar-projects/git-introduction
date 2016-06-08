package com.spaghettisoft.component.chess.figures;

import javax.naming.InvalidNameException;

import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.players.Move;


public class Queen extends AbstractFigure{
	
	public static final String NAME = "Queen"; 
	public Queen(Color figureColor) throws InvalidNameException {
		super(figureColor, Queen.NAME);
		
	}

	@Override
	public boolean validMove(Move move) {
		int row = move.getSubtractionbetweenRows();
		int colum = move.getSubtractionbetweenColums();

		boolean isDiagonals = (row != 0 && colum != 0) && (Math.abs(row) == Math.abs(colum));
		boolean idHorizontalOrVertical = (row == 0 && colum != 0) || (row != 0 && colum == 0);

		boolean result = isDiagonals || idHorizontalOrVertical;
		return result;
	}
}
