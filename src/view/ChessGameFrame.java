package view;

import controller.ClickController;
import controller.GameController;
import model.CheatingMode;
import model.ChessColor;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这个类表示游戏窗体，窗体上包含：
 * 1 Chessboard: 棋盘
 * 2 JLabel:  标签
 * 3 JButton： 按钮
 */
public class ChessGameFrame extends JFrame {
    private final int WIDTH; //显示框宽度
    private final int HEIGHT; //显示框高度
    public final int CHESSBOARD_SIZE; //棋盘高度
    private GameController gameController;
    private static JLabel labelTurn, labelTime, labelScoreRed, labelScoreBlack;

    private CheatingMode cheatingMode;
    JLabel cheatLabel = new JLabel();

    //private static JPanel jp = new JPanel();

    Chessboard chessboard;

    public ChessGameFrame(int width, int height) {
        setTitle("Dark Chess"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        //addBackground();

        addChessboard();

        addLabel();
        addStartButton();
        addLoadButton();
        addRestartButton();
        addCheatButton();
        addCheatingLabel();
        addSaveButton();
        addRegretButton();

//        jp.setLocation(0,0);
//        jp.setSize(720, 720);
//        jp.setLayout(null);
//        add(jp);
    }


    /**
     * 在游戏窗体中添加棋盘
     */
    private void addChessboard() {
        chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        chessboard.setOpaque(false);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 10, HEIGHT / 10);
        add(chessboard);
    }

    /**
     * 在游戏窗体中添加标签
     */
    private void addLabel() {
        labelTurn = new JLabel();
        labelTurn.setLocation(WIDTH * 3 / 5, HEIGHT / 10);
        labelTurn.setSize(200, 60);
        labelTurn.setFont(new Font("Rockwell", Font.ITALIC, 20));
        add(labelTurn);

        labelScoreRed = new JLabel("RED's score: 0");
        labelScoreRed.setLocation(WIDTH * 3 / 5,HEIGHT / 10 + 100);
        labelScoreRed.setSize(200, 60);
        labelScoreRed.setFont(new Font("Rockwell", Font.ITALIC, 20));
        //labelScoreRed.setOpaque(false);
        add(labelScoreRed);

        labelScoreBlack = new JLabel("BLACK's score: 0");
        labelScoreBlack.setLocation(WIDTH * 3 / 5,HEIGHT / 10 + 150);
        labelScoreBlack.setSize(200, 60);
        labelScoreBlack.setFont(new Font("Rockwell", Font.ITALIC, 20));
        //labelScoreBlack.setOpaque(false);
        add(labelScoreBlack);
    }

    public static JLabel getLabelTurn() {
        return labelTurn;
    }

    public static JLabel getLabelScoreRed() {
        return labelScoreRed;
    }

    public static JLabel getLabelScoreBlack() {
        return labelScoreBlack;
    }

    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示start
     */

    private void addStartButton() {
        JButton button = new JButton("Start the game.");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Welcome, chess player!"));
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 240);
        button.setSize(180, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(button);
    }

    /**
     * 加入重新开始按钮
     */
    private void addRestartButton() {
        JButton button = new JButton("Restart");
        button.setLocation(WIDTH * 3 / 5, HEIGHT /10 + 300);
        button.setSize(180, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.addActionListener(e -> {
            remove(chessboard);
            repaint();
            Player.changeScoreBlack( -Player.scoreBlack );
            Player.changeScoreRed( -Player.scoreRed);
            labelTurn.setText("");
            ClickController.cnt = 0;
            addChessboard();
        });
        add(button);
    }

    /**
     * 加入从外部导入按钮
     */
    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 360);
        button.setSize(180, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        //button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            gameController.loadGameFromFile(path);
        });
    }

    /**
     * 保存
     */
    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(WIDTH*3/5,HEIGHT/10 + 420);
        button.setSize(180,45);
        button.setFont(new Font("Rockwell",Font.BOLD,15));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click save");
            String path=JOptionPane.showInputDialog(this,"Input Path here");
            gameController.saveGameToFile("src/ChessboardData.txt");
        });
    }

    /**
     * 加入作弊模式按钮
     */
    private static int checkCheat = 0;

    public static int getCheckCheat() {
        return checkCheat;
    }

    private void addCheatButton() {
        JButton button = new JButton("Cheating Mode");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 480);
        button.setSize(180, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        //button.setBackground(Color.LIGHT_GRAY);
        add(button);


        button.addActionListener(e -> {
            if(checkCheat == 0){
                System.out.println("Enter cheating mode");
                cheatLabel.setText("Cheating Mode on");
                checkCheat = 1;
            }
            else {
                System.out.println("Exit cheating mode");
                cheatLabel.setText("");
                checkCheat = 0;
                for(int i=0;i<8;i++){
                    for(int j=0;j<4;j++){
                        if(chessboard.getChessComponents()[i][j].getCheckCheat() == 1){
                            chessboard.getChessComponents()[i][j].setCheckCheat(0);
                            chessboard.getChessComponents()[i][j].setReversal(false);
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
            }
        });
    }
    private void addRegretButton(){
        JButton button = new JButton("Regret");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 540);
        button.setSize(180,45);
        button.setFont(new Font("Rockwell",Font.BOLD,15));
        add(button);
        button.addActionListener(e -> {
            repaint();
            List<String> list = new ArrayList<>();
            list = chessboard.stack.pop();
            chessboard.loadGame(list);
            int scoreRed = Integer.parseInt(list.get(9));
            Player.scoreRed = scoreRed;
            labelScoreRed.setText(String.format("RED's score: %d", scoreRed));

            int scoreBlack = Integer.parseInt(list.get(10));
            Player.scoreBlack = scoreBlack;
            labelScoreBlack.setText(String.format("BLACK's score: %d", scoreBlack));

            ClickController.cnt = Integer.parseInt(list.get(11));



        });
    }

    /**
     * 加入 进入作弊模式 标签提示
     */
    public void addCheatingLabel() {
        cheatLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 200);
        cheatLabel.setSize(180,40);
        cheatLabel.setForeground(Color.RED);
        cheatLabel.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(cheatLabel);
    }


//    public void addBackground() {
//        ImageIcon img = new ImageIcon("D:/JAVA product/Final Project/img.jpg");
//        JLabel background = new JLabel(img);
//        background.setBounds(0,0,720,720);
//        JPanel jp1 = new JPanel();
//        jp1.add(background);
//        jp1.setLocation(0,0);
//        jp1.setSize(720,720);
//        jp1.setLayout(null);
//        jp1.setOpaque(false);
//        add(jp1);
//    }

}
