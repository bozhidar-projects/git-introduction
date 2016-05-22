package com.spaghettisoft.component.ninemenmorris;

import java.util.HashMap;
import java.util.Map;

import com.spaghettisoft.globals.StaticObjects;

public class BoardState {

    static Map<String, String> mapPossMovements = new HashMap<>();

    private static BoardTile[][] boardArrays;

    private static boolean gameNotEnded = true;

    private static int placedPools = 0;

    public static String[][] mills = { { "A", "B", "C" }, { "C", "O", "X" }, { "X", "W", "V" }, { "V", "J", "A" },
            { "D", "E", "F" }, { "F", "N", "U" }, { "U", "T", "S" }, { "S", "K", "D" }, { "G", "H", "I" },
            { "I", "M", "R" }, { "R", "Q", "P" }, { "P", "L", "G" }, { "B", "E", "H" }, { "M", "N", "O" },
            { "Q", "T", "W" }, { "J", "K", "L" }, { "A", "D", "G" }, { "C", "F", "I" }, { "X", "U", "R" },
            { "V", "S", "P" }, };

    public static void main(String[] args) {

        mapPossMovements.put("A", "BDJ");
        mapPossMovements.put("B", "ACE");
        mapPossMovements.put("C", "BFO");
        mapPossMovements.put("D", "AGEK");
        mapPossMovements.put("E", "BDFH");
        mapPossMovements.put("F", "CEIN");
        mapPossMovements.put("G", "DHL");
        mapPossMovements.put("H", "EGI");
        mapPossMovements.put("I", "FHM");
        mapPossMovements.put("J", "AKV");
        mapPossMovements.put("K", "DJLS");
        mapPossMovements.put("L", "GKP");
        mapPossMovements.put("M", "INR");
        mapPossMovements.put("N", "FMOU");
        mapPossMovements.put("O", "CNX");
        mapPossMovements.put("P", "LQS");
        mapPossMovements.put("Q", "PRT");
        mapPossMovements.put("R", "MQU");
        mapPossMovements.put("S", "KPTV");
        mapPossMovements.put("T", "QSUW");
        mapPossMovements.put("U", "NRTX");
        mapPossMovements.put("V", "JSW");
        mapPossMovements.put("W", "TVX");
        mapPossMovements.put("X", "OUW");

        boardArrays = new BoardTile[7][7];

        boardArrays[0][0] = new BoardTile("A");
        boardArrays[0][3] = new BoardTile("B");
        boardArrays[0][6] = new BoardTile("C");
        boardArrays[1][1] = new BoardTile("D");
        boardArrays[1][3] = new BoardTile("E");
        boardArrays[1][5] = new BoardTile("F");
        boardArrays[2][2] = new BoardTile("G");
        boardArrays[2][3] = new BoardTile("H");
        boardArrays[2][4] = new BoardTile("I");
        boardArrays[3][0] = new BoardTile("J");
        boardArrays[3][1] = new BoardTile("K");
        boardArrays[3][2] = new BoardTile("L");
        boardArrays[3][4] = new BoardTile("M");
        boardArrays[3][5] = new BoardTile("N");
        boardArrays[3][6] = new BoardTile("O");
        boardArrays[4][2] = new BoardTile("P");
        boardArrays[4][3] = new BoardTile("Q");
        boardArrays[4][4] = new BoardTile("R");
        boardArrays[5][1] = new BoardTile("S");
        boardArrays[5][3] = new BoardTile("T");
        boardArrays[5][5] = new BoardTile("U");
        boardArrays[6][0] = new BoardTile("V");
        boardArrays[6][3] = new BoardTile("W");
        boardArrays[6][6] = new BoardTile("X");

        for (int i = 0; i < boardArrays.length; i++) {
            for (int j = 0; j < boardArrays[i].length; j++) {
                if (boardArrays[i][j] == null) {
                    boardArrays[i][j] = new BoardTile("*");
                }
            }
        }

        gameEngine();

        // String PlayerPlace = "A";
        // String poolType = BoardTile.WHITE;
        // placePool(poolType, PlayerPlace);
        //
        // String PlayerPlace2 = "D";
        // String poolType2 = BoardTile.WHITE;
        // placePool(poolType2, PlayerPlace2);
        //
        // String PlayerPlace3 = "G";
        // String poolType3 = BoardTile.WHITE;
        // placePool(poolType3, PlayerPlace3);
        //
        // String PlayerPlace4 = "B";
        // String poolType4 = BoardTile.WHITE;
        // placePool(poolType4, PlayerPlace4);
        //
        // String PlayerPlace5 = "C";
        // String poolType5 = BoardTile.WHITE;
        // placePool(poolType5, PlayerPlace5);
        //
        // String PlayerPlace6 = "J";
        // String poolType6 = BoardTile.BLACK;
        // placePool(poolType6, PlayerPlace6);
        //
        // String PlayerPlace7 = "V";
        // String poolType7 = BoardTile.BLACK;
        // placePool(poolType7, PlayerPlace7);

        // movePool("C", "B");

        // deletePool("B");

        // checkForMills("A");

        // checkForFlying("E", "H");

        // drawBoard();
    }

    private static void drawBoard() {

        for (int i = 0; i < boardArrays.length; i++) {
            for (int j = 0; j < boardArrays[i].length; j++) {
                System.out.print(boardArrays[i][j].getVisalization());
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("===================================");
    }

    private static void placePool(String poolType, String place) {

        BoardTile tileToPlace = getBoardTileByPlace(place);

        if (poolType.equals(BoardTile.WHITE)) {
            tileToPlace.addWhitePool();
        } else if (poolType.equals(BoardTile.BLACK)) {
            tileToPlace.addBlackPool();
        } else {
            tileToPlace.emptyTile();
        }
        placedPools++;

        drawBoard();

        checkForMills(place);

    }

    private static void movePool(String placeFrom, String placeTo) throws IllegalMoveException {

        BoardTile tileFromPlace = getBoardTileByPlace(placeFrom);
        BoardTile tileToPlace = getBoardTileByPlace(placeTo);

        if (checkForFlying(placeFrom, placeTo)) {

            tileToPlace.setPoolType(tileFromPlace.getPoolType());
            tileFromPlace.emptyTile();
            drawBoard();
        } else {
            throw new IllegalMoveException();
        }

        checkForMills(placeTo);

    }

    private static boolean checkForFlying(String placeFrom, String placeTo) {

        // System.out.println(mapPossMovements.get(placeFrom));
        boolean notFlying = mapPossMovements.get(placeFrom).contains(placeTo);

        // if (notFlying == true) {
        // System.out.println("This is true");
        // } else {
        // System.out.println("You are cheating. Try again!");
        // checkForFlying(placeFrom, placeTo); // TOZI RED VEREN LI E?
        // }
        return notFlying;
    }

    private static void deletePool(String placeFrom) {
        BoardTile tileFromPlace = getBoardTileByPlace(placeFrom);

        String deletedPoolType = tileFromPlace.getPoolType();

        tileFromPlace.emptyTile();

        drawBoard();

        checkForEndOfGame(deletedPoolType);

    }

    private static boolean checkForMills(String place) {

        boolean foundMills = false;
        int millsCounter = 0;

        BoardTile tilePlace = getBoardTileByPlace(place);
        String currentPoolType = tilePlace.getPoolType();

        for (int i = 0; i < mills.length; i++) {
            for (int j = 0; j < mills[i].length; j++) {
                if (place.equals(mills[i][j]) && currentPoolType == (getBoardTileByPlace(mills[i][0])).getPoolType()
                        && currentPoolType == (getBoardTileByPlace(mills[i][1])).getPoolType()
                        && currentPoolType == (getBoardTileByPlace(mills[i][2])).getPoolType()) {

                    foundMills = true;
                    millsCounter++;
                    System.out.println("Mill found at " + mills[i][0] + mills[i][1] + mills[i][2] + " !");
                    // System.out.println(foundMills);
                }
            }
        }
        if (foundMills) { // Calls "mills" times deletePool();
            for (int i = 0; i < millsCounter; i++) {
                System.out.println("Enter an oponent place to delete: ");
                String placeToDelete = StaticObjects.scanner.nextLine();
                // System.out.println(placeToDelete);
                deletePool(placeToDelete);
            }
        }

        return foundMills; // Redundant
    }

    private static BoardTile getBoardTileByPlace(String place) {
        for (int i = 0; i < boardArrays.length; i++) {
            for (int j = 0; j < boardArrays[i].length; j++) {
                if (boardArrays[i][j].getBackground().equals(place)) {
                    return boardArrays[i][j];
                }
            }
        }
        throw new IllegalArgumentException("Illegal place");
    }

    private static void checkForEndOfGame(String deletedPoolType) {

        int poolCounterOponent = 0;

        // BoardTile tileLastMove = getBoardTileByPlace(placeToDelete);

        if (deletedPoolType.equals(BoardTile.WHITE)) {

            for (int i = 0; i < boardArrays.length; i++) {
                for (int j = 0; j < boardArrays[i].length; j++) {

                    if (boardArrays[i][j].getPoolType().equals(BoardTile.WHITE)) {
                        poolCounterOponent++;
                    }
                }
            }
            System.out.println("White pools" + poolCounterOponent);
            if ((poolCounterOponent < 3) && (placedPools == 18)) {
                gameNotEnded = false;
                System.out
                        .println("Black player wins !!! White player has only " + poolCounterOponent + " pools left!");
            }
        }

        else if (deletedPoolType.equals(BoardTile.BLACK)) {
            for (int i = 0; i < boardArrays.length; i++) {
                for (int j = 0; j < boardArrays[i].length; j++) {

                    if (boardArrays[i][j].getPoolType().equals(BoardTile.BLACK)) {
                        poolCounterOponent++;
                    }
                }
            }
            System.out.println("Black pools " + poolCounterOponent);
            if ((poolCounterOponent < 3) && (placedPools == 18)) {
                gameNotEnded = false;
                System.out
                        .println("White player wins !!! Black player has only " + poolCounterOponent + " pools left!");
            }
        } else {
            System.out.println("EMPTY pools" + poolCounterOponent);
            System.out.println("emty pull type");
        }
    }

    private static void gameEngine() {
        drawBoard();

        for (int i = 1; i < 19; i++) {

            if (i % 2 == 0) {
                System.out.println("BLACK : Enter place: ");
                String place = StaticObjects.scanner.nextLine();
                placePool(BoardTile.BLACK, place);
            } else {
                System.out.println("WHITE : Enter place: ");
                String place = StaticObjects.scanner.nextLine();
                placePool(BoardTile.WHITE, place);
            }
            drawBoard();
        }

        for (int j = 1; j < 1000; j++) {

            if (j % 2 == 0) {
                while (true) {
                    System.out.println("BLACK : Move FROM place: ");
                    String placeFrom = StaticObjects.scanner.nextLine();

                    System.out.println("BLACK : Move TO place: ");
                    String placeTo = StaticObjects.scanner.nextLine();

                    try {
                        movePool(placeFrom, placeTo);
                        break;

                    } catch (IllegalMoveException e) {
                        System.out.println("Wrong move. Please try again.");
                    }

                }

            } else {

                while (true) {
                    System.out.println("WHITE : Move FROM place: ");
                    String placeFrom = StaticObjects.scanner.nextLine();

                    System.out.println("WHITE : Move TO place: ");
                    String placeTo = StaticObjects.scanner.nextLine();
                    try {
                        movePool(placeFrom, placeTo);
                        break;
                    } catch (IllegalMoveException e) {
                        System.out.println("Wrong move. Please try again.");
                    }
                }
            }
            drawBoard();
        }
    }

    private static boolean checkForExistance(String place) {

        boolean poolAlreadyExists = false;

        BoardTile tileLastMove = getBoardTileByPlace(place);
        String currentPoolType = tileLastMove.getPoolType();

        if (currentPoolType.equals(BoardTile.BLACK) || currentPoolType.equals(BoardTile.WHITE)) {
            poolAlreadyExists = true;
        }

        return poolAlreadyExists;
    }
}