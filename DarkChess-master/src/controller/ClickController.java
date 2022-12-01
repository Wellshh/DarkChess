package controller;


import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import model.ChessColor;
import model.Player;
import view.ChessGameFrame;
import view.Chessboard;

public class ClickController {
    private final Chessboard chessboard;
    private SquareComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public static int cnt=0;

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void onClick(SquareComponent squareComponent) {
        cnt++;
        if( !(squareComponent.getChessColor() == ChessColor.BLACK) && cnt == 1){
            swapPlayer();
        }

        //判断第一次点击
        if (first == null) {
            if (handleFirst(squareComponent)) {
                squareComponent.setSelected(true);
                first = squareComponent;
                first.repaint();
                showOptions(first);
            }
        } else {
            if (first == squareComponent) { // 再次点击取消选取
                squareComponent.setSelected(false);
                SquareComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
                closeOptions(squareComponent);
            } else if (handleSecond(squareComponent)) {
                closeOptions(first);
                //repaint in swap chess method.
                chessboard.swapChessComponents(first, squareComponent);
                chessboard.clickController.swapPlayer();
                Player.checkWinner();

                first.setSelected(false);
                first = null;
            }
        }
    }

    public void checkDestination(SquareComponent chess, SquareComponent chess1) {
        if(chess.getChessColor() != chess1.getChessColor() && chess1.isReversal() &&
                (chess.getRank() >= chess1.getRank() || (chess.getRank() == 0 && chess1.getRank() == 6)) ||
                chess1 instanceof EmptySlotComponent) {
            chess1.setAbleToMove(true);
        }
    }

    public void checkX(int x, int y) {
        int x1 = x,cnt = 0;
        while(x1 > 0) {
            x1--;
            if(!(chessboard.getChessComponents()[x1][y] instanceof EmptySlotComponent)){
                if(cnt == 1){
                    chessboard.getChessComponents()[x1][y].setAbleToMove(true);
                    chessboard.getChessComponents()[x1][y].repaint();
                    break;
                }
                else {
                    cnt++;
                }
            }
        }

        x1 = x; cnt = 0;
        while(x1 < 7) {
            x1++;
            if(!(chessboard.getChessComponents()[x1][y] instanceof EmptySlotComponent)){
                if(cnt == 1){
                    chessboard.getChessComponents()[x1][y].setAbleToMove(true);
                    chessboard.getChessComponents()[x1][y].repaint();
                    break;
                }
                else {
                    cnt++;
                }
            }
        }
    }

    public void checkY(int x, int y) {
        int y1 = y,cnt = 0;
        while(y1 > 0) {
            y1--;
            if(!(chessboard.getChessComponents()[x][y1] instanceof EmptySlotComponent)){
                if(cnt == 1){
                    chessboard.getChessComponents()[x][y1].setAbleToMove(true);
                    chessboard.getChessComponents()[x][y1].repaint();
                    break;
                }
                else {
                    cnt++;
                }
            }
        }

        y1 = y; cnt = 0;
        while(y1 < 3) {
            y1++;
            if(!(chessboard.getChessComponents()[x][y1] instanceof EmptySlotComponent)){
                if(cnt == 1){
                    chessboard.getChessComponents()[x][y1].setAbleToMove(true);
                    chessboard.getChessComponents()[x][y1].repaint();
                    break;
                }
                else {
                    cnt++;
                }
            }
        }
    }

    /**
     * 选择的棋子可行走的提示
     * @param chess
     */
    public void showOptions(SquareComponent chess) {
        int x = chess.getChessboardPoint().getX(),y = chess.getChessboardPoint().getY();
        /**
         * 若选择到炮车，可行走提示
         */
        if(chess.getRank() == 1) {
            checkX(x,y);
            checkY(x,y);
        }
        /**
         * 若选择非炮车，可行走提示
         */
        else {
            if(x-1 >= 0) {
                SquareComponent chess1 = chessboard.getChessComponents()[x-1][y];
                checkDestination(chess,chess1);
                chess1.repaint();
            }
            if(y+1 <= 3) {
                SquareComponent chess1 = chessboard.getChessComponents()[x][y+1];
                checkDestination(chess,chess1);
                chess1.repaint();
            }
            if(x+1 <= 7) {
                SquareComponent chess1 = chessboard.getChessComponents()[x+1][y];
                checkDestination(chess,chess1);
                chess1.repaint();
            }
            if(y-1 >= 0) {
                SquareComponent chess1 = chessboard.getChessComponents()[x][y-1];
                checkDestination(chess,chess1);
                chess1.repaint();
            }
        }

    }

    /**
     * 关闭选择的棋子可行走的提示
     * @param chess
     */
    public void closeOptions(SquareComponent chess) {
        int x = chess.getChessboardPoint().getX(),y = chess.getChessboardPoint().getY();
        if(chess.getRank() == 1) {
            for(int i=0;i<8;i++){
                for(int j=0;j<4;j++){
                    chessboard.getChessComponents()[i][j].setAbleToMove(false);
                    chessboard.getChessComponents()[i][j].repaint();
                }
            }
        }
        else {
            if(x-1 >= 0) {
                SquareComponent chess1 = chessboard.getChessComponents()[x-1][y];
                chess1.setAbleToMove(false);
                chess1.repaint();
            }
            if(y+1 <= 3) {
                SquareComponent chess1 = chessboard.getChessComponents()[x][y+1];
                chess1.setAbleToMove(false);
                chess1.repaint();
            }
            if(x+1 <= 7) {
                SquareComponent chess1 = chessboard.getChessComponents()[x+1][y];
                chess1.setAbleToMove(false);
                chess1.repaint();
            }
            if(y-1 >= 0) {
                SquareComponent chess1 = chessboard.getChessComponents()[x][y-1];
                chess1.setAbleToMove(false);
                chess1.repaint();
            }
        }
    }


    /**
     * @param squareComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(SquareComponent squareComponent) {
        if (!squareComponent.isReversal()) {
            squareComponent.setReversal(true);
            System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            squareComponent.repaint();
            chessboard.clickController.swapPlayer();
            return false;
        }
        return squareComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param squareComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(SquareComponent squareComponent) {

        //没翻开或空棋子，进入if
        if(!squareComponent.isReversal() && first.getRank()!=1) {
            //没翻开且非空棋子不能走!
            if (!(squareComponent instanceof EmptySlotComponent)) {
                return false;
            }
        }
        return (squareComponent.getChessColor() != chessboard.getCurrentColor() || first.getRank() == 1) &&
                first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());
    }

    public void swapPlayer() {
        chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
        ChessGameFrame.getLabelTurn().setText(String.format("%s's TURN", chessboard.getCurrentColor().getName()));
    }
}
