package com.spaghettisoft.component.chess.chessgame;

import javax.naming.InvalidNameException;

import com.spaghettisoft.component.chess.board.ChessBoard;
import com.spaghettisoft.component.chess.board.ChessBoardManager;
import com.spaghettisoft.component.chess.exceptions.EmtyFieldException;
import com.spaghettisoft.component.chess.exceptions.IllegalCastingMoveException;
import com.spaghettisoft.component.chess.exceptions.IllegalEnPassantMoveException;
import com.spaghettisoft.component.chess.exceptions.IllegalMoveCheckException;
import com.spaghettisoft.component.chess.exceptions.IllegalMoveException;
import com.spaghettisoft.component.chess.exceptions.IlligalCapturedKingException;
import com.spaghettisoft.component.chess.exceptions.IncorectColorCaptureException;
import com.spaghettisoft.component.chess.exceptions.IncorectColorMoveException;
import com.spaghettisoft.component.chess.exceptions.KingCheckException;
import com.spaghettisoft.component.chess.exceptions.SameFieldException;
import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.gamelogic.GameLogic;
import com.spaghettisoft.component.chess.players.Move;
import com.spaghettisoft.component.chess.players.Player;
import com.spaghettisoft.component.game.AbstractGame;

public class ChessGame extends AbstractGame {

	private GameLogic gameLogic;
	private ChessBoard chessBoard;
	private ChessBoardManager chessBoardManager;
	private Player whitePlayer;
	private Player blackPlayer;
	private Player currentPlayer;
	private boolean gameEnded;

	public ChessGame(String whitePlayerName, String blackPlayerName) {
		whitePlayer = new Player(Color.WHITE, whitePlayerName);
		blackPlayer = new Player(Color.BLACK, blackPlayerName);
	    this.currentPlayer = whitePlayer;
		initialize(whitePlayer, blackPlayer);
	}

	public void initialize(Player whitePlayer, Player blackPlayer) {
		chessBoard = new ChessBoard();
		chessBoardManager = new ChessBoardManager(chessBoard);
		chessBoardManager.fillChessBoardWithFigures();
		gameLogic = new GameLogic(whitePlayer, blackPlayer, chessBoard);


	}

	@Override
	protected void printEndGameMessage() {
	}

	@Override
	protected void drawGame() {
		this.chessBoard.drawChessBoard();
	}

	@Override
	protected boolean isEnded() {
		return gameEnded;
	}
	
	@Override
	protected void processGame() {

		try {
			
			Move playerMove = currentPlayer.askForMove();
			if (playerMove == null) {
				gameEnded = true;
				return;
			}

			if (gameLogic.move(currentPlayer, playerMove)) {

				currentPlayer = gameLogic.getNextPlayer(currentPlayer);

				gameEnded = gameLogic.isGameEnded(currentPlayer);
				if (gameEnded) {
					return;
				}
				
			}
		} catch (IllegalEnPassantMoveException e) {
			System.out.println("EnPassant move is not posible");
		} catch (SameFieldException e) {
			System.out.println("You can't move on the same field");
		} catch (EmtyFieldException e) {
			System.out.println("În the chosen field doesn't exist figure");
		} catch (IncorectColorMoveException e) {
			System.out.println("The figure that moves is enemy");
		} catch (IncorectColorCaptureException e) {
			System.out.println("It is not possible that figure to be captured because it is not an enemy");
		} catch (IllegalMoveException e) {
			System.out.println("The rules do not allow the selected figure to move in this way");
		} catch (KingCheckException e) {
			System.out.println("can not perform a move that does not save the king of chess");
		} catch (IlligalCapturedKingException e) {
			System.out.println("The king can not be captured");
		} catch (IllegalMoveCheckException e) {
			System.out.println("Can not figure moved if it leaves the king in chess");
		} catch (InvalidNameException e) {
			System.out.println("Figure with that name does not exist");
		} catch (IllegalCastingMoveException e) {
			System.out.println("Castling is impossible");	
		}

	}

	
}
