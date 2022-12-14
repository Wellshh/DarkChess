package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class SoldierChessComponent extends ChessComponent {

    public SoldierChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        rank = 0;
        score = 1;
        if (this.getChessColor() == ChessColor.RED) {
            name = "兵";
            code = "00";
        } else {
            name = "卒";
            code = "10";
        }
    }

    public SoldierChessComponent(ChessColor chessColor) {
        super(chessColor);
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        if (isReversal()) {
            this.reversal = "1";
            if (this.getChessColor() == ChessColor.BLACK) {
                addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\ipad_chess-zu3@2x.png", chessPicture);
                chessPicture.setVisible(true);
            } else {
                addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\ipad_chess-bing-red3@2x.png", chessPicture);
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
