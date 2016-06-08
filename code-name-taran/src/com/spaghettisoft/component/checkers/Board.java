package com.spaghettisoft.component.checkers;

public final class Board {

	public final String oPawn = "o";
	public final String xPawn = "x";

	private static final int ROW_COUNT = 8;
	private static final int COLUMN_COUNT = 8;

	private boolean finished = false;

	public String[][] board;

	public Board() {
		initialize();
	}

	public void draw() {

		int a = 0;
		int b = 0;
		System.out.printf("  0 1 2 3 4 5 6 7\n");
		for (int i = 0; i < board.length; i++) {

			System.out.print(a++);

			for (int j = 0; j < board[i].length; j++) {
				String sign = " ";

				if (board[i][j] != null) {
					sign = board[i][j];
				}

				System.out.print("|" + sign);
			}

			System.out.println("|" + b++);
		}
		System.out.printf("  0 1 2 3 4 5 6 7\n");
	}

	public void move(Player player, Move move) {
		// Move current player's pawn
		board[move.getTo().y][move.getTo().x] = board[move.getFrom().y][move.getFrom().x];
		board[move.getFrom().y][move.getFrom().x] = null;

		// Capture opponent player's pawn
		Point middlePoint = new Point((move.getTo().y - move.getFrom().y) / 2 + move.getFrom().y,
				(move.getTo().x - move.getFrom().x) / 2 + move.getFrom().x);
		board[middlePoint.y][middlePoint.x] = null;

		if ((player.getDirection() == Direction.UP && move.getTo().y == 0)
				|| (player.getDirection() == Direction.DOWN && move.getTo().y == 7)) {
			board[move.getTo().y][move.getTo().x] = board[move.getTo().y][move.getTo().x].toUpperCase();
		}

		if (!hasDifferentPawns()) {
			System.out.println("Congratulations " + player.getName() + ", you are the winner!");
			finished = true;
		}

	}

	// checks whether has a different pawns in the board
	public boolean hasDifferentPawns() {

		boolean xFound = false;
		boolean oFound = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j] != null) {
					if (!xFound && "x".compareToIgnoreCase(board[i][j]) == 0) {
						xFound = true;
					}

					if (!oFound && "o".compareToIgnoreCase(board[i][j]) == 0) {
						oFound = true;
					}
				}

				if (xFound && oFound) {
					return true;
				}
			}
		}
		return false;
	}

	// checks whether pawn has a move or not
	public boolean hasMoves() {

		boolean canMove = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				canMove = canMove || canMove(new Point(i, j));
			}
		}
		return canMove;
	}

	private boolean canMove(Point position) {

		return false;
	}

	private void initialize() {
		board = new String[ROW_COUNT][COLUMN_COUNT];
		placePools();
	}

	private void placePools() {
		placeOddPoolsOnRow(0, xPawn);
		placeEvenPoolsOnRow(1, xPawn);
		placeOddPoolsOnRow(2, xPawn);

		placeEvenPoolsOnRow(5, oPawn);
		placeOddPoolsOnRow(6, oPawn);
		placeEvenPoolsOnRow(7, oPawn);
	}

	private void placeEvenPoolsOnRow(int row, String poolType) {
		for (int i = 0; i < board[row].length; i += 2) {
			board[row][i] = poolType;
		}
	}

	private void placeOddPoolsOnRow(int row, String poolType) {
		for (int i = 1; i < board[row].length; i += 2) {
			board[row][i] = poolType;
		}
	}

	public boolean isLegalMove(Move move, Player player) {

		if (!isMoveInRange(move)) {
			System.out.println("Move out of range!");
			return false;
		}

		if (!player.isPlayersPawn(board[move.getFrom().y][move.getFrom().x])) {
			System.out.println("Wrong pawn!");
			return false;
		}

		if (isKingMove(move, player) && !isKing(move.getFrom())) {
			System.out.println("Not a king");
			return false;
		}

		if (!isEmpty(move.getTo())) {
			System.out.println("Destination not empty");
			return false;
		}

		if (isDoubleDiagonalMove(move)) {
			Point middlePoint = new Point((move.getTo().y - move.getFrom().y) / 2 + move.getFrom().y,
					(move.getTo().x - move.getFrom().x) / 2 + move.getFrom().x);

			if (isEmpty(middlePoint)) {
				System.out.println("Can't jump over two empty boxes!");
				return false;
			}

			if (!isOppositePlayersPawn(player, middlePoint)) {
				System.out.println("Can't capture own pawn!");
				return false;
			}

		} else if (!isSingleDiagonalMove(move)) {
			System.out.println("Wrong move");
			return false;
		}

		return true;
	}

	// checks whether pawn is out of the range of the board
	private boolean isMoveInRange(Move move) {
		return isPointInRange(move.getFrom()) && isPointInRange(move.getTo());
	}

	// checks whether current position is in range of the board
	private boolean isPointInRange(Point point) {
		return point.y >= 0 && point.y < ROW_COUNT && point.x >= 0 && point.x < ROW_COUNT;
	}

	// checks whether current pawn is moved on diagonal thought one box
	private boolean isSingleDiagonalMove(Move move) {

		return Math.abs(move.getFrom().y - move.getTo().y) == 1 && Math.abs(move.getFrom().x - move.getTo().x) == 1;
	}

	// checks whether current pawn is moved on diagonal thought two boxes
	private boolean isDoubleDiagonalMove(Move move) {

		return Math.abs(move.getFrom().y - move.getTo().y) == 2 && Math.abs(move.getFrom().x - move.getTo().x) == 2;
	}

	// checks whether jumped pawn is an opposite player
	private boolean isOppositePlayersPawn(Player player, Point position) {
		return board[position.y][position.x] != null && !player.isPlayersPawn(board[position.y][position.x]);
	}

	// check whether next move is in the empty box
	private boolean isEmpty(Point position) {
		return board[position.y][position.x] == null;
	}

	private boolean isKingMove(Move move, Player player) {
		int shift = move.getTo().y - move.getFrom().y;

		return (player.getDirection() == Direction.UP && shift > 0)
				|| (player.getDirection() == Direction.DOWN && shift < 0);
	}

	// checks whether pawn is king
	private boolean isKing(Point position) {
		return board[position.y][position.x] != null
				&& ("" + board[position.y][position.x]).toUpperCase().equals((board[position.y][position.x]));
	}

	public boolean isFinished() {
		return finished;
	}

}
