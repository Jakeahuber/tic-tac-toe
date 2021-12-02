package com.example.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameManager {

    private final TicTacButton[][] BOARD;
    private char turn;

    public GameManager(TicTacButton[][] board, char turn) {
        this.BOARD = board;
        this.turn = turn;
    }

    public char getTurn() {
        return turn;
    }

    // Returns true if there is a winner, and false otherwise
    public boolean checkWinner() {
        // Checks for diagonal win
        if (!BOARD[1][1].getValue().equals("")) {
            if ((BOARD[0][0].getValue().equals(BOARD[1][1].getValue()) && BOARD[0][0].getValue().equals(BOARD[2][2].getValue())) ||
                    (BOARD[2][0].getValue().equals(BOARD[1][1].getValue()) && BOARD[2][0].getValue().equals(BOARD[0][2].getValue()))) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
           // Checks for horizontal win
           if (!BOARD[i][0].getValue().equals("")) {
               if (BOARD[i][0].getValue().equals(BOARD[i][1].getValue()) &&
                   BOARD[i][0].getValue().equals(BOARD[i][2].getValue())) {
                   return true;
               }
           }
           // Checks for vertical win
           if (!BOARD[0][i].getValue().equals("")) {
               if (BOARD[0][i].getValue().equals(BOARD[1][i].getValue()) &&
                   BOARD[0][i].getValue().equals(BOARD[2][i].getValue())) {
                   return true;
               }
           }
        }
        return false;
    }

    // Returns true if space is empty, and false otherwise
    public boolean spaceIsEmpty(int x, int y) {
        return BOARD[x][y].getValue().equals("");
    }

    // Sets button pressed to either X or O, depending on whose turn it is.
    public void markSpot(int x, int y, Image xIcon, Image oIcon) {
        BOARD[x][y].setValue(Character.toString(turn));
        if (turn == 'X') {
            BOARD[x][y].setGraphic(new ImageView(xIcon));
        }
        else {
            BOARD[x][y].setGraphic(new ImageView(oIcon));
        }
    }

    // Changes turn from X to O, or O to X after user makes a move
    public void changeTurn() {
        if (turn == 'X') {
            turn = 'O';
        } else {
            turn = 'X';
        }
    }

    // Returns true if there is a winner, or if all spaces on the board have been filled
    public boolean gameOver() {
        if (checkWinner()) {
            return true;
        }
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!BOARD[i][j].getValue().equals("")) {
                    counter++;
                }
            }
        }
        return counter == 9;
    }

    // Clears board
    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                BOARD[i][j].setGraphic(null);
                BOARD[i][j].setValue("");
            }
        }
        turn = 'X';
    }

}
