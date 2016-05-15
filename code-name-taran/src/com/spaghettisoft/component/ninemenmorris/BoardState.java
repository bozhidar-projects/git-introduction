package com.spaghettisoft.component.ninemenmorris;

import java.util.HashMap;
import java.util.Map;

public class BoardState {

    static Map<String, String> mapPossMovements = new HashMap<>();

    private static BoardTile[][] boardArrays;

    public static String[][] mills = { { "A", "B", "C" }, { "C", "O", "X" }, { "X", "W", "V" }, { "V", "J", "A" },
            { "D", "E", "F" }, { "F", "N", "U" }, { "U", "T", "S" }, { "S", "K", "D" }, { "G", "H", "I" },
            { "I", "M", "R" }, { "R", "Q", "P" }, { "P", "L", "G" }, { "B", "R", "H" }, { "M", "N", "O" },
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

        String PlayerPlace = "A";
        String poolType = BoardTile.WHITE;
        placePool(poolType, PlayerPlace);

        String PlayerPlace2 = "D";
        String poolType2 = BoardTile.WHITE;
        placePool(poolType2, PlayerPlace2);

        String PlayerPlace3 = "G";
        String poolType3 = BoardTile.WHITE;
        placePool(poolType3, PlayerPlace3);

        String PlayerPlace4 = "B";
        String poolType4 = BoardTile.WHITE;
        placePool(poolType4, PlayerPlace4);

        String PlayerPlace5 = "C";
        String poolType5 = BoardTile.WHITE;
        placePool(poolType5, PlayerPlace5);

        String PlayerPlace6 = "J";
        String poolType6 = BoardTile.BLACK;
        placePool(poolType6, PlayerPlace6);

        String PlayerPlace7 = "V";
        String poolType7 = BoardTile.BLACK;
        placePool(poolType7, PlayerPlace7);

        // movePool("C", "B");

        // deletePool("B");

        // checkForMills("A");

        checkForFlying("E", "H");

        for (int i = 0; i < boardArrays.length; i++) {
            for (int j = 0; j < boardArrays[i].length; j++) {
                System.out.print(boardArrays[i][j].getVisalization());
            }
            System.out.println();
            System.out.println();
        }
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
    }

    private static void movePool(String placeFrom, String placeTo) {

        BoardTile tileFromPlace = getBoardTileByPlace(placeFrom);
        BoardTile tileToPlace = getBoardTileByPlace(placeTo);

        tileToPlace.setPoolType(tileFromPlace.getPoolType());
        tileFromPlace.emptyTile();
    }

    private static void checkForFlying(String placeFrom, String placeTo) {

        // System.out.println(mapPossMovements.get(placeFrom));
        boolean a1 = mapPossMovements.get(placeFrom).contains(placeTo);

        if (a1 == true) {
            System.out.println("This is true");
        } else {
            System.out.println("You are cheating. Try again!");
        }
    }

    private static void deletePool(String placeFrom) {
        BoardTile tileFromPlace = getBoardTileByPlace(placeFrom);
        tileFromPlace.emptyTile();
    }

    private static boolean checkForMills(String place) {

        boolean foundMills = false;
        BoardTile tilePlace = getBoardTileByPlace(place);
        String currentPoolType = tilePlace.getPoolType();

        for (int i = 0; i < mills.length; i++) {
            for (int j = 0; j < mills[i].length; j++) {
                if (place.equals(mills[i][j]) && currentPoolType == (getBoardTileByPlace(mills[i][0])).getPoolType()
                        && currentPoolType == (getBoardTileByPlace(mills[i][1])).getPoolType()
                        && currentPoolType == (getBoardTileByPlace(mills[i][2])).getPoolType()) {

                    foundMills = true;
                    System.out.println(mills[i][0] + mills[i][1] + mills[i][2]);
                    System.out.println(foundMills);
                }
            }
        }
        return foundMills;
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

    private static void checkForEndOfGame(String placeLastMove) {

        int poolCounterOponent = 0;

        BoardTile tileLastMove = getBoardTileByPlace(placeLastMove);

        if (tileLastMove.getPoolType().equals(BoardTile.WHITE)) {

            for (int i = 0; i < boardArrays.length; i++) {
                for (int j = 0; j < boardArrays[i].length; j++) {

                    if (boardArrays[i][j].getPoolType().equals(BoardTile.BLACK)) {
                        poolCounterOponent++;
                    }
                }
            }
            if (poolCounterOponent < 3) {
                System.out.println("White Player Wins !!! Black player has only " + poolCounterOponent + " pools");
            }
        }

        else if (tileLastMove.getPoolType().equals(BoardTile.BLACK)) {
            for (int i = 0; i < boardArrays.length; i++) {
                for (int j = 0; j < boardArrays[i].length; j++) {

                    if (boardArrays[i][j].getPoolType().equals(BoardTile.WHITE)) {
                        poolCounterOponent++;
                    }
                }
            }
            if (poolCounterOponent < 3) {
                System.out.println("Black Player Wins !!! White player has only " + poolCounterOponent + " pools");
            }
        }
    }
}
