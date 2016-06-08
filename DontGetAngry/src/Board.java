
public class Board {

	public static String[][] board = new String[11][11];
	public static final String BLANK = "[ ]";	
	public static final String EMPTY = "   ";
	
	public static String[][] getBoard() {
		return board;
	}

	public static void clear(int i, int j) {
		board[i][j] = BLANK;
	}

	public static void set(int i, int j, Player player) {
		board[i][j] = player.LETTER;
	}

	public static void fill() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = BLANK;
			}
		}

		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board.length - 1; j++) {
				board[i][j] = EMPTY;
				if((i==j) && (i!= (board.length-1)/2)){
					board[i][j] = BLANK;
				}
				if(((i+j)==board.length-1) && (i!= (board.length-1)/2)){
					board[i][j] = BLANK;
				}
			}
		}

	}

	public static void print() {

		System.out.println();
		System.out.print(Game.players.get(0).name+" Tokens: "+Game.players.get(0).notInGameTokens.size() );
		System.out.print("\t\t\t\t\t\t" + Game.players.get(1).name+" Tokens: "+Game.players.get(1).notInGameTokens.size() );
		System.out.println("\n");
		for (int i = 0; i < board.length; i++) {
			System.out.print("\t\t");
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		try {
			System.out.print(Game.players.get(3).name+" Tokens: "+Game.players.get(3).notInGameTokens.size() );
		} catch (IndexOutOfBoundsException e){}
		try{
			System.out.print("\t\t\t\t\t\t" + Game.players.get(2).name+" Tokens: "+Game.players.get(2).notInGameTokens.size() );
		} catch (IndexOutOfBoundsException e){}
		System.out.println();

	}
}
