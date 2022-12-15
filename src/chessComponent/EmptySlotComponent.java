package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 这个类表示棋盘上的空棋子的格子
 */
public class EmptySlotComponent extends SquareComponent {

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size);
        isReversal = true;
        code = "30";
    }

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, int size) {
        super(chessboardPoint, location, ChessColor.NONE, null, size);
        isReversal = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);
        if(ableToMove){
            addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\rect_blue.png",optionPicture);
            optionPicture.setVisible(true);
        }
        else {
            optionPicture.setVisible(false);
        }
    }

    @Override
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }

}
