package chessComponent;

import controller.ClickController;
import controller.ClickControllerCheat;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

/**
 * 表示黑红士
 */
public class AdvisorChessComponent extends ChessComponent {

    public AdvisorChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        rank = 5;
        score = 10;
        if (this.getChessColor() == ChessColor.RED) {
            name = "仕";
            code = "05";

        } else {
            name = "士";
            code = "15";
        }
    }

    public AdvisorChessComponent(ChessColor chessColor) {
        super(chessColor);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (hasGrid == true) {
            super.paintComponent(g);
        }
        if (isReversal()) {
            this.reversal = "1";
            if (this.getChessColor() == ChessColor.BLACK) {
                if (chessSkin == 0) {
                    addlabel("src/assets/ipad_chess-shi3@2x.png", chessPicture);
                } else if (chessSkin == 1) {
                    addlabel("src/assets/类一/qipan_hei_shi.png", chessPicture);
                } else {
                        addlabel("src/assets/类二/ipad_chess-shi4@2x.png", chessPicture);
                }
                chessPicture.setVisible(true);
            } else {
                if(chessSkin ==0){
                addlabel("src/assets/ipad_chess-shi-red3@2x.png", chessPicture);}
                else if(chessSkin ==1){addlabel("src/assets/类一/qipan_hong_shi.png",chessPicture);}
                else{addlabel("src/assets/类二/ipad_chess-shi-red4@2x.png",chessPicture);}
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
