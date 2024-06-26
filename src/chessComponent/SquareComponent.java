package chessComponent;

import Socket.*;
import UI.AudioPlayer;
import controller.ClickController;
import controller.ClickControllerCheat;
import model.ChessColor;
import model.ChessboardPoint;
//import net.sf.json.JSONObject;
import view.ChessGameFrame;
import view.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * 这个类是一个抽象类，主要表示8*4棋盘上每个格子的棋子情况。
 * 有两个子类：
 * 1. EmptySlotComponent: 空棋子
 * 2. ChessComponent: 表示非空棋子
 */
public abstract class SquareComponent extends JComponent {
    private JLayeredPane layeredPane;
    JLabel chessPicture = new JLabel();
    JLabel movePicture = new JLabel();
    JLabel optionPicture = new JLabel();
    public String name;

    private static final Color squareColor = new Color(250, 180, 90);
    protected static int spacingLength;
    protected static final Font CHESS_FONT = new Font("宋体", Font.BOLD, 36);

    protected int rank, score; //棋子顺序，分值

    protected String code;//记录棋子代号

    protected String reversal = "0";//记录棋子翻转状态

    public String getCode() {
        return code;
    }

    public String getReversal() {
        return reversal;
    }

    public void setReversal(String reversal) {
        this.reversal = reversal;
    }

    /**
     * chessboardPoint: 表示8*4棋盘中，当前棋子在棋格对应的位置，如(0, 0), (1, 0)等等
     * chessColor: 表示这个棋子的颜色，有红色，黑色，无色三种
     * isReversal: 表示是否翻转
     * selected: 表示这个棋子是否被选中
     */
    protected ChessboardPoint chessboardPoint;
    protected ChessColor chessColor;

    public void setChessColor(ChessColor chessColor) {
        this.chessColor = chessColor;
    }

    protected boolean isReversal;

    protected boolean ableToMove;
    private boolean selected;

    protected int checkCheat = 0;

    public int getCheckCheat() {
        return checkCheat;
    }

    public void setCheckCheat(int checkCheat) {
        this.checkCheat = checkCheat;
    }

    /**
     * handle click event
     */
    private ClickController clickController = null;

    private ClickControllerCheat clickControllerCheat = null;
    public static int ChessboardSkin = 0;

    protected SquareComponent(String name, ChessColor chessColor, ClickController clickController, int size) {
        this.clickController = clickController;
        clickControllerCheat = new ClickControllerCheat(clickController.getChessboard());
        this.chessColor = chessColor;
        this.name = name;
        setSize(size, size);
        setLayout(new GridLayout(1, 1));
    }


    protected SquareComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);   //鼠标触发事件
        setLocation(location);
        setSize(size, size);
        this.chessboardPoint = chessboardPoint;
        this.chessColor = chessColor;
        this.selected = false;
        this.clickController = clickController;
        this.isReversal = false;
        clickControllerCheat = new ClickControllerCheat(clickController.getChessboard());
    }

    public SquareComponent(ChessColor chessColor) {
        this.chessColor = chessColor;
    }

    public boolean isReversal() {
        return isReversal;
    }

    public void setReversal(boolean reversal) {
        isReversal = reversal;
    }

    public boolean isAbleToMove() {
        return ableToMove;
    }

    public void setAbleToMove(boolean ableToMove) {
        this.ableToMove = ableToMove;
    }

    public static void setSpacingLength(int spacingLength) {
        SquareComponent.spacingLength = spacingLength;
    }

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getRank() {
        return rank;
    }

    public int getScore() {
        return score;
    }

    /**
     * @param another 主要用于和另外一个棋子交换位置
     *                <br>
     *                调用时机是在移动棋子的时候，将操控的棋子和对应的空位置棋子(EmptySlotComponent)做交换
     */
    public void swapLocation(SquareComponent another) {
        ChessboardPoint chessboardPoint1 = getChessboardPoint(), chessboardPoint2 = another.getChessboardPoint();
        Point point1 = getLocation(), point2 = another.getLocation();
        setChessboardPoint(chessboardPoint2);
        setLocation(point2);
        another.setChessboardPoint(chessboardPoint1);//被吃掉地棋子怎么回去了？
        another.setLocation(point1);
    }

    /**
     * @param e 响应鼠标监听事件
     *          <br>
     *          当接收到鼠标动作的时候，这个方法就会自动被调用，调用监听者的onClick方法，处理棋子的选中，移动等等行为。
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            System.out.printf("Click [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
            if (ChessGameFrame.getCheckCheat() == 0) {
//                JSONObject info = new JSONObject();
                System.out.println();
                System.out.printf("%d %d\n",getChessboardPoint().getX(),getChessboardPoint().getY());
                System.out.println();
//                info.put("op",1);
//                info.put("x",this.getChessboardPoint().getX());
//                info.put("y",this.getChessboardPoint().getY());
//                Client.sendInfo(info);
                clickController.onClick(this);
            } else {
                clickControllerCheat.onClick(this);
            }
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(0); //1000 milliseconds
                } catch (InterruptedException k) {
                    k.printStackTrace();
                }
                AudioPlayer.playSound("src/assets/类二/CLICK.WAV");
            });
            t.start();
        }
    }

    /**
     * @param chessboard  棋盘
     * @param destination 目标位置，如(0, 0), (0, 1)等等
     * @return this棋子对象的移动规则和当前位置(chessboardPoint)能否到达目标位置
     * <br>
     * 这个方法主要是检查移动的合法性，如果合法就返回true，反之是false。
     */
    //todo: Override this method for Cannon
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination) {
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        if ((destinationChess instanceof EmptySlotComponent && checkPlace(destination)) || destinationChess.isReversal() &&
                (rank >= destinationChess.getRank() && checkPlace(destination)) || (rank == 0 && destinationChess.getRank() == 6)) {
            return true;
        } else {
            System.out.println("invalid operation");
            return false;
        }
    }

    /**
     * 检查目标旗子位置是否合法
     *
     * @param destination
     * @return
     */
    public boolean checkPlace(ChessboardPoint destination) {
        int x1 = chessboardPoint.getX(), y1 = chessboardPoint.getY();
        int x2 = destination.getX(), y2 = destination.getY();
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) == 1;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        ImageIcon image = null;
        if (ChessboardSkin == 0) {
            image = new ImageIcon("src/assets/b6ee3de5e13755cf90a336b316d5b4b3.jpeg");
        }
        else if(ChessboardSkin == 1){
            image = new ImageIcon("src/assets/类二/ipad_chessboard@2x.png");
        }
        else {image = new ImageIcon("src/assets/类一/pop_common_bg.png");}
        image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
        g.drawImage(image.getImage(), 0, 0, this);
//        add(layeredPane);
//        JLabel label = new JLabel();
//        addlabel("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\assets\\b6ee3de5e13755cf90a336b316d5b4b3.jpeg",label);
//        g.setColor(Color.WHITE);
//        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
    }

    void addlabel(String filename, JLabel label) {
        ImageIcon bg = new ImageIcon(filename);
        ImageIcon imageIcon = new ImageIcon(filename);
        Image temp = imageIcon.getImage().getScaledInstance(this.getWidth() + 10, this.getHeight() + 10, imageIcon.getImage().SCALE_DEFAULT);
        imageIcon = new ImageIcon(temp);
        label.setIcon(imageIcon);
        label.setVisible(true);
        add(label);
        label.setSize(this.getWidth(), this.getHeight());
    }
}
