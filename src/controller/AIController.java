package controller;

import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import model.ChessColor;
import model.Player;
import view.ChessGameFrame;
import view.Chessboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AIController {
    private Chessboard chessboard;

    public AIController(Chessboard chessboard) {
        this.chessboard = chessboard;
        AIPlayer();
    }

    private int cnt, layer;
    private void AIPlayer() {
        layer++;
        if(layer == 5) {
            layer--;
            System.out.println(layer);
            return;
        }
        SquareComponent[][] chess = chessboard.getChessComponents();
        for(int i=0;i<8;i++) {
            for(int j=0;j<4;j++) {
                if(!chess[i][j].isReversal()) {
                    chess[i][j].setReversal(true);
                    System.out.printf("%d %d\n",i,j);
                    AIPlayer();
                    if(layer == 4) {

                    }
                }
            }
        }
    }
}
