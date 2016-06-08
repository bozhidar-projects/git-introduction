package com.spaghettisoft.application.fourinarow;

import com.spaghettisoft.component.game.AbstractGame;
import com.spaghettisoft.globals.StaticObjects;

public class FourInARow extends AbstractGame {
    private static final int WIDTH = 6;
    private static final int HEIGHT = 6;
    private char[][] board;

    private char currentSymbol = '1';

    public FourInARow() {
        initialize();
    }

    @Override
    protected void printEndGameMessage() {
        initialize();
    }

    @Override
    protected void drawGame() {
        for (int w = 0; WIDTH > w; w += 1) {
            for (int h = 0; HEIGHT > h; h += 1) {
                System.out.print("|" + board[w][h]);
            }
            System.out.println("|");
        }
    }

    private void initialize() {
        board = new char[HEIGHT][WIDTH];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '.';
            }
        }
    }

    @Override
    protected boolean isEnded() {
        return check2() || check1();
    }

    @Override
    protected void processGame() {
        System.out.println("Enter a valid move: ");
        int row;
        int column;
        do {
            row = StaticObjects.scanner.nextInt();
            column = StaticObjects.scanner.nextInt();
            if (board[row][column] == '.') {
                board[row][column] = currentSymbol;
                currentSymbol = (currentSymbol == '1' ? '2' : '1');
            } else {
                System.out.println("Incorrect move!");
            }
        } while (row > 5 || column > 5);

    }

    public boolean checkFirstHorizontal() {
        boolean flag = true;
        int count = 0;
        while (flag) {
            for (int w = 0; WIDTH > w; w += 1) {
                for (int h = 0; HEIGHT > h; h += 1) {
                    if (board[w][h] == '1') {
                        count += 1;
                    } else {
                        count = 0;
                    }
                    if (count >= 4) {
                        System.out.println("Player 1 wins");
                        flag = false;
                        return flag;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public boolean checkFirstVertical() {

        boolean flag = true;

        int counter = 0;
        while (flag) {

            for (int h = 0; HEIGHT > h; h += 1) {
                for (int w = 0; WIDTH > w; w += 1) {
                    if (board[w][h] == '1') {
                        counter += 1;
                    } else {
                        counter = 0;
                    }
                    if (counter >= 4) {
                        System.out.println("Player 1 wins");
                        flag = false;
                        return flag;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public boolean checkSecondHorizontal() {
        boolean flag = true;

        int counter = 0;
        while (flag) {

            for (int w = 0; WIDTH > w; w += 1) {
                for (int h = 0; HEIGHT > h; h += 1) {
                    if (board[w][h] == '2') {
                        counter += 1;
                    } else {
                        counter = 0;
                    }
                    if (counter >= 4) {
                        System.out.println("Player 2 wins");
                        flag = false;
                        return flag;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public boolean checkSecondVertical() {
        boolean flag = true;

        int counter = 0;
        while (flag) {

            for (int h = 0; HEIGHT > h; h += 1) {
                for (int w = 0; WIDTH > w; w += 1) {
                    if (board[w][h] == '2') {
                        counter += 1;
                    } else {
                        counter = 0;
                    }
                    if (counter >= 4) {
                        System.out.println("Player 2 wins");
                        flag = false;
                        return flag;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public boolean checkFirstDiagonal() {

        boolean flag = true;

        int counter = 0;

        Boolean check = false;

        int checkColumn = 1;
        int checkRow = 1;

        while (flag) { // goes through until an 1 is found
            for (int w = 0; WIDTH > w; w++) {
                for (int h = 0; HEIGHT > h; h++) {
                    if (board[w][h] == '1') { // if 1 is found, add one to
                                              // counter and go into loop
                        counter++;
                        check = true;
                        while (check) { // goes through diagonally looking for 1
                            if (checkColumn + w <= WIDTH - 1
                                    && checkRow + h <= HEIGHT - 1) {
                                if (board[w + checkColumn][h
                                        + checkRow] == '1') {
                                    counter++;
                                }
                            }

                            // adds 1 to checkers
                            checkColumn++;
                            checkRow++;

                            if (checkColumn == WIDTH - 1
                                    || checkRow == HEIGHT - 1) {
                                check = false;
                                break;
                            }

                            if (counter >= 4) {
                                System.out.println("Player 1 wins");
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (counter >= 4) {
                        flag = false;
                        break;
                    }

                    // resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean checkSecondDiagonal() {

        boolean flag = true;

        int counter = 0;

        Boolean check = false;

        int checkColumn = 1;
        int checkRow = 1;

        while (flag) { // goes through until an O is found
            for (int w = 0; WIDTH > w; w++) {
                for (int h = 0; HEIGHT > h; h++) {
                    if (board[w][h] == '2') {
                        counter++;
                        check = true;
                        while (check) {
                            if (checkColumn + w <= WIDTH - 1
                                    && checkRow + h <= HEIGHT - 1) {
                                if (board[w + checkColumn][h
                                        + checkRow] == '2') {
                                    counter++;
                                }
                            }

                            // adds 1 to checkers
                            checkColumn++;
                            checkRow++;

                            if (checkColumn == WIDTH - 1
                                    || checkRow == HEIGHT - 1) { //if outside of board, break
                                check = false;
                                break;
                            }

                            if (counter >= 4) {
                                System.out.println("Player 2 wins");
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (counter >= 4) {
                        flag = false;
                        break;
                    }

                    // resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean check1() {
        boolean flag = false;
        if (!checkFirstHorizontal() || !checkFirstVertical()
                || !checkFirstDiagonal()) {
            flag = true;
        }
        return flag;
    }

    public boolean check2() {
        boolean flag = false;
        if (!checkSecondHorizontal() || !checkSecondVertical()
                || !checkSecondDiagonal()) {
            flag = true;
        }
        return flag;
    }

}
