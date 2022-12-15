package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红相
 */
public class MinisterChessComponent extends ChessComponent {

    public MinisterChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        rank = 4;
        score = 5;
        if (this.getChessColor() == ChessColor.RED) {
            name = "相";
            code = "04";
        } else {
            name = "像";
            code = "14";
        }
    }

    public MinisterChessComponent(ChessColor chessColor) {
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
                addlabel("src/assets/ipad_chess-xiang3@2x.png",chessPicture);}
                else if(chessSkin == 1){addlabel("src/assets/类一/qipan_hei_xiang.png",chessPicture);}
                else{addlabel("src/assets/类二/ipad_chess-xiang4@2x.png",chessPicture);}
                chessPicture.setVisible(true);
            }
            else {
                if(chessSkin == 0){
                addlabel("src/assets/ipad_chess-xiang-red3@2x.png",chessPicture);}
                else if(chessSkin == 1){addlabel("src/assets/类一/qipan_hong_xiang.png",chessPicture);}
                else {addlabel("src/assets/类二/ipad_chess-xiang-red4@2x.png",chessPicture);}
                chessPicture.setVisible(true);
            }
        }
        else{
            this.reversal = "0";
            chessPicture.setVisible(false);
        }
        if(isSelected()){
            addlabel("src/assets/rect_red.png",movePicture);
            movePicture.setVisible(true);
        }
        else{
            movePicture.setVisible(false);
        }
        if(ableToMove){
            addlabel("src/assets/rect_blue.png",optionPicture);
            optionPicture.setVisible(true);
        }
        else {
            optionPicture.setVisible(false);
        }
    }
}
