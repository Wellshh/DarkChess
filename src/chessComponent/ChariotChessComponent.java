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
}
