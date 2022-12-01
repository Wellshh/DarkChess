package controller;


import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import model.ChessColor;
import model.Player;
import view.ChessGameFrame;
import view.Chessboard;

public class ClickControllerCheat {
    private final Chessboard chessboard;
    private SquareComponent first;

    public ClickControllerCheat(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

//    public static int cnt=0;

    public void onClick(SquareComponent squareComponent) {
        if(!squareComponent.isReversal()){
            squareComponent.setCheckCheat(1);
        }
        squareComponent.setReversal(true);
        squareComponent.repaint();
    }

}
