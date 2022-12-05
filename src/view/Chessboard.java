package view;

import chessComponent.*;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent {
    public Stack<List<String>> stack = new Stack<>();//哥哥


    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 4;

    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player
    private ChessColor currentColor = ChessColor.BLACK;


    //all chessComponents in this chessboard are shared only one model controller
    public final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;


    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width + 2, height - 1);
        CHESS_SIZE = (height - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);

        initAllChessOnBoard();//Test
    }

    public SquareComponent[][] getChessComponents() {
        return squareComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * 将SquareComponent 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     * @param squareComponent
     */
    public void putChessOnBoard(SquareComponent squareComponent) {
        int row = squareComponent.getChessboardPoint().getX(), col = squareComponent.getChessboardPoint().getY();
        if (squareComponents[row][col] != null) {
            remove(squareComponents[row][col]);
        }
        add(squareComponents[row][col] = squareComponent);
    }

    /**
     * 交换chess1 chess2的位置
     * 如果chess2是空格子，只是相当于交换；如果chess2不是空格子，chess2被摧毁
     * @param chess1
     * @param chess2
     */
    public void swapChessComponents(SquareComponent chess1, SquareComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            if( chess2.getChessColor() == ChessColor.BLACK){
                Player.changeScoreRed( chess2.getScore() );
            }
            else {
                Player.changeScoreBlack( chess2.getScore() );
            }
            remove(chess2);

            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));//移除组件后把底下的格子得添上
        }
        chess1.swapLocation(chess2);//把chess1组件换到chess2空格子的位置，数组上也换了位置
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        squareComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        squareComponents[row2][col2] = chess2;//归零,重新把数组填好

        //只重新绘制chess1 chess2，其他不变
        chess1.repaint();
        chess2.repaint();

    }


    /**
    放棋子
     */
    private void initAllChessOnBoard() {
        Random random = new Random();
        int[][] a=new int[8][4];
        int[] b;
        for(int p=0;p<=1;p++){
            b=new int[7];
            for(int q=0;q<16;q++){
                if(p==0){
                    ChessColor color=ChessColor.RED;
                    SquareComponent squareComponent;
                    int i=random.nextInt(8);
                    int j=random.nextInt(4);
                    while(a[i][j]==1){
                        i=random.nextInt(8);
                        j=random.nextInt(4);
                    }
                    a[i][j]=1;
                    int k=random.nextInt(7),check=0;
                    while(check==0){
                        if(k==0){
                            if(b[k]==1){
                                k=random.nextInt(7);
                                continue;
                            }
                        }
                        else if(k==5){
                            if(b[k]==5){
                                k=random.nextInt(7);
                                continue;
                            }
                        }
                        else if(b[k]==2){
                            k=random.nextInt(7);
                            continue;
                        }
                        check=1;
                        if(k==0) {
                            squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==1){
                            squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==2) {
                            squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==3) {
                            squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==4) {
                            squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==5) {
                            squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else{
                            squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }
                }
                else{
                    ChessColor color=ChessColor.BLACK;
                    SquareComponent squareComponent;
                    int i=random.nextInt(8);
                    int j=random.nextInt(4);
                    while(a[i][j]==1){
                        i=random.nextInt(8);
                        j=random.nextInt(4);
                    }
                    a[i][j]=1;
                    int k=random.nextInt(7),check=0;
                    while(check==0){
                        if(k==0){
                            if(b[k]==1){
                                k=random.nextInt(7);
                                continue;
                            }
                        }
                        else if(k==5){
                            if(b[k]==5){
                                k=random.nextInt(7);
                                continue;
                            }
                        }
                        else if(b[k]==2){
                            k=random.nextInt(7);
                            continue;
                        }
                        check=1;
                        if(k==0) {
                            squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==1){
                            squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==2) {
                            squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==3) {
                            squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==4) {
                            squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else if(k==5) {
                            squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        else{
                            squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            b[k]++;
                        }
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                    }
                }
            }
        }
    }

    /**
     * 绘制棋盘格子
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 将棋盘上行列坐标映射成Swing组件的Point
     * @param row 棋盘上的行
     * @param col 棋盘上的列
     * @return
     */
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    /**
     * 通过GameController调用该方法
     * @param chessData
     */
    public void loadGame(List<String> chessData) {
        chessData.forEach(System.out::println);
        clickController.setFirst(null);
        for(int i=0;i<8;i++) {
            String[] chess = chessData.get(i).split(",");
            for(int j=0;j<4;j++) {
                if(chess[j].charAt(0) == '0') {
                    char k=chess[j].charAt(1);
                    ChessColor color = ChessColor.RED;
                    SquareComponent squareComponent;
                    if(k == '6') {
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '5'){
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '4') {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '3') {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '2') {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '0') {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else{
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }

                    if(chess[j].charAt(2) == '0') {
                        squareComponent.setReversal(false);
                        squareComponent.setReversal("0");
                    }
                    else {
                        squareComponent.setReversal(true);
                        squareComponent.setReversal("1");
                    }
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }
                else if(chess[j].charAt(0) == '1') {
                    char k=chess[j].charAt(1);
                    ChessColor color = ChessColor.BLACK;
                    SquareComponent squareComponent;
                    if(k == '6') {
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '5'){
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '4') {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '3') {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '2') {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else if(k == '0') {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    else{
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }

                    if(chess[j].charAt(2) == '0') {
                        squareComponent.setReversal(false);
                        squareComponent.setReversal("0");
                    }
                    else {
                        squareComponent.setReversal(true);
                        squareComponent.setReversal("1");
                    }
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }
                else {
                    SquareComponent squareComponent = new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE);
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }
            }
        }
        repaint();
        ChessGameFrame.getLabelScoreRed().repaint();
        ChessGameFrame.getLabelScoreBlack().repaint();

        if(chessData.get(8).equals("0")) {
            currentColor = ChessColor.RED;
            ChessGameFrame.getLabelTurn().setText(String.format("%s's TURN", getCurrentColor().getName()));
        }
        else {
            currentColor = ChessColor.BLACK;
            ChessGameFrame.getLabelTurn().setText(String.format("%s's TURN", getCurrentColor().getName()));
        }

        int scoreRed = Integer.parseInt(chessData.get(9));
        Player.changeScoreRed(scoreRed);

        int scoreBlack = Integer.parseInt(chessData.get(10));
        Player.changeScoreBlack(scoreBlack);

        ClickController.cnt = Integer.parseInt(chessData.get(11));

    }
}
