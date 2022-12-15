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
//        super.paintComponent(g);
        if (isReversal()) {
            this.reversal = "1";
            if (this.getChessColor() == ChessColor.BLACK) {
                addlabel("src/assets/ipad_chess-shi3@2x.png",chessPicture);
                chessPicture.setVisible(true);
            }
            else {
                addlabel("src/assets/ipad_chess-shi-red3@2x.png",chessPicture);
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
