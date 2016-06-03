package com.spaghettisoft.component.dontgetangry;

public class Token {
	public int id, i, j;
	public boolean started = false;
	public Player player;
	public String direction;
	private int diagonalSquaresLeft;
	public boolean finished;
	
	public boolean hasFinished(){
		return finished;
	}
	
	public boolean hasReachedEnd() {
		int turn = player.getTurn();
		if (turn == 1 && i == 0 && j == 0) {
			diagonalSquaresLeft = player.diagSquaresLeft;
			return true;
		} else if (turn == 2 && i == 0 && j == 10) {
			diagonalSquaresLeft = player.diagSquaresLeft;
			return true;
		} else if (turn == 3 && i == 10 && j == 10) {
			diagonalSquaresLeft = player.diagSquaresLeft;
			return true;
		} else if (turn == 4 && i == 10 && j == 0) {
			diagonalSquaresLeft = player.diagSquaresLeft;
			return true;
		} else {
			return false;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Token(Player player) {
		this.player = player;
		direction = Directions.STRAIGHT;
		finished = false;
	}

	public void move(int squares) {
		if (squares != 0) {
			System.out.println(player.name + " moves " + squares + " squares.");
			DontGetAngry.pause();
		}
		Board.clear(i, j);
		if (direction == Directions.STRAIGHT) {
			goStraight(squares);
		}
		if (direction == Directions.DIAGONAL) {
			goDiagonally(squares);
		}
		Board.set(i, j, player);
		Board.print();
	}

	public void starts() {
		this.started = true;
	}

	public boolean hasStarted() {
		return started;
	}

	public void setStartingPosition() {
		if (player.turn == 1) {
			i = 0;
			j = 1;
		} else if (player.turn == 2) {
			i = 1;
			j = 10;
		} else if (player.turn == 3) {
			i = 10;
			j = 9;
		} else if (player.turn == 4) {
			i = 9;
			j = 0;
		}
	}

	public void goDiagonally(int squares) {
		
		if(squares<=diagonalSquaresLeft){
			int turn = player.getTurn();
			for (int k = 0; k < squares; k++) {
				if (turn == 1) {
					i++;
					j++;
				} else if (turn == 2) {
					i++;
					j--;
				} else if (turn == 3) {
					i--;
					j--;
				} else if (turn == 4) {
					i--;
					j++;
				}
				diagonalSquaresLeft--;
				if(diagonalSquaresLeft <=0){
					finished = true;
					break;
				}
			}
		}else{
			System.out.println("Can't move "+squares+" squares!" );
		}
	}

	public void goStraight(int squares) {
		for (int k = 0; k < squares; k++) {
			if(hasReachedEnd()){
				direction=Directions.DIAGONAL;
				goDiagonally(squares-k);
				break;
			}
			if (i == 0) {
				if (j == 10) {
					i++;
				} else {
					j++;
				}
			} else if (j == 10) {
				if (i == 10) {
					j--;
				} else {
					i++;
				}
			} else if (i == 10) {
				if (j == 0) {
					i--;
				} else {
					j--;
				}
			} else if (j == 0) {
				if (i == 0) {
					j++;
				} else {
					i--;
				}
			}
			
		}
	}
}
