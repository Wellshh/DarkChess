package view;

import controller.GameController;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import model.*;

import model.*;

public class WinnerScreen extends JFrame {

    private final int WIDTH;

    private final int HEIGHT;

    public WinnerScreen(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        setTitle("Winner Screen");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setBackground(Color.WHITE);
        setLayout(null);

        addWinnerLabel();
        addSureButton();
        addRestartButton();
    }

    public void addWinnerLabel() {
        JLabel label = new JLabel();
        if(Player.scoreBlack > Player.scoreRed){
            label.setText("BLACK wins!");
        }
        else {
            label.setText("RED wins!");
        }
        label.setLocation(WIDTH / 2 - 100, HEIGHT / 2 - 200);
        label.setFont(new Font("Rockwell", Font.BOLD, 30));
        label.setSize(200,200);
        add(label);
    }

    /**
     * 加入 确认 标签
     */
    public void addSureButton() {
        JButton button = new JButton("Confirm");
        button.setLocation(WIDTH / 2 - 75, HEIGHT / 2);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.setSize(150, 35);
        button.addActionListener(e -> {
            System.exit(0);
        });
        add(button);
    }

    /**
     * 加入重新开始按钮
     */
    private void addRestartButton() {
        JButton button = new JButton("Restart");
        button.setLocation(WIDTH /2 - 75, HEIGHT / 2 + 75);
        button.setSize(150, 35);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.addActionListener(e -> {
            ChessGameFrame chessGameFrame = new ChessGameFrame(1000, 1000);
            chessGameFrame.setVisible(true);
            this.dispose();
        });
        add(button);
    }
}
