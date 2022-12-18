package view;

import UI.AudioPlayer;
import controller.GameController;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

import Socket.*;

public class StartScreen extends JFrame {

    private final int WIDTH; //开始界面宽度

    private final int HEIGHT; //开始界面高度

    public JLabel StartLabel = new JLabel();


    public StartScreen(int width, int height) throws IOException {
        WIDTH = width;
        HEIGHT = height;
        setTitle("Start Screen");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addlabel();
        addStartButton();
//        addRuleButton();
    }

    private void addStartButton() {
        JButton button = new JButton("Start the game.");
        button.setLocation(WIDTH / 2 - 120, HEIGHT / 2 +260);
        button.setSize(240, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon icon = new ImageIcon("src/assets/类三/ipad_start-btn@2x.png");
        Image temp = icon.getImage().getScaledInstance(310,120,icon.getImage().SCALE_AREA_AVERAGING);
//        button.setOpaque(false);
        icon = new ImageIcon(temp);
        button.setIcon(icon);
//        addlabel();
//        ImageIcon bg = new ImageIcon("ipad_start-btn@2x.png");
//        Image image = bg.getImage();
//        Image smallerimage = image.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon smallericon = new ImageIcon(smallerimage);
//        button.setIcon(smallericon);
//        button.setContentAreaFilled(false);
        button.addActionListener(e -> {
            ChessGameFrame chessGameFrame = new ChessGameFrame(800, 800);
            chessGameFrame.setVisible(true);
            AudioPlayer.playSound("src/assets/类二/StartGame.wav");
            this.dispose();
        });
        add(button);
    }
    public void addRuleButton(){
        JButton jButton = new JButton();
        jButton.setLocation(WIDTH / 2+120, HEIGHT / 2 +260);
        jButton.setSize(240,60);
        ImageIcon icon = new ImageIcon("src/assets/类三/ipad_tishi-btn@2x.png");
        Image temp = icon.getImage().getScaledInstance(310,80,icon.getImage().SCALE_AREA_AVERAGING);
        icon = new ImageIcon(temp);
        jButton.setIcon(icon);
        add(jButton);
    }

    private void addlabel() {
        ImageIcon bg = new ImageIcon("src/assets/类三/Default.png");
        Image image = bg.getImage();
        Image smallerimage = image.getScaledInstance(720, 720, Image.SCALE_SMOOTH);
        ImageIcon smallericon = new ImageIcon(smallerimage);
        JLabel label = new JLabel(smallericon);
        label.setText("yi");
        label.setVisible(true);
        add(label);
        label.setSize(720, 720);
    }
    void addlabel(String filename, JLabel label,int width,int height) {
        ImageIcon imageIcon = new ImageIcon(filename);
        Image temp = imageIcon.getImage().getScaledInstance(width, height, imageIcon.getImage().SCALE_DEFAULT);
        imageIcon = new ImageIcon(temp);
        label.setIcon(imageIcon);
        label.setVisible(true);
        add(label);
        label.setSize(width, height);
    }





}
