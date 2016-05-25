package com.spaghettisoft.component.connectthefour;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.game.AbstractGame;

public class ConnectTheFour extends AbstractGame {
	
	private Grid grid;
	
	public ConnectTheFour(Grid grid) {
		this.grid = grid;
	}

	@Override
	public void show() {

	}
	
	@Override
	protected void printEndGameMessage() {
		String O = "O";
		String X = "X";
		
	}
	
	protected void printGameMessage(String ch) {
		if (isEnded()) {
			System.out.println("No winner yet!");
		} else {
			if (ch.contains("X")) {
				System.out.println("We have a winner! : Player " + 1);
//				System.out.println(container.toString());
			} else {
				System.out.println("We have a winner! : Player " + 2);
//				System.out.println(container.toString());
			}
		}
	}

	@Override
	protected void drawGame() {
		for (int i = 0; i < grid.columnsLength(); i++) {
			for (int j = 0; j < grid.rowsLength() ; j++) {
				System.out.print(grid.getElement(i, j) + " | ");
			}
			System.out.println();
		}
	
	}

	@Override
	protected boolean isEnded() {
		boolean checkMessage = grid.check("X");
		return checkMessage;
	}

	@Override
	protected void processGame() {
		 Scanner input = new Scanner(System.in);
		 boolean playerChange = true;
         while(!isEnded()) {
        	 if (playerChange) {
        		 System.out.print("Player 1 - Choose a column: ");
     		     int column = input.nextInt();
     		     grid.dropCoin(column, "X"); 
     		     printGameMessage("X");
     		     playerChange = false;
        	 } else {
        		 System.out.print("Player 2 - Choose a column: ");
     		     int column = input.nextInt();
     		     grid.dropCoin(column, "O");
     		     printGameMessage("O");
     		     playerChange = true;	 
        	 }	
		}
        input.close();
	}

	public static void main(String[] args) {

		Grid grid = new Grid();
		
		ConnectTheFour ctf = new ConnectTheFour(grid);

		String[][] arr = { { " ", " ", " ", " ", " ", " ", " " },
				           { " ", " ", " ", " ", " ", " ", " " },
				           { " ", " ", " ", " ", " ", " ", " " },
				           { " ", " ", " ", " ", " ", " ", " " },
				           { " ", " ", " ", " ", " ", " ", " " },
				           { " ", " ", " ", " ", " ", " ", " " } };

		for (int i = 0; i < grid.rowsLength(); i++) {
			for (int j = 0; j < grid.columnsLength(); j++) {
				grid.setElement(i, j, arr[i][j]);
			}
		}
		
		ctf.processGame();
		
		

		
	

	}
}
