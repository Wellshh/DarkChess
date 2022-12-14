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
//        super.paintComponent(g);
        if (isReversal()) {
            this.reversal = "1";
            if (this.getChessColor() == ChessColor.BLACK) {
                addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\ipad_chess-xiang3@2x.png",chessPicture);
                chessPicture.setVisible(true);
            }
            else {
                addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\ipad_chess-xiang-red3@2x.png",chessPicture);
                chessPicture.setVisible(true);
            }
        }
        else{
            this.reversal = "0";
            chessPicture.setVisible(false);
        }
        if(isSelected()){
            addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\rect_red.png",movePicture);
            movePicture.setVisible(true);
        }
        else{
            movePicture.setVisible(false);
        }
        if(ableToMove){
            addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\rect_blue.png",optionPicture);
            optionPicture.setVisible(true);
        }
        else {
            optionPicture.setVisible(false);
        }
    }
}
