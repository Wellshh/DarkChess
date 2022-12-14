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
//        super.paintComponent(g);
        if (isReversal()) {
            this.reversal = "1";
            if (this.getChessColor() == ChessColor.BLACK) {
                addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\ipad_chess-ju3@2x.png", chessPicture);
                chessPicture.setVisible(true);
            } else {
                addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\ipad_chess-ju-red3@2x.png", chessPicture);
                chessPicture.setVisible(true);
            }
        } else {
            this.reversal = "0";
            chessPicture.setVisible(false);
        }
        if (isSelected()) {
            addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\rect_red.png", movePicture);
            movePicture.setVisible(true);
        } else {
            movePicture.setVisible(false);
        }
        if (ableToMove) {
            addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\rect_blue.png", optionPicture);
            optionPicture.setVisible(true);
        } else {
            optionPicture.setVisible(false);
        }
    }
}
