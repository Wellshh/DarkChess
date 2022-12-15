package chessComponent;

import controller.ClickController;
import controller.ClickControllerCheat;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

/**
 * 表示棋盘上非空棋子的格子，是所有非空棋子的父类
 */
public class ChessComponent extends SquareComponent {
    public boolean hasGrid = true;
    public static int chessSkin = 0;
    protected String name;// 棋子名字：例如 兵，卒，士等

    public ChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
    }

    public ChessComponent(ChessColor chessColor){
        super(chessColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        //绘制棋子填充色
//        g.setColor(Color.ORANGE);
//        g.fillOval(spacingLength, spacingLength, this.getWidth() - 2 * spacingLength, this.getHeight() - 2 * spacingLength);
//        //绘制棋子边框
//        g.setColor(Color.DARK_GRAY);
//        g.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);
//
//        if (isReversal) {
//            //绘制棋子文字
//            reversal = "1";
//            g.setColor(this.getChessColor().getColor());
//            g.setFont(CHESS_FONT);
//            g.drawString(this.name, this.getWidth() / 4, this.getHeight() * 2 / 3);
//
//            //绘制棋子被选中时状态
//            if (isSelected()) {
//                g.setColor(Color.RED);
//                Graphics2D g2 = (Graphics2D) g;
//                g2.setStroke(new BasicStroke(4f));
//                g2.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);
//            }
//        }
//
//        if (ableToMove) {
//            g.setColor(Color.BLUE);
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setStroke(new BasicStroke(4f));
//            g2.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);
//        }
    }

    @Override
    void addlabel(String filename,JLabel label) {
        ImageIcon bg = new ImageIcon(filename);
        ImageIcon imageIcon = new ImageIcon(filename);
        Image temp = imageIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), imageIcon.getImage().SCALE_DEFAULT);
        imageIcon = new ImageIcon(temp);
        label.setIcon(imageIcon);
        label.setVisible(true);
        add(label);
        label.setSize(this.getWidth(), this.getHeight());
    }
}
//}
