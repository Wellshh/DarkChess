package controller;

import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import model.ChessColor;
import model.ChessboardPoint;
import model.Player;
import view.ChessGameFrame;
import view.Chessboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AIController {

    Chessboard chessboard;

    private int color;

    int[][][] chess = new int[10][10][4]; //0位：颜色 0红 1黑；1位：翻开状态 0 未翻开 1 翻开；2位：棋子编号；3位：分数

    public AIController(Chessboard chessboard, ChessColor chessColor) {
        if(chessColor==ChessColor.RED) {
            color=1;
        }
        else {
            color=0;
        }
        this.chessboard = chessboard;
        SquareComponent[][] chessCom = chessboard.getChessComponents();
        //构造棋盘
        for(int i=0;i<8;i++) {
            for(int j=0;j<4;j++) {
                //存储棋子颜色
                if(chessCom[i][j] instanceof EmptySlotComponent) {
                    chess[i][j][0] = -1;
                    continue;
                }
                if(chessCom[i][j].getChessColor() == ChessColor.RED) {
                    chess[i][j][0] = 0;
                }
                else {
                    chess[i][j][0] = 1;
                }

                //存储棋子翻开状态
                if(chessCom[i][j].isReversal()) {
                    chess[i][j][1]=1;
                }
                else {
                    chess[i][j][1]=0;
                }
                //存储棋子编号
                chess[i][j][2]=chessCom[i][j].getRank();

                //存储棋子分数
                chess[i][j][3] = chessCom[i][j].getScore();
            }
        }
    }

    public int aiPlayer() {
        int ans1 = -1, ans2 = -1;
        int x0, y0, x1, y1, x2, y2;
        for(int i=0;i<8;i++) {
            for(int j=0;j<4;j++) {
                if(chess[i][j][0] == -1) {
                    continue;
                }
                //翻棋子
                if(chess[i][j][1] == 0) {
                    chess[i][j][0] = 1;
                    int val = Dfs(chess,1, -1000, 1000, false);
                    if(ans1 < val) {
                        x0 = i; y0 = j;
                        ans1 = val;
                    }
                    chess[i][j][0] = 0;
                }

                //移动棋子
                for(int m=0;m<8;m++){
                    for(int n=0;n<4;n++){
                        if((m!=i || n!=j) && chess[i][j][0]==color) {
                            if(checkDestination(i,j,m,n)) {
                                int[] t1=Arrays.copyOf(chess[i][j],4);
                                int[] t2=Arrays.copyOf(chess[m][n],4);
                                int val = Dfs(chess,1, -1000, 1000, false);
                            }
                        }

                    }
                }
            }
        }
        if(ans1 > ans2) {
            return x0 * 100 + y0 * 10;
        }
        else {

        }
    }

    public int Dfs(int[][][] chess, int layer, int a, int b, boolean isAI) {
        if(layer == 3) {
            return scoreCount(chess);
        }
        if(isAI) {
            return a;
        }
        else {
            return b;
        }
    }

    public int scoreCount(SquareComponent[][] chess) {
        int scoreRed = 95, scoreBlack = 95;
        for(int i=0;i<8;i++) {
            for(int j=0;j<4;j++) {
                if(chess[i][j].getChessColor().equals(ChessColor.BLACK)) {
                    scoreBlack -= chess[i][j].getScore();
                }
                else {
                    scoreRed -= chess[i][j].getScore();
                }
            }
        }
        return scoreRed - scoreBlack;
    }

    public boolean checkDestination(int x1, int y1, int x2, int y2) {

        if(x1!=x2 && y1!=y2) {
            return false;
        }
        if(chess[x1][y1][2] ==1) {
            int cnt=0;
            if(x1==x2) {
                for(int j=Math.min(y1,y2);j<Math.max(y1,y2);j++) {
                    if(chess[x1][j][0]!=-1) {
                        cnt++;
                    }
                }
            }
            else {
                for(int i=Math.min(x1,x2);i<Math.max(x1,x2);i++) {
                    if(chess[i][y1][0]!=-1) {
                        cnt++;
                    }
                }
            }
            return cnt==1;
        }
        else {
            return chess[x1][y1][3]>=chess[x2][y2][3] && Math.abs(x1-x2)+Math.abs(y1-y2)==1;
        }
    }
}
