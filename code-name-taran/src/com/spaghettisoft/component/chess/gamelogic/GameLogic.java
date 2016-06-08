package com.spaghettisoft.component.chess.gamelogic;

import com.spaghettisoft.component.chess.board.ChessBoard;
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
import com.spaghettisoft.component.chess.figures.AbstractFigure;
import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.figures.Bishop;
import com.spaghettisoft.component.chess.figures.King;
import com.spaghettisoft.component.chess.figures.Knight;
import com.spaghettisoft.component.chess.figures.Pawn;
import com.spaghettisoft.component.chess.figures.Queen;
import com.spaghettisoft.component.chess.figures.Rook;
import com.spaghettisoft.component.chess.players.Move;
import com.spaghettisoft.component.chess.players.Player;
import com.spaghettisoft.component.chess.userinput.UserInputValidatorAndConvertor;
import com.spaghettisoft.globals.StaticObjects;

import javax.naming.InvalidNameException;

public class GameLogic {

    private static final int SEVEN = 7;
    private static final int FIFTY_MOVES_RULE_VALUE = 50;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int MINUS_ONE = -1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    //R		//L 	 //D	 //U      //DR 	  //DL	   //LU		 //RU
    private static final int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 },
            { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

    private Player whitePlayer;
    private Player blackPlayer;
    private ChessBoard chessBoard;
    private Move lastEnemyMove;
    private int numberOfFicgureOnChessBoard = 32;
    private int numberOfMovesWithoutMovePawn = 0;
    private int numberOfMovesWithoutCapture = 0;
    private boolean isCurentPlayerInCheck = false;

    public GameLogic(Player whitePlayer, Player blackPlayer,
            ChessBoard chessBoard) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.chessBoard = chessBoard;
    }

    public boolean move(Player player, Move move) throws EmtyFieldException,
            IncorectColorMoveException, IncorectColorCaptureException,
            IllegalMoveException, KingCheckException,
            IlligalCapturedKingException, IllegalMoveCheckException,
            IllegalEnPassantMoveException, SameFieldException,
            InvalidNameException, IllegalCastingMoveException {
        boolean result = false;
        if (canMove(player, move)) {
            AbstractFigure capturedFigure = this.chessBoard
                    .getFigure(move.getToRow(), move.getToColumn());
            AbstractFigure movedFigure = this.chessBoard
                    .getFigure(move.getFromRow(), move.getFromColumn());

            if (capturedFigure != null) {
                this.resetNumberOfMovesWithoutCapture();
                this.chessBoard.addFigure(move.getToRow(), move.getToColumn(),
                        null);
                this.decrementNumberOfFicgureOnTheChessBoard();
            } else {
                this.incrementNumberOfMovesWithoutCapture();
            }

            if (movedFigure.getName().equals(Pawn.NAME)) {
                this.resetNumberOfMovesWithoutMovePawn();
            } else {
                this.incrementNumberOfMovesWithoutMovePawn();
            }

            movedFigure.incrementMoveCounter();
            this.chessBoard.addFigure(move.getToRow(), move.getToColumn(),
                    this.chessBoard.getFigure(move.getFromRow(),
                            move.getFromColumn()));
            this.chessBoard.addFigure(move.getFromRow(), move.getFromColumn(),
                    null);
            this.setLastEnemyMove(move);
            checkFoPawnPromotion(player, move);
            result = true;

        }
        return result;
    }

    public Player getNextPlayer(Player currentPlayer) {
        if (currentPlayer.getColor().equals(Color.WHITE)) {
            return this.blackPlayer;
        } else {
            return this.whitePlayer;
        }
    }

    private boolean isCorrectColorFigureCurrentPosition(Player player,
            Move move) throws IncorectColorMoveException {
        Color figureColor = this.chessBoard
                .getFigure(move.getFromRow(), move.getFromColumn()).getColor();
        Color playerColor = player.getColor();
        boolean result = playerColor.equals(figureColor);
        return result;
    }

    private boolean isCorrectColorFigureChoosenForMovingPosition(Player player,
            Move move) throws IncorectColorCaptureException {
        boolean result = false;
        AbstractFigure figure;
        Color figureColor;
        Color playerColor;
        figure = this.chessBoard.getFigure(move.getToRow(), move.getToColumn());
        if (figure != null) {
            figureColor = this.chessBoard
                    .getFigure(move.getToRow(), move.getToColumn()).getColor();
            playerColor = player.getColor();
            result = playerColor.equals(figureColor);
        }
        return result;
    }

    private boolean isFromFieldEmpty(int row, int column)
            throws EmtyFieldException {
        boolean result = (this.chessBoard.getFigure(row, column) == null);
        return result;
    }

    private boolean canPawnMove(Move move) throws EmtyFieldException {
        AbstractFigure figure = this.chessBoard.getFigure(move.getToRow(),
                move.getToColumn());
        if (isEnPassant(move)) {
            return true;
        }
        if (figure == null && move.getSubtractionbetweenColums() == ZERO) {
            return true;
        }
        if ((figure != null)
                && (Math.abs(move.getSubtractionbetweenColums()) == ONE)) {
            return true;
        }

        return false;
    }

    private boolean checkForFiguresBetweenTwoFields(Move move) {
        boolean result = false;
        AbstractFigure figure;
        int currentRow = move.getFromRow();
        int currentColumn = move.getFromColumn();

        int[] directions = getDirection(move);
        int directionRow = directions[ZERO];
        int directionColum = directions[ONE];

        do {
            currentRow += directionRow;
            currentColumn += directionColum;

            if (!IsRowAndColumAreMatrixRange(currentRow, currentColumn)
                    || ((currentRow == move.getToRow())
                            && (currentColumn == move.getToColumn()))) {
                break;
            }
            figure = this.chessBoard.getFigure(currentRow, currentColumn);
            if (figure != null) {
                result = true;
                break;
            }

        } while (true);
        return result;
    }

    private boolean isFieldInCheck(Player player, int row, int column) {
        int counter = 0;
        AbstractFigure figure;
        int[] figureCordinates;
        Move move;

        for (int i = 0; i < this.chessBoard.getBoardLenght(); i++) {
            for (int j = 0; j < this.chessBoard.getBoardLenght(); j++) {
                if (isThisFigureKnight(i, j) && isEnemyFigure(player, i, j)) {
                    if (this.chessBoard.getFigure(i, j)
                            .validMove(new Move(i, j, row, column))) {
                        return true;
                    }
                }
            }
        }

        while (counter < directions.length) {
            figureCordinates = getPositionOfFirstFoundFigureInGivenDirection(
                    player, row, column, directions[counter][ZERO],
                    directions[counter][ONE]);

            if (figureCordinates != null) {
                figure = this.chessBoard.getFigure(figureCordinates[ZERO],
                        figureCordinates[ONE]);
                move = new Move(figureCordinates[ZERO], figureCordinates[ONE],
                        row, column);
                if (isEnemyFigure(player, figureCordinates[ZERO],
                        figureCordinates[ONE]) && figure.validMove(move)) {
                    if (!(isThisFigurePawn(figureCordinates[ZERO],
                            figureCordinates[ONE])
                            && (figureCordinates[ONE] == column))) {
                        return true;
                    }
                }
            }
            counter++;
        }
        return false;
    }

    private int[] getPositionOfFirstFoundFigureInGivenDirection(Player player,
            int row, int column, int rowDirection, int columnDirection) {
        int currentRow = row;
        int currentColumn = column;
        int[] figureCordinates = null;
        AbstractFigure figure;

        while (true) {
            currentRow += rowDirection;
            currentColumn += columnDirection;

            if (!IsRowAndColumAreMatrixRange(currentRow, currentColumn)) {
                break;
            }
            figure = this.chessBoard.getFigure(currentRow, currentColumn);
            if (figure != null
                    && !(!isEnemyFigure(player, currentRow, currentColumn)
                            && isThisFigureKing(figure))) {
                figureCordinates = new int[TWO];
                figureCordinates[ZERO] = currentRow;
                figureCordinates[ONE] = currentColumn;
                return figureCordinates;
            }
        }
        return figureCordinates;
    }

    private boolean IsRowAndColumAreMatrixRange(int currentRow,
            int currentColum) {
        boolean result = (ZERO <= currentRow
                && currentRow < this.chessBoard.getBoardLenght())
                && (ZERO <= currentColum
                        && currentColum < this.chessBoard.getBoardLenght());
        return result;
    }

    private boolean isKignInCheckWhenMoveFigure(Player player, Move move) {
        boolean result = false;
        int[] kingPosition = getCurrentPlayerKingPosition(player);
        Move moveBetweenKingAndMovedFigure = new Move(kingPosition[ZERO],
                kingPosition[ONE], move.getFromRow(), move.getFromColumn());
        AbstractFigure movedFigure = this.chessBoard
                .getFigure(move.getFromRow(), move.getFromColumn());
        AbstractFigure figure;

        int[] directionsCurrentMove = getDirection(move);
        int directionRowCurrentMove = directionsCurrentMove[ZERO];
        int directionColumCurrentMove = directionsCurrentMove[ONE];
        int[] directionsMoveBetweenKingAndMovedFigure = getDirection(
                moveBetweenKingAndMovedFigure);
        int directionRowMoveBetweenKingAndMovedFigure = directionsMoveBetweenKingAndMovedFigure[ZERO];
        int directionColumMoveBetweenKingAndMovedFigure = directionsMoveBetweenKingAndMovedFigure[ONE];

        if ((directionRowCurrentMove == directionRowMoveBetweenKingAndMovedFigure)
                && (directionColumCurrentMove == directionColumMoveBetweenKingAndMovedFigure)
                || (directionRowCurrentMove
                        * MINUS_ONE == directionRowMoveBetweenKingAndMovedFigure)
                        && (directionColumCurrentMove
                                * MINUS_ONE == directionColumMoveBetweenKingAndMovedFigure)) {
            return false;
        }

        boolean isSameRow = moveBetweenKingAndMovedFigure
                .getFromRow() == moveBetweenKingAndMovedFigure.getToRow();
        boolean isSameColumn = moveBetweenKingAndMovedFigure
                .getFromColumn() == moveBetweenKingAndMovedFigure.getToColumn();
        boolean isSameDiagonal = Math.abs(moveBetweenKingAndMovedFigure
                .getSubtractionbetweenRows()) == Math
                        .abs(moveBetweenKingAndMovedFigure
                                .getSubtractionbetweenColums());
        if (player.getColor().equals(movedFigure.getColor())
                && (isSameRow || isSameColumn || isSameDiagonal)) {
            int currentRow = moveBetweenKingAndMovedFigure.getToRow();
            int currentColum = moveBetweenKingAndMovedFigure.getToColumn();
            do {
                currentRow += directionRowMoveBetweenKingAndMovedFigure;
                currentColum += directionColumMoveBetweenKingAndMovedFigure;
                if (!IsRowAndColumAreMatrixRange(currentRow, currentColum)) {
                    break;
                }
                figure = this.chessBoard.getFigure(currentRow, currentColum);
                if ((figure != null) && !figure.equals(movedFigure)) {
                    if (figure.getColor().equals(player.getColor())) {
                        return false;
                    }
                    result = figure.validMove(new Move(currentRow, currentColum,
                            moveBetweenKingAndMovedFigure.getFromRow(),
                            moveBetweenKingAndMovedFigure.getFromColumn()));
                    break;
                }

            } while (true);

        }
        return result;
    }

    private int[] getDirection(Move move) {
        int[] directions = new int[TWO];
        int row = move.getSubtractionbetweenRows();
        int colum = move.getSubtractionbetweenColums();
        directions[ZERO] = (int) Math.signum(row) * MINUS_ONE;
        directions[ONE] = (int) Math.signum(colum) * MINUS_ONE;
        return directions;
    }

    private int[] getCurrentPlayerKingPosition(Player player) {
        int[] position = new int[TWO];
        for (int i = 0; i < chessBoard.getBoardLenght(); i++) {
            for (int j = 0; j < chessBoard.getBoardLenght(); j++) {
                if (isThisFigureKing(i, j) && !isEnemyFigure(player, i, j)) {
                    position[ZERO] = i;
                    position[ONE] = j;
                    return position;
                }
            }
        }
        return position;
    }

    private boolean isThisFigureKnight(int row, int column) {
        AbstractFigure figure = this.chessBoard.getFigure(row, column);
        boolean result = false;
        result = figure instanceof Knight;
        return result;
    }

    private boolean isThisFigureKnight(AbstractFigure figure) {
        return figure instanceof Knight;
    }

    private boolean isThisFigurePawn(int row, int column) {
        boolean result = false;
        AbstractFigure figure = this.chessBoard.getFigure(row, column);
        result = figure instanceof Pawn;
        return result;
    }

    private boolean isThisFigurePawn(AbstractFigure figure) {
        return figure instanceof Pawn;
    }

    private boolean isThisFigureKing(int row, int column) {
        boolean result = false;
        AbstractFigure figure = this.chessBoard.getFigure(row, column);
        result = figure instanceof King;
        return result;
    }

    private boolean isThisFigureKing(AbstractFigure figure) {
        return figure instanceof King;
    }

    private boolean isThisFigureBishop(AbstractFigure figure) {
        return figure instanceof Bishop;
    }

    private boolean isEnemyFigure(Player player, int row, int column) {
        boolean result = !this.chessBoard.getFigure(row, column).getColor()
                .equals(player.getColor());
        return result;
    }

    private AbstractFigure[] getAllLeftFigureOnChessBord() {
        AbstractFigure[] allLeftFigureOnChessBord = new AbstractFigure[getNumberOfFicgureOnTheChessBoard()];
        AbstractFigure figure;
        int counter = 0;
        for (int i = 0; i < chessBoard.getBoardLenght(); i++) {
            for (int j = 0; j < chessBoard.getBoardLenght(); j++) {
                figure = this.chessBoard.getFigure(i, j);
                if (figure != null) {
                    allLeftFigureOnChessBord[counter++] = figure;
                }
            }
        }
        return allLeftFigureOnChessBord;
    }

    private boolean isKingAgainstKing(
            AbstractFigure[] allLeftFigureOnChessBord) {
        boolean result = false;
        if (allLeftFigureOnChessBord.length == TWO) {
            result = isThisFigureKing(allLeftFigureOnChessBord[ZERO])
                    && isThisFigureKing(allLeftFigureOnChessBord[ONE]);
        }
        return result;
    }

    private boolean isKingAgainstKingAndBishopOrKnight(
            AbstractFigure[] allLeftFigureOnChessBord) {
        boolean result = false;
        boolean isCorrectColorBishop = false;
        int numberOfKIngs = 0;

        if (allLeftFigureOnChessBord.length == THREE) {
            for (int i = 0; i < allLeftFigureOnChessBord.length; i++) {
                if (isThisFigureKing(allLeftFigureOnChessBord[i])) {
                    numberOfKIngs++;
                } else if (isThisFigureBishop(allLeftFigureOnChessBord[i])
                        || isThisFigureKnight(allLeftFigureOnChessBord[i])) {
                    isCorrectColorBishop = true;
                }
            }
            result = isCorrectColorBishop && numberOfKIngs == TWO;
        }
        return result;
    }

    private boolean isKingAndBishopAgainstKingAndBishop(
            AbstractFigure[] allLeftFigureOnChessBord) {
        boolean result = false;
        int numberOfKIngs = 0;
        int numberOfBishops = 0;
        Bishop first = null;
        Bishop second = null;

        if (allLeftFigureOnChessBord.length == FOUR) {
            for (int i = 0; i < allLeftFigureOnChessBord.length; i++) {
                if (isThisFigureKing(allLeftFigureOnChessBord[i])) {
                    numberOfKIngs++;
                } else if (isThisFigureBishop(allLeftFigureOnChessBord[i])) {
                    if (first == null) {
                        first = (Bishop) allLeftFigureOnChessBord[i];
                    } else {
                        second = (Bishop) allLeftFigureOnChessBord[i];
                    }
                    numberOfBishops++;
                }
            }
            if (first != null && second != null) {
                int[] bishopsPosition = getBishopsPositions();
                Color firstFoundBishop = getChessbordFieldColor(
                        bishopsPosition[ZERO], bishopsPosition[ONE]);
                Color secondFoundBishop = getChessbordFieldColor(
                        bishopsPosition[TWO], bishopsPosition[THREE]);
                result = (numberOfBishops == TWO)
                        && firstFoundBishop.equals(secondFoundBishop)
                        && (numberOfKIngs == TWO);
            }
        }
        return result;
    }

    private Color getChessbordFieldColor(int row, int column) {
        if (row % TWO == ZERO) {
            if (column % TWO == ZERO) {
                return Color.WHITE;
            } else {
                return Color.BLACK;
            }
        } else {
            if (column % TWO == ZERO) {
                return Color.BLACK;
            } else {
                return Color.WHITE;
            }
        }
    }

    private int[] getBishopsPositions() {
        int counter = 0;
        int[] bishopsPosition = new int[FOUR];
        for (int i = 0; i < this.chessBoard.getBoardLenght(); i++) {
            for (int j = 0; j < this.chessBoard.getBoardLenght(); j++) {
                if (isThisFigureBishop(this.chessBoard.getFigure(i, j))) {
                    if (counter < THREE) {
                        bishopsPosition[counter++] = i;
                        bishopsPosition[counter] = j;
                        counter++;
                    }
                }
            }
        }
        return bishopsPosition;
    }

    private boolean isNoPossibilityOfCheckmate() {
        AbstractFigure[] allLeftFigureOnChessBord = getAllLeftFigureOnChessBord();

        boolean result = isKingAgainstKing(allLeftFigureOnChessBord)
                || isKingAgainstKingAndBishopOrKnight(allLeftFigureOnChessBord)
                || isKingAndBishopAgainstKingAndBishop(
                        allLeftFigureOnChessBord);

        return result;
    }

    private boolean isFiftyMoveRule() {
        boolean result = (this
                .getNumberOfMovesWithoutCapture() == FIFTY_MOVES_RULE_VALUE)
                && (this.getNumberOfMovesWithoutMovePawn() == FIFTY_MOVES_RULE_VALUE);
        return result;
    }

    private boolean isGameDraw(Player player) {

        boolean result = isNoPossibilityOfCheckmate() || isFiftyMoveRule();

        return result;
    }

    private boolean isEnPassant(Move move) {

        boolean result = false;
        boolean isOtherPawnCorrecColor = false;
        boolean isPawnMovedCorrectSide = false;

        int currentRow = move.getFromRow();
        int currentColumn = move.getFromColumn();
        int moveColumn = move.getToColumn();

        Move lastEnemyMove = getLastEnemyMove();

        if (lastEnemyMove == null) {
            return false;
        }

        if (isThisFigurePawn(lastEnemyMove.getToRow(),
                lastEnemyMove.getToColumn())
                && (Math.abs(
                        lastEnemyMove.getSubtractionbetweenRows()) == TWO)) {

            if (isThisFigurePawn(currentRow, currentColumn)
                    && (currentColumn != (this.chessBoard.getBoardLenght()
                            - ONE))
                    && isThisFigurePawn(currentRow, currentColumn + ONE)) {
                isOtherPawnCorrecColor = !(this.chessBoard
                        .getFigure(currentRow, currentColumn).getColor()
                        .equals(this.chessBoard
                                .getFigure(currentRow, currentColumn + ONE)
                                .getColor()));

                isPawnMovedCorrectSide = (moveColumn == (currentColumn + ONE));
                if (isOtherPawnCorrecColor && isPawnMovedCorrectSide) {

                    if ((currentRow == lastEnemyMove.getToRow())
                            && (currentColumn + ONE == lastEnemyMove
                                    .getToColumn())) {
                        this.decrementNumberOfFicgureOnTheChessBoard();
                        this.chessBoard.addFigure(currentRow,
                                currentColumn + ONE, null);
                        this.resetNumberOfMovesWithoutCapture();

                        return true;
                    }
                }
            }

            if (isThisFigurePawn(currentRow, currentColumn)
                    && currentColumn != ZERO
                    && isThisFigurePawn(currentRow, currentColumn - ONE)) {
                isOtherPawnCorrecColor = !(this.chessBoard
                        .getFigure(currentRow, currentColumn).getColor()
                        .equals(this.chessBoard
                                .getFigure(currentRow, currentColumn - ONE)
                                .getColor()));

                isPawnMovedCorrectSide = (moveColumn == (currentColumn - ONE));
                if (isOtherPawnCorrecColor && isPawnMovedCorrectSide) {
                    if ((currentRow == lastEnemyMove.getToRow())
                            && (currentColumn - ONE == lastEnemyMove
                                    .getToColumn())) {
                        this.decrementNumberOfFicgureOnTheChessBoard();
                        this.chessBoard.addFigure(currentRow,
                                currentColumn - ONE, null);
                        this.resetNumberOfMovesWithoutCapture();
                        return true;
                    }
                }
            }

        }
        return result;
    }

    private boolean isCastling(Player player, Move move) {
        boolean result = false;
        AbstractFigure figureKing = this.chessBoard.getFigure(move.getFromRow(),
                move.getFromColumn());
        AbstractFigure rook;
        Move moveFromKingToRook;
        if (isThisFigureKing(move.getFromRow(), move.getFromColumn())
                && figureKing.isFirstMove()) {
            int[] rookPosition = getRookPosition(figureKing, move);
            rook = this.chessBoard.getFigure(rookPosition[ZERO],
                    rookPosition[ONE]);
            moveFromKingToRook = new Move(move.getFromRow(),
                    move.getFromColumn(), rookPosition[ZERO],
                    rookPosition[ONE]);
            ;
            if (rook != null && rook.isFirstMove() && !isCurentPlayerInCheck()
                    && !checkForFiguresBetweenTwoFields(moveFromKingToRook)) {
                int numberOfFieldrForCheck = Math
                        .abs(move.getFromColumn() - rookPosition[ONE]);
                int dirrection;
                if (numberOfFieldrForCheck == FOUR) {
                    dirrection = MINUS_ONE;
                } else {
                    dirrection = ONE;
                }
                int currentColumn = move.getFromColumn();
                int numberFreeFields = ZERO;
                for (int i = ONE; i < numberOfFieldrForCheck; i++) {
                    currentColumn += dirrection;
                    if (!isFieldInCheck(player, move.getFromRow(),
                            currentColumn)) {
                        numberFreeFields++;
                    }
                }
                result = (numberFreeFields == (numberOfFieldrForCheck - ONE));
                if (result) {
                    if (dirrection > ZERO) {
                        this.chessBoard.addFigure(move.getFromRow(),
                                move.getFromColumn() + ONE, rook);
                    } else {
                        this.chessBoard.addFigure(move.getFromRow(),
                                move.getFromColumn() - ONE, rook);
                    }
                    this.chessBoard.addFigure(rookPosition[ZERO],
                            rookPosition[ONE], null);
                }

            }

        }
        return result;
    }

    private int[] getRookPosition(AbstractFigure figure, Move move) {
        boolean direction = move.getSubtractionbetweenColums() > ZERO;
        int[] array = new int[TWO];

        if (figure.getColor().equals(Color.WHITE)) {

            if (direction) {
                array[ZERO] = SEVEN;
                array[ONE] = ZERO;
            } else {
                array[ZERO] = SEVEN;
                array[ONE] = SEVEN;
            }

        } else {

            if (direction) {
                array[ZERO] = ZERO;
                array[ONE] = ZERO;
            } else {
                array[ZERO] = ZERO;
                array[ONE] = SEVEN;
            }

        }
        return array;
    }

    private boolean canMove(Player player, Move move)
            throws EmtyFieldException, IncorectColorMoveException,
            IncorectColorCaptureException, IllegalMoveException,
            KingCheckException, IlligalCapturedKingException,
            IllegalMoveCheckException, IllegalEnPassantMoveException,
            SameFieldException, IllegalCastingMoveException {
        boolean result = true;

        if (isFromFieldEmpty(move.getFromRow(), move.getFromColumn())) {
            throw new EmtyFieldException();
        }
        if (isSameField(move)) {
            throw new SameFieldException();
        }
        if (!isCorrectColorFigureCurrentPosition(player, move)) {
            throw new IncorectColorMoveException();
        }
        if (isThisFigureKing(move.getToRow(), move.getToColumn())) {
            throw new IlligalCapturedKingException();
        }
        if (isCorrectColorFigureChoosenForMovingPosition(player, move)) {
            throw new IncorectColorCaptureException();
        }
        if (!this.chessBoard.getFigure(move.getFromRow(), move.getFromColumn())
                .validMove(move)) {
            throw new IllegalMoveException();
        }
        if (!isThisFigureKnight(move.getFromRow(), move.getFromColumn())
                && checkForFiguresBetweenTwoFields(move)) {
            throw new IllegalMoveException();
        }
        if (isThisFigurePawn(move.getFromRow(), move.getFromColumn())
                && !canPawnMove(move)) {
            throw new IllegalMoveException();
        }
        if (isThisFigureKing(move.getFromRow(), move.getFromColumn())
                && Math.abs(move.getSubtractionbetweenColums()) == TWO
                && !isCastling(player, move)) {
            throw new IllegalCastingMoveException();
        }
        if (!isThisFigureKing(move.getFromRow(), move.getFromColumn())
                && isKignInCheckWhenMoveFigure(player, move)) {
            throw new IllegalMoveCheckException();
        }

        stopMoveFiguresIfKingIncheck(player, move);

        return result;
    }

    private void stopMoveFiguresIfKingIncheck(Player player, Move move)
            throws IllegalMoveException, KingCheckException {
        this.chessBoard.addFigure(move.getToRow(), move.getToColumn(),
                this.chessBoard.getFigure(move.getFromRow(),
                        move.getFromColumn()));
        this.chessBoard.addFigure(move.getFromRow(), move.getFromColumn(),
                null);

        int[] kingPosition = getCurrentPlayerKingPosition(player);
        if (isFieldInCheck(player, kingPosition[ZERO], kingPosition[ONE])) {
            this.chessBoard.addFigure(move.getFromRow(), move.getFromColumn(),
                    this.chessBoard.getFigure(move.getToRow(),
                            move.getToColumn()));
            this.chessBoard.addFigure(move.getToRow(), move.getToColumn(),
                    null);
            throw new KingCheckException();
        }

        this.chessBoard.addFigure(move.getFromRow(), move.getFromColumn(),
                this.chessBoard.getFigure(move.getToRow(), move.getToColumn()));
        this.chessBoard.addFigure(move.getToRow(), move.getToColumn(), null);
    }

    private void checkFoPawnPromotion(Player player, Move move)
            throws InvalidNameException {
        AbstractFigure figure = null;
        if (isThisFigurePawn(move.getToRow(), move.getToColumn())) {
            if (this.chessBoard.getFigure(move.getToRow(), move.getToColumn())
                    .getColor().equals(Color.WHITE)) {
                if (move.getToRow() == ZERO) {
                    if (move.getToColumn() % TWO == ZERO) {
                        figure = choosePromotionFigure(player, move,
                                Color.WHITE);
                    } else {
                        figure = choosePromotionFigure(player, move,
                                Color.BLACK);
                    }
                    this.chessBoard.addFigure(move.getToRow(),
                            move.getToColumn(), null);
                    this.chessBoard.addFigure(move.getToRow(),
                            move.getToColumn(), figure);
                }
            } else {
                if (move.getToRow() == SEVEN) {
                    if (move.getToColumn() % TWO == ZERO) {
                        figure = choosePromotionFigure(player, move,
                                Color.BLACK);
                    } else {
                        figure = choosePromotionFigure(player, move,
                                Color.WHITE);
                    }
                    this.chessBoard.addFigure(move.getToRow(),
                            move.getToColumn(), null);
                    this.chessBoard.addFigure(move.getToRow(),
                            move.getToColumn(), figure);
                }
            }

        }
    }

    private AbstractFigure choosePromotionFigure(Player player, Move move,
            Color squareColor) throws InvalidNameException {
        AbstractFigure figure = null;
        String userInput;

        while (true) {
            Color playerColor = player.getColor();
            System.out.println(playerColor
                    + " player choose figure to replace pawn.   Example: Bishop");

            userInput = StaticObjects.scanner.nextLine();
            String trimString = UserInputValidatorAndConvertor
                    .clearAllWhiteSpaces(userInput);

            switch (trimString) {
            case Rook.NAME:
                figure = new Rook(playerColor);
                break;
            case Bishop.NAME:
                figure = new Bishop(playerColor);
                break;
            case Knight.NAME:
                figure = new Knight(playerColor);
                break;
            case Queen.NAME:
                figure = new Queen(playerColor);
                break;
            case King.NAME:
                figure = new King(playerColor);
                break;
            }

            if (figure != null) {
                break;
            }

            System.out.println("Invalid answer. Try again.\n");

        }
        return figure;
    }

    private boolean isSameField(Move move) {
        boolean result = move.getFromRow() == move.getToRow()
                && move.getToColumn() == move.getFromColumn();
        return result;
    }

    public boolean isGameEnded(Player player) {
        boolean result = false;
        if (!result && isGameDraw(player)) {
            System.out.println("Game end with draw");
            result = true;
        }

        if (!result && isCheckmate(player)) {
            System.out.println(player.getName() + " is in checkmate");
            result = true;
        }

        return result;
    }

    private boolean isCheckmate(Player player) {
        int[] kingPosition = getCurrentPlayerKingPosition(player);
        boolean isAllFreeFieldsAroundKingAreInCheck = isAllFreeFieldsAroundKingAreInCheck(
                player, kingPosition);
        boolean canTheKingBeCoveredByFigure = canTheKingBeCoveredByFigure(
                player, kingPosition);
        return isAllFreeFieldsAroundKingAreInCheck
                && !canTheKingBeCoveredByFigure;
    }

    private boolean canTheKingBeCoveredByFigure(Player player,
            int[] kingPosition) {
        int counter = 0;
        AbstractFigure figure;
        int[] figureCordinates;
        int[] cordinatesFigureAttackKing = new int[TWO];
        int numberOfFoundCordinates = 0;

        for (int i = 0; i < this.chessBoard.getBoardLenght(); i++) {
            for (int j = 0; j < this.chessBoard.getBoardLenght(); j++) {
                if (isThisFigureKnight(i, j) && isEnemyFigure(player, i, j)) {
                    if (this.chessBoard.getFigure(i, j).validMove(new Move(i, j,
                            kingPosition[ZERO], kingPosition[ONE]))) {
                        if (numberOfFoundCordinates >= ONE) {
                            return false;
                        }
                        cordinatesFigureAttackKing[numberOfFoundCordinates] = i;
                        numberOfFoundCordinates++;
                        cordinatesFigureAttackKing[numberOfFoundCordinates] = j;
                    }
                }
            }
        }

        while (counter < directions.length) {
            figureCordinates = getPositionOfFirstFoundFigureInGivenDirection(
                    player, kingPosition[ZERO], kingPosition[ONE],
                    directions[counter][ZERO], directions[counter][ONE]);

            if (figureCordinates != null) {
                figure = this.chessBoard.getFigure(figureCordinates[ZERO],
                        figureCordinates[ONE]);
                if (!figure.getColor().equals(player.getColor())
                        && figure.validMove(new Move(figureCordinates[ZERO],
                                figureCordinates[ONE], kingPosition[ZERO],
                                kingPosition[ONE]))) {
                    if (!(isThisFigurePawn(figureCordinates[ZERO],
                            figureCordinates[ONE])
                            && (figureCordinates[ONE] == kingPosition[ONE]))) {
                        if (numberOfFoundCordinates >= ONE) {
                            return false;
                        }
                        cordinatesFigureAttackKing[numberOfFoundCordinates] = figureCordinates[ZERO];
                        numberOfFoundCordinates++;
                        cordinatesFigureAttackKing[numberOfFoundCordinates] = figureCordinates[ONE];
                    }
                }
            }
            counter++;
        }

        int[] direction = getDirection(
                new Move(cordinatesFigureAttackKing[ZERO],
                        cordinatesFigureAttackKing[ONE], kingPosition[ZERO],
                        kingPosition[ONE]));
        int currentRow = cordinatesFigureAttackKing[ZERO];
        int currentColumn = cordinatesFigureAttackKing[ONE];
        Move move;
        AbstractFigure figure2;
        while (true) {

            for (int i = 0; i < this.chessBoard.getBoardLenght(); i++) {
                for (int j = 0; j < this.chessBoard.getBoardLenght(); j++) {
                    figure2 = this.chessBoard.getFigure(i, j);
                    if (figure2 != null && !isEnemyFigure(player, i, j)
                            && !isThisFigureKing(figure2)) {
                        move = new Move(i, j, currentRow, currentColumn);
                        if (isThisFigureKnight(i, j)) {
                            if (figure2.validMove(move)
                                    && !isKignInCheckWhenMoveFigure(player,
                                            move)) {
                                return true;
                            }
                        } else {
                            if (figure2.validMove(move)
                                    && !checkForFiguresBetweenTwoFields(move)
                                    && !isKignInCheckWhenMoveFigure(player,
                                            move)) {
                                return true;
                            }
                        }
                    }
                }
            }

            currentRow += direction[ZERO];
            currentColumn += direction[ONE];
            if (!IsRowAndColumAreMatrixRange(currentRow, currentColumn)
                    || (currentRow == kingPosition[ZERO]
                            && currentColumn == kingPosition[ONE])) {
                break;
            }

        }
        return false;
    }

    private boolean isAllFreeFieldsAroundKingAreInCheck(Player player,
            int[] kingPosition) {
        int row;
        int column;
        AbstractFigure figure;
        int numberOfFreeFields = 0;
        int nummberOfFreeFieldsInCheck = 0;

        for (int i = 0; i < directions.length; i++) {
            row = kingPosition[ZERO] + directions[i][ZERO];
            column = kingPosition[ONE] + directions[i][ONE];
            if (IsRowAndColumAreMatrixRange(row, column)) {
                figure = this.chessBoard.getFigure(row, column);
                if (figure == null || isEnemyFigure(player, row, column)) {
                    numberOfFreeFields++;
                    if (isFieldInCheck(player, row, column)) {
                        nummberOfFreeFieldsInCheck++;
                    }
                }
            }
        }

        return numberOfFreeFields == nummberOfFreeFieldsInCheck;

    }

    public int getNumberOfFicgureOnTheChessBoard() {
        return numberOfFicgureOnChessBoard;
    }

    public void decrementNumberOfFicgureOnTheChessBoard() {
        numberOfFicgureOnChessBoard--;
    }

    public int getNumberOfMovesWithoutMovePawn() {
        return numberOfMovesWithoutMovePawn;
    }

    public void incrementNumberOfMovesWithoutMovePawn() {
        numberOfMovesWithoutMovePawn++;
    }

    public void resetNumberOfMovesWithoutMovePawn() {
        numberOfMovesWithoutMovePawn = 0;
    }

    public int getNumberOfMovesWithoutCapture() {
        return numberOfMovesWithoutCapture;
    }

    public void incrementNumberOfMovesWithoutCapture() {
        numberOfMovesWithoutCapture++;
    }

    public void resetNumberOfMovesWithoutCapture() {
        numberOfMovesWithoutCapture = 0;
    }

    public boolean isCurentPlayerInCheck() {
        return isCurentPlayerInCheck;
    }

    public void setCurentPlayerInCheck(boolean isCurentPlayerInCheck) {
        this.isCurentPlayerInCheck = isCurentPlayerInCheck;
    }

    public Move getLastEnemyMove() {
        return lastEnemyMove;
    }

    public void setLastEnemyMove(Move lastEnemyMove) {
        this.lastEnemyMove = lastEnemyMove;
    }

}
