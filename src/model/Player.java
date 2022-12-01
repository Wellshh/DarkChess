package model;

import view.ChessGameFrame;
import view.WinnerScreen;

public class Player {
    public static int scoreRed;
    public static int scoreBlack;

    public static void changeScoreRed( int score) {
        scoreRed += score;
        ChessGameFrame.getLabelScoreRed().setText(String.format("RED's score: %d", scoreRed));
    }

    public static  void changeScoreBlack( int score) {
        scoreBlack += score;
        ChessGameFrame.getLabelScoreBlack().setText(String.format("BLACK's score: %d", scoreBlack));
    }

    public  static void checkWinner() {
        if( scoreRed >= 60 || scoreBlack >= 60) {
            WinnerScreen winnerScreen = new WinnerScreen(500,500);
            winnerScreen.setVisible(true);
        }
    }
}
