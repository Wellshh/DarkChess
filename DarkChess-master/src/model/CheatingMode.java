package model;

import chessComponent.SquareComponent;
import controller.ClickController;
import controller.GameController;
import model.CheatingMode;
import model.Player;
import view.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class CheatingMode {

    Chessboard chessboard;

    private ClickController clickController;

    public CheatingMode(Chessboard chessboard){
        this.chessboard = chessboard;
    }

    /**
     * 进入作弊模式
     */
    public void enterCheatingMode() {
        clickController = new ClickController(chessboard);
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                if(!chessboard.getChessComponents()[i][j].isReversal()){
                    SquareComponent chess = chessboard.getChessComponents()[i][j];
                    clickController.onClick(chess);
                }
            }
        }
    }

    /**
     * 退出作弊模式
     */
    public void existCheatingMode() {
        clickController = new ClickController(chessboard);
    }
}
