package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红王
 */
public class GeneralChessComponent extends ChessComponent {

    public GeneralChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        rank = 6;
        score = 30;
        if (this.getChessColor() == ChessColor.RED) {//
            name = "帥";
            code = "06";
        } else {
            name = "將";
            code = "16";
        }
    }

    public GeneralChessComponent(ChessColor chessColor) {
        super(chessColor);
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        if (isReversal()) {
            this.reversal = "1";
            if (this.getChessColor() == ChessColor.BLACK) {
                addlabel("src/assets/ipad_chess-jiang3@2x.png", chessPicture);
                chessPicture.setVisible(true);
            } else {
                addlabel("src/assets/ipad_chess-shuai-red3@2x.png", chessPicture);
                chessPicture.setVisible(true);
            }
        } else {
            this.reversal = "0";
            chessPicture.setVisible(false);
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
