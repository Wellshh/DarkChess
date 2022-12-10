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
import java.util.Random;

public class AIController {

    Chessboard chessboard;

    private int color;

    int[][][] chess = new int[10][10][4]; //0位：颜色 0红 1黑；1位：翻开状态 0 未翻开 1 翻开；2位：棋子编号；3位：分数

    public AIController(Chessboard chessboard, ChessColor chessColor) {
        if(chessColor==ChessColor.RED) {
            color=0;
        }
        else {
            color=1;
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

        for(int i=0;i<8;i++) {
            for(int j=0;j<4;j++) {
                System.out.printf("%d%d%d%d ", chess[i][j][0],chess[i][j][1],chess[i][j][2],chess[i][j][3]);
            }
            System.out.println();
        }
    }

    int[] rev = new int[3];
    int[] swa = new int[5];
    public int[] aiPlayer() {
        int a = -1000,b;
        for(int i=0;i<8;i++) {
            for(int j=0;j<4;j++) {
                if(chess[i][j][0] == -1) {
                    continue;
                }
                //翻棋子
                if(chess[i][j][1] == 0) {
                    chess[i][j][1] = 1;
                    int val = Dfs(chess,1, a, 1000, false);
                    a=Math.max(a,val);
                    if(rev[0] < val) {
                        rev[0] = val;
                        rev[1] = i;
                        rev[2] = j;
                    }
                    chess[i][j][1] = 0;
                }
                //移动棋子
                if(chess[i][j][1]==1 && chess[i][j][0]==color) {
                    for(int m=0;m<8;m++){
                        for(int n=0;n<4;n++){
                            if((m!=i || n!=j)) {
                                if(checkDestination(i,j,m,n)) {
                                    int col = chess[m][n][0];
                                    int re = chess[m][n][1];
                                    chess[m][n][1]=1;
                                    swapChess(chess,i,j,m,n);
                                    chess[i][j][0]=-1;
                                    int val = Dfs(chess,1, a, 1000, false);
                                    a=Math.max(a,val);
                                    chess[i][j][0] = -1;
                                    if(swa[0]<val) {
                                        swa[0] = val;
                                        swa[1] = i;
                                        swa[2] = j;
                                        swa[3] = m;
                                        swa[4] = n;
                                    }
                                    chess[i][j][1]=re;
                                    chess[i][j][0]=col;
                                    swapChess(chess,m,n,i,j);
                                }
                            }

                        }
                    }
                }

            }
        }
        if(rev[0]>=swa[0]) {
            if(rev[0]==0) {
                Random ran = new Random();
                rev[1]= ran.nextInt(8);
                rev[2]=ran.nextInt(4);
                while(chess[rev[1]][rev[2]][0]==-1 || chess[rev[1]][rev[2]][1]==1) {
                    rev[1]= ran.nextInt(8);
                    rev[2]=ran.nextInt(4);
                }
            }
            return rev;
        }
        else {
            return swa;
        }
    }

    public int Dfs(int[][][] chess, int layer, int a, int b, boolean isAI) {
        System.out.println();
        System.out.printf("%d %d\n",a,b);
        System.out.println();
        if(layer == 2) {
            return scoreCount(chess);
        }
        if(isAI) {
            for(int i=0;i<8;i++) {
                for(int j=0;j<4;j++) {
                    if(chess[i][j][0] == -1) {
                        continue;
                    }
                    //翻棋子
                    if(chess[i][j][1] == 0) {
                        chess[i][j][1] = 1;
                        System.out.printf("%d %d\n",a,b);
                        a=Math.max(a,Dfs(chess,layer+1, a, b, false));
                        chess[i][j][1] = 0;
                        if(a>b) {
                            return a;
                        }
                    }
                    //移动棋子
                    if(chess[i][j][1]==1) {
                        for(int m=0;m<8;m++){
                            for(int n=0;n<4;n++){
                                if((m!=i || n!=j) && chess[i][j][0]==color) {
                                    if(checkDestination(i,j,m,n)) {
                                        int col = chess[m][n][0];
                                        int re = chess[m][n][1];
                                        swapChess(chess,i,j,m,n);
                                        chess[i][j][0] = -1;
                                        System.out.printf("%d %d\n",a,b);
                                        a=Math.max(a,Dfs(chess,layer+1, a, b, false));
                                        chess[i][j][0]=col;
                                        chess[i][j][1]=re;
                                        swapChess(chess,m,n,i,j);
                                        if(a>b) {
                                            return a;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return a;
        }
        else {
            for(int i=0;i<8;i++) {
                for(int j=0;j<4;j++) {
                    if(chess[i][j][0] == -1) {
                        continue;
                    }
                    //翻棋子
                    if(chess[i][j][1] == 0) {
                        chess[i][j][1] = 1;
                        System.out.printf("%d %d\n",a,b);
                        b=Math.min(b,Dfs(chess,layer+1, a, b, true));
                        chess[i][j][1] = 0;
                        if(a>b) {
                            return b;
                        }
                    }
                    //移动棋子
                    if(chess[i][j][1]==1){
                        for(int m=0;m<8;m++){
                            for(int n=0;n<4;n++){
                                if((m!=i || n!=j) && chess[i][j][0]!=color) {
                                    if(checkDestination(i,j,m,n)) {
                                        int col = chess[m][n][0];
                                        int re=chess[m][n][1];
                                        swapChess(chess,i,j,m,n);
                                        chess[i][j][0] = -1;
                                        System.out.printf("%d %d\n",a,b);
                                        b=Math.min(b,Dfs(chess,layer+1, a, b, true));
                                        chess[i][j][0]=col;
                                        chess[i][j][1]=re;
                                        swapChess(chess,m,n,i,j);
                                        if(a>b) {
                                            return b;
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
            return b;
        }
    }

    public int scoreCount(int[][][] chess) {
        int scoreRed = 95, scoreBlack = 95;
        for(int i=0;i<8;i++) {
            for(int j=0;j<4;j++) {
                if(chess[i][j][0] == 1) {
                    scoreRed -= chess[i][j][3];
                }
                else {
                    scoreBlack -= chess[i][j][3];
                }
            }
        }
        if(color == 0) {
            //System.out.printf("%d %d\n",scoreRed,scoreBlack);
            return scoreRed - scoreBlack;
        }
        else {
            //System.out.printf("%d %d\n",scoreRed,scoreBlack);
            return scoreBlack - scoreRed;
        }
    }

    public boolean checkDestination(int x1, int y1, int x2, int y2) {

        if(x1!=x2 && y1!=y2) {
            return false;
        }
        if(chess[x1][y1][2] ==1) {
            int cnt=0;
            if(x1==x2) {
                for(int j=Math.min(y1,y2)+1;j<Math.max(y1,y2);j++) {
                    if(chess[x1][j][0]!=-1) {
                        cnt++;
                    }
                }
            }
            else {
                for(int i=Math.min(x1,x2)+1;i<Math.max(x1,x2);i++) {
                    if(chess[i][y1][0]!=-1) {
                        cnt++;
                    }
                }
            }
            return cnt==1;
        }
        else {
            return ((chess[x2][y2][0]==-1)||(chess[x1][y1][3]>=chess[x2][y2][3] && chess[x1][y1][0]!=chess[x2][y2][0]))
                    && Math.abs(x1-x2)+Math.abs(y1-y2)==1 && chess[x2][y2][1]==1;
        }
    }

    public void swapChess(int[][][] chess,int x1,int y1,int x2,int y2) {
        int[] t1=Arrays.copyOf(chess[x1][y1],4);
        int[] t2=Arrays.copyOf(chess[x2][y2],4);
        chess[x1][y1] = Arrays.copyOf(t2,4);
        chess[x2][y2] = Arrays.copyOf(t1,4);
    }
}
