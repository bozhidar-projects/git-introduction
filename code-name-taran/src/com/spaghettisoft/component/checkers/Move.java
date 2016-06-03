package com.spaghettisoft.component.checkers;

public class Move {

	private Point from;
	private Point to;

	public Move(Point from, Point to) {
		super();
		this.from = from;
		this.to = to;
	}

	public Point getFrom() {
		return from;
	}

	public Point getTo() {
		return to;
	}

}
