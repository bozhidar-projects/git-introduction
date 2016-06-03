package com.spaghettisoft.component.checkers;

import com.spaghettisoft.component.game.AbstractGame;
import com.spaghettisoft.globals.StaticObjects;

public class Checkers extends AbstractGame {

	private State currentState = State.STARTING;
	private Board board = new Board();
	private Player p1;
	private Player p2;
	private Player currentPlayer;

	public Checkers() {
		this.board = new Board();
	}

	@Override
	protected void printEndGameMessage() {
		System.out.println("The game is over! ;)");
		System.out.println("If you want to play again, please select an option from the menu below");
	}

	@Override
	protected void drawGame() {
		board.draw();
	}

	@Override
	protected boolean isEnded() {
		return board.isFinished();
	}

	@Override
	protected void processGame() {

		if (currentState == State.STARTING) {

			System.out.print("Enter name of the first to move: ");
			String name = StaticObjects.scanner.nextLine();
			p1 = new Player(name, "o", Direction.UP);
			System.out.print("Enter name of " + name + "'s opponent: ");
			String opponentName = StaticObjects.scanner.nextLine();
			p2 = new Player(opponentName, "x", Direction.DOWN);
			System.out.println(name + ", you will play with 'o' pawns.");
			System.out.println(opponentName + ", you will play with 'x' pawns.");
			currentPlayer = p1;

			currentState = State.PLAYING;

		} else if (currentState == State.PLAYING) {

			Move move;
			while ((move = getNextMove(currentPlayer)) == null);
			
			board.move(currentPlayer, move);

			if (currentPlayer == p1) {
				currentPlayer = p2;
			} else {
				currentPlayer = p1;
			}
		}
	}

	private Move getNextMove(Player player) {

		System.out.println(player.getName() + "'s move: ");

		String movedPawn = StaticObjects.scanner.nextLine();

		if (movedPawn.length() != 5) {
			return null;
		}

		Move move;

		try {
			int fromX = Integer.parseInt("" + movedPawn.charAt(0));
			int fromY = Integer.parseInt("" + movedPawn.charAt(1));

			int toX = Integer.parseInt("" + movedPawn.charAt(3));
			int toY = Integer.parseInt("" + movedPawn.charAt(4));

			move = new Move(new Point(fromX, fromY), new Point(toX, toY));

			if (!board.isLegalMove(move, currentPlayer)) {
				System.out.println("Illegal move, please try again!");
				return null;
			}
		} catch (NumberFormatException e) {
			System.out.println("Illegal move format, please try again!");
			return null;
		}
		return move;
	}
}
