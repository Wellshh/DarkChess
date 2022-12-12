package chessComponent;

import controller.ClickController;
import model.ChessColor;

import java.awt.*;

public class FakeChessComponent extends SquareComponent{

    public  FakeChessComponent(String name, ChessColor chessColor, ClickController clickController, int size) {
        super(name, chessColor, clickController, size);
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Asdfasd");
        super.paintComponent(g);
        //绘制棋子填充色
        g.setColor(Color.ORANGE);
        g.fillOval(spacingLength, spacingLength, this.getWidth() - 2 * spacingLength, this.getHeight() - 2 * spacingLength);
        //绘制棋子边框
        g.setColor(Color.DARK_GRAY);
        g.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);
        //绘制棋子文字
        g.setColor(this.getChessColor().getColor());
        g.setFont(CHESS_FONT);
        g.drawString(this.name, this.getWidth() / 4, this.getHeight() * 2 / 3);
    }
}
