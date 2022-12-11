package view;

import controller.GameController;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class StartScreen extends JFrame {

    private final int WIDTH; //开始界面宽度

    private final int HEIGHT; //开始界面高度


    public StartScreen( int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        setTitle("Start Screen");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addStartButton();
    }

    private void addStartButton() {
        JButton button = new JButton("Start the game.");
        button.setLocation(WIDTH / 2 - 120, HEIGHT / 2 - 30);
        button.setSize(240, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.addActionListener(e -> {
            ChessGameFrame chessGameFrame = new ChessGameFrame(800, 800);
            chessGameFrame.setVisible(true);

//            WinnerScreen winnerScreen = new WinnerScreen(360, 360);
//            winnerScreen.setVisible(true);

            this.dispose();
        });
        add(button);
    }

}
