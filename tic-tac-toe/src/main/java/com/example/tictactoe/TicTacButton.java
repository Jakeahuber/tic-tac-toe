package com.example.tictactoe;

import javafx.scene.control.Button;

public class TicTacButton extends Button {
    private final int x;
    private final int y;
    private String value;

    public TicTacButton(int x, int y) {
        this.x = x;
        this.y = y;
        this.value = "";
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
