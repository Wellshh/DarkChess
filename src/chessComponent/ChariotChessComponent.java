package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红车
 */
public class ChariotChessComponent extends ChessComponent {

    public ChariotChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        rank = 3;
        score = 5;
        if (this.getChessColor() == ChessColor.RED) {
            name = "俥";
            code = "03";
        } else {
            name = "車";
            code = "13";
        }
    }

    public ChariotChessComponent(ChessColor chessColor) {
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
                addlabel("src/assets/ipad_chess-ju3@2x.png", chessPicture);}
                else if(chessSkin ==1){addlabel("src/assets/类一/qipan_hei_che.png",chessPicture);}
                else {addlabel("src/assets/类二/ipad_chess-ju4@2x.png",chessPicture);}
                chessPicture.setVisible(true);
            } else {
                if(chessSkin == 0){
                addlabel("src/assets/ipad_chess-ju-red3@2x.png", chessPicture);}
                else if(chessSkin ==1){addlabel("src/assets/类一/qipan_hong_che.png",chessPicture);}
                else{addlabel("src/assets/类二/ipad_chess-ju-red4@2x.png",chessPicture);}
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
