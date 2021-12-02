package com.example.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Setup
        AnchorPane welcomePane = new AnchorPane();
        AnchorPane mainPane = new AnchorPane();

        Scene welcomeScene = new Scene(welcomePane, 415, 480);
        Scene mainScene = new Scene(mainPane, 415, 480);

        welcomeScene.getStylesheets().add("styles.css");
        mainScene.getStylesheets().add("styles.css");

        stage.getIcons().add(new Image("file:src/main/resources/icon.png"));
        stage.setTitle("Tic Tac Toe");
        stage.setScene(welcomeScene);
        stage.show();
        // End setup

        // welcomeScene setup
        Label labelWelcomeText = new Label("Tic Tac Toe!");
        labelWelcomeText.getStyleClass().add("labelWelcomeText");

        Button buttonPlayNow = new Button("Play now!");
        buttonPlayNow.setPrefSize(100, 45);
        buttonPlayNow.setOnAction((ActionEvent event) -> stage.setScene(mainScene));

        AnchorPane.setLeftAnchor(labelWelcomeText, 63.0);
        AnchorPane.setTopAnchor(labelWelcomeText, 20.0);

        AnchorPane.setLeftAnchor(buttonPlayNow, 150.0);
        AnchorPane.setTopAnchor(buttonPlayNow, 110.0);

        welcomePane.getChildren().addAll(labelWelcomeText, buttonPlayNow);
        // End of welcomeScene setup

        // mainScene setup

        // Images of X and O
        Image xIcon = new Image("x-icon.png", 100, 100, false, false);
        Image oIcon = new Image("o-icon.png", 100, 100, false, false);

        TicTacButton[][] board = new TicTacButton[3][3];
        GameManager gameManager = new GameManager(board, 'X');

        Label labelResponseText = new Label("It is player " + gameManager.getTurn() + "'s turn.");

        Button buttonReplay = new Button("Replay?");
        buttonReplay.setVisible(false);

        Label labelNotValidChoice = new Label("Not a valid choice!");
        labelNotValidChoice.setVisible(false);

        AnchorPane.setLeftAnchor(labelResponseText, 157.0);
        AnchorPane.setTopAnchor(labelResponseText, 20.0);

        AnchorPane.setLeftAnchor(buttonReplay, 175.0);
        AnchorPane.setTopAnchor(buttonReplay, 432.0);

        AnchorPane.setLeftAnchor(labelNotValidChoice, 155.0);
        AnchorPane.setTopAnchor(labelNotValidChoice, 432.0);

        mainPane.getChildren().addAll(labelResponseText, buttonReplay, labelNotValidChoice);

        // Creates board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                // Displays button on screen
                board[i][j] = new TicTacButton(i, j);
                board[i][j].setPrefSize(120, 120);

                AnchorPane.setLeftAnchor(board[i][j], 20 + (125.0 * j));
                AnchorPane.setTopAnchor(board[i][j], 50 + (125.0 * i));

                mainPane.getChildren().addAll(board[i][j]);

                board[i][j].setOnAction((ActionEvent event) -> {
                    TicTacButton clickedTemp = (TicTacButton)event.getSource();

                    // Players may select a box if the game is not over.
                    if (!gameManager.gameOver()) {

                        labelNotValidChoice.setVisible(false);

                        // Players may only select boxes that aren't empty
                        if (gameManager.spaceIsEmpty(clickedTemp.getX(), clickedTemp.getY())) {
                            gameManager.markSpot(clickedTemp.getX(), clickedTemp.getY(), xIcon, oIcon);

                            // Displays the winner if there is one
                            if (gameManager.checkWinner()) {
                                labelResponseText.setText("    Player " + clickedTemp.getValue() + " won!");
                                buttonReplay.setVisible(true);
                            }

                            // Displays if the game ended in a draw
                            else if (gameManager.gameOver() && !gameManager.checkWinner()) {
                                labelResponseText.setText("      It's a draw!");
                                buttonReplay.setVisible(true);
                            }

                            // Continues playing
                            else {
                                gameManager.changeTurn();
                                labelResponseText.setText("It is player " + gameManager.getTurn() + "'s turn.");
                            }
                        }

                        // User selected a non-empty box
                        else {
                            labelNotValidChoice.setVisible(true);
                        }
                    }
                });
            }
        }

        // After the game ends, the replay button is made visible
        buttonReplay.setOnAction((ActionEvent event) -> {
            gameManager.clearBoard();
            labelResponseText.setText("It is player " + gameManager.getTurn() + "'s turn.");
            buttonReplay.setVisible(false);
        });
        // End of mainScene setup
    }

    public static void main(String[] args) {
        launch();
    }
}