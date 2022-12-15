package view;

import controller.GameController;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class StartScreen extends JFrame {

    private final int WIDTH; //开始界面宽度

    private final int HEIGHT; //开始界面高度


    public StartScreen(int width, int height) throws IOException {
        WIDTH = width;
        HEIGHT = height;
        setTitle("Start Screen");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addStartButton();
        addlabel();

    }

    private void addStartButton() {
        JButton button = new JButton("Start the game.");
        button.setLocation(WIDTH / 2 - 120, HEIGHT / 2 - 30);
        button.setSize(240, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.addActionListener(e -> {
            ChessGameFrame chessGameFrame = new ChessGameFrame(800, 800);
            chessGameFrame.setVisible(true);

//            WinnerScreen winnerScreen = new WinnerScreen(360, 360);
//            winnerScreen.setVisible(true);

            this.dispose();
        });
        add(button);
    }

    private void addlabel() {
        ImageIcon bg = new ImageIcon("C:\\Users\\Wells\\IdeaProjects\\DarkChess\\src\\新建文件夹\\splash_big_logo.png");
        Image image = bg.getImage();
        Image smallerimage = image.getScaledInstance(720, 720, Image.SCALE_SMOOTH);
        ImageIcon smallericon = new ImageIcon(smallerimage);
        JLabel label = new JLabel(smallericon);
        label.setText("yi");
        label.setVisible(true);
        add(label);
        label.setSize(720, 720);
    }


//    public class MyCanvas extends Canvas {
//        Image image;
//
//        MyCanvas(Image image) {
//            this.image = image;
//        }
//
//        //重写paint方法
//        @Override
//        public void paint(Graphics g) {
//            super.paint(g);
//            //调用Graphics画笔的drawImage()方法开始画图。
//            g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
//        }
//
//        @Override
//        public Dimension getPreferredSize() {
//            return new Dimension(WIDTH, HEIGHT);
//        }
//
//    }
}
