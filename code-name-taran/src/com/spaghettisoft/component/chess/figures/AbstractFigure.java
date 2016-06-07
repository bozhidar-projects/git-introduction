package com.spaghettisoft.component.chess.figures;

import javax.naming.InvalidNameException;

public abstract class AbstractFigure implements IFigures {
	
	public enum  Color {
		WHITE, BLACK
	}
	
	private String name;
	private Color figureColor;
	private int moveCounter = 0;
	
	public AbstractFigure(Color figureColor, String name) throws InvalidNameException {
		this.name = formsNameFigure(figureColor, name);
		this.figureColor = figureColor;
	}
	

	private String formsNameFigure(Color color, String name) throws InvalidNameException {
		if (color != null) {
			String colorFirstLetter = color.name().substring(0, 1).toUpperCase();
			String nameFirstTwoLetters = name.substring(0, 2).toUpperCase();
			return colorFirstLetter.concat(nameFirstTwoLetters);
		}
		throw new InvalidNameException("The colors can be null only Black or White");
	}
	
	@Override
	public String toString() {
		return "Name: " + this.getName() + " number of moves made during the game: " + getMoveCounter(); 
	}

	public boolean isFirstMove() {
		boolean result = this.getMoveCounter() == 0;
		return result;
	}
	
	public String getName() {
		return name;
	}

	public Color getColor() {
		return figureColor;
	}

	public int getMoveCounter() {
		return moveCounter;
	}

	public void incrementMoveCounter() {
		this.moveCounter++;
	}


}
