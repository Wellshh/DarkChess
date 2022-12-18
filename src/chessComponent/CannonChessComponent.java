package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示黑红炮
 */
public class CannonChessComponent extends ChessComponent {

    public CannonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        rank = 1;
        score = 5;
        if (this.getChessColor() == ChessColor.RED) {
            name = "炮";
            code = "01";
        } else {
            name = "砲";
            code = "11";
        }
    }

    public CannonChessComponent(ChessColor chessColor) {
        super(chessColor);
    }

    @Override
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination){
        SquareComponent destinationChess1 = chessboard[destination.getX()][destination.getY()];
        if( destinationChess1 instanceof EmptySlotComponent ){
            return false;
        }

        if(chessboardPoint.getX() == destination.getX() || chessboardPoint.getY() == destination.getY()){
            int cnt=0;
            if(chessboardPoint.getX() == destination.getX()) {
                int y1=chessboardPoint.getY(), y2=destination.getY();
                if(y1>y2){
                    int y=y1;
                    y1=y2;
                    y2=y;
                }
                y1++;
                while(y1<y2){
                    SquareComponent destinationChess = chessboard[destination.getX()][y1];
                    if(! (destinationChess instanceof EmptySlotComponent)){
                        cnt++;
                    }
                    y1++;
                }
            }
            else {
                int x1=chessboardPoint.getX(), x2=destination.getX();
                if(x1>x2){
                    int x=x1;
                    x1=x2;
                    x2=x;
                }
                x1++;
                while(x1<x2){
                    SquareComponent destinationChess = chessboard[x1][destination.getY()];
                    if(! (destinationChess instanceof EmptySlotComponent)){
                        cnt++;
                    }
                    x1++;
                }
            }
            if(cnt == 1){
                return true;
            }
            else {
                System.out.println("invalid operation");
                return false;
            }
        }
        else {
            System.out.println("invalid operation");
            return false;
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        if(hasGrid == true){
            super.paintComponent(g);}
        if (isReversal()) {
            this.reversal = "1";
            if (this.getChessColor() == ChessColor.BLACK) {
                if(chessSkin == 0){
                addlabel("src/assets/ipad_chess-pao3@2x.png",chessPicture);}
                else if(chessSkin ==1){addlabel("src/assets/类一/qipan_hei_pao.png",chessPicture);}
                else{addlabel("src/assets/类二/ipad_chess-pao4@2x.png",chessPicture);}
                chessPicture.setVisible(true);
            }
            else {
                if(chessSkin ==0){
                addlabel("src/assets/ipad_chess-pao-red3@2x.png",chessPicture);}
                else if(chessSkin ==1){addlabel("src/assets/类一/qipan_hong_pao.png",chessPicture);}
                else{addlabel("src/assets/类二/ipad_chess-pao-red4@2x.png",chessPicture);}
                chessPicture.setVisible(true);
            }
        }
        else{
            this.reversal = "0";
            addlabel("src/assets/15.png",chessPicture);

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
