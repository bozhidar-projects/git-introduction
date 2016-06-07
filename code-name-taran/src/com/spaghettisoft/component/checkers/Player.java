package com.spaghettisoft.component.checkers;

public class Player {
	
	private String name;
	private String pawn;
	private Direction direction;

	public Player(String name, String pawn, Direction direction) {
		super();
		this.setName(name);
		this.pawn = pawn;
		this.direction = direction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPawn() {
		return pawn;
	}

	public void setPawn(String pawn) {
		this.pawn = pawn;
	}
	
	public boolean isPlayersPawn(String pawn) {
		return this.pawn.compareToIgnoreCase(pawn) == 0;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
}
