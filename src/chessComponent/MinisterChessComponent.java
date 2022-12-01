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
}
