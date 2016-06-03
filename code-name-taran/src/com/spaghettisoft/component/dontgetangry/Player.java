package com.spaghettisoft.component.dontgetangry;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {
	public String name;
	public int turn;
	public Stack<Token> notInGameTokens = new Stack<Token>();
	public List<Token> inGameTokens = new ArrayList<>();
	public int diagSquaresLeft = 4;
	public String LETTER ;
	
	public Player(String name) {
		this.name = name;
		
		for (int i = 0; i < 4; i++) {
			notInGameTokens.add(new Token(this));
		}
		this.LETTER ="[".concat(name.substring(0, 1) + "]");
	}
	
	public boolean hasWon(){
		if(diagSquaresLeft <= 0){
			return true;
		}
		return false;
	}
	
	public boolean hasTokensToStart(){
		if(notInGameTokens.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean hasATokenInGame(){
		return inGameTokens.size() > 0;
	}
	
	public boolean hasMoreThanOneTokenInGame(){
		return inGameTokens.size() > 1;
	}
	
	public Token getNewTokenInGame()throws NoPiecesLeftException {
		if (notInGameTokens.size() == 0) {
			throw new NoPiecesLeftException();
		} else {
			Token token = notInGameTokens.pop();
			token.starts();
			token.setStartingPosition();
			inGameTokens.add(token);
			return token;
		}
		
	}

	public int getPieces() {
		return notInGameTokens.size();
	}

	public void remove(Token token) {
		inGameTokens.remove(token);
		notInGameTokens.push(token);
		token.started=false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

}