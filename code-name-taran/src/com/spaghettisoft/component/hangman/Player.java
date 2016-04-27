package com.spaghettisoft.component.hangman;

import java.util.Random;

public class Player {
	
	private String name;
//	private static String WORD;
	private static final char LIFE = '@';
	private static final char DEAD = 'X';
	private static final int NUMBER_OF_LIFES = 10;
	private int lifeCounter;
	private char[] lifes = new char[NUMBER_OF_LIFES];
	private boolean myTurn = false;
	
	
	public Player(String name){
		setName(name);
		lifeCounter = NUMBER_OF_LIFES;
		for(int i = 0; i < NUMBER_OF_LIFES; i++){
			lifes[i] = LIFE;
		}		
	}
	
	public void munisLife(){
		lifes[--lifeCounter] = DEAD;
	}
	
	public char guess(){		
		Random r = new Random();
		int c = r.nextInt(26) + (byte)'A';
		return (char)c;			
	}
	
	public boolean isDead(){
		return lifeCounter == 0;
	}
	
	public String toString(){
		String result = "Name : " + this.name + "\nlifes : " + this.lifeCounter;
		return result;
	}
	
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public char[] getLifes(){
		return lifes;
	}
	
	public int getLifeCounter(){
		return lifeCounter;
	}
	
	public boolean getMyTurn(){
		return myTurn;
	}
	
	protected void setMyTurn(boolean turn){
		myTurn = turn;
	}

}
