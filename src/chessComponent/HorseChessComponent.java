package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红马
 */
public class HorseChessComponent extends ChessComponent {

    public HorseChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        rank = 2;
        score = 5;
        if (this.getChessColor() == ChessColor.RED) {
            name = "傌";
            code = "02";
        } else {
            name = "馬";
            code = "12";
        }
    }

    public HorseChessComponent(ChessColor chessColor) {
        super(chessColor);
    }

    @Override
    public void paintComponent(Graphics g) {
        if(hasGrid == true){
            super.paintComponent(g);}
        if (isReversal()) {
            this.reversal = "1";
            if (this.getChessColor() == ChessColor.BLACK) {
                if(chessSkin == 0){
                addlabel("src/assets/ipad_chess-ma3@2x.png", chessPicture);}
                else if(chessSkin == 1){addlabel("src/assets/类一/qipan_hei_ma.png",chessPicture);}
                else{addlabel("src/assets/类二/ipad_chess-ma4@2x.png",chessPicture);}
                chessPicture.setVisible(true);
            } else {
                if(chessSkin == 0){
                addlabel("src/assets/ipad_chess-ma-red3@2x.png", chessPicture);}
                else if(chessSkin == 1){addlabel("src/assets/类一/qipan_hong_ma.png",chessPicture);}
                else{addlabel("src/assets/类二/ipad_chess-ma-red4@2x.png",chessPicture);}
                chessPicture.setVisible(true);
            }
        } else {
            this.reversal = "0";
            addlabel("src/assets/15.png",chessPicture);
        }
        if (isSelected()) {
            addlabel("src/assets/rect_red.png", movePicture);
            movePicture.setVisible(true);
        } else {
            movePicture.setVisible(false);
        }
        if (ableToMove) {
            addlabel("src/assets/rect_blue.png", optionPicture);
            optionPicture.setVisible(true);
        } else {
            optionPicture.setVisible(false);
        }
    }
}
