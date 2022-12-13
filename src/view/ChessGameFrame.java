package view;

import chessComponent.FakeChessComponent;
import chessComponent.SquareComponent;
import controller.AIController;
import controller.ClickController;
import controller.GameController;
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
    JLabel cheatLabel = new JLabel(), AILabel = new JLabel();
    Chessboard chessboard;
    public static  JPanel redPanel, blackPanel;

    public JPanel getRedPanel() {
        return redPanel;
    }

    private static int checkCheat = 0, checkAI = 0;



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

        addAIButton();

        addChessboard();

        addLabel();
        addStartButton();
        addLoadButton();
        addRestartButton();
        addCheatButton();
        addCheatingLabel();
        addSaveButton();
        addPanel();
        addRegretButton();
    }

    /**
     * 在游戏窗体中添加装棋子的面板
     */
    private void addPanel() {
        redPanel = new JPanel(new FlowLayout());
        blackPanel = new JPanel(new FlowLayout());
        redPanel.setBorder(BorderFactory.createBevelBorder(100,Color.RED,Color.BLUE));//给面板添加边框
        blackPanel.setOpaque(true);
        redPanel.setOpaque(true);
        blackPanel.setSize(230, 900);
        redPanel.setSize(240, 900);
        blackPanel.setBorder(BorderFactory.createBevelBorder(100,Color.RED,Color.BLUE));
        blackPanel.setLocation(WIDTH *7 / 10, HEIGHT * 1 / 7);
        redPanel.setLocation(2, HEIGHT/7);
        getContentPane().add(redPanel);
        getContentPane().add(blackPanel);
    }

    /**
     * 在游戏窗体中添加棋盘
     */
    private void addChessboard() {
        chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        chessboard.setOpaque(true);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 4 + 40, HEIGHT / 10+40);
        add(chessboard);
    }

    /**
     * 在游戏窗体中添加标签
     */
    private void addLabel() {
        labelTurn = new JLabel();
        labelTurn.setLocation(WIDTH / 100000, HEIGHT / 100000);
        labelTurn.setSize(1000, 60);
        labelTurn.setFont(new Font("Rockwell", Font.ITALIC, 30));
        add(labelTurn);

        labelScoreRed = new JLabel("RED's score: 0");
        labelScoreRed.setLocation(WIDTH / 10000 + 50, HEIGHT / 50 + 50);
        labelScoreRed.setSize(200, 60);
        labelScoreRed.setFont(new Font("Rockwell", Font.ITALIC, 20));
        //labelScoreRed.setOpaque(false);
        add(labelScoreRed);

        labelScoreBlack = new JLabel("BLACK's score: 0");
        labelScoreBlack.setLocation(WIDTH - 200, HEIGHT / 50 + 57);
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

    public static int getCheckAI() {
        return checkAI;
    }

    /**
     * 加入AI按钮
     */

    private void addAIButton() {
        JButton button = new JButton("AI");
        button.setLocation(320, 50);
        button.setSize(120, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.addActionListener(e -> {
            if (checkAI == 0) {
                System.out.println("Enter AI mode");
                AILabel.setText("AI Mode on");
                String st = JOptionPane.showInputDialog("Enter the level"); //输入难度等级
                checkAI = Integer.parseInt(st);
            } else {
                AILabel.setText("");
                checkAI = 0;
            }
        });
        add(button);
    }

    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示start
     */

    private void addStartButton() {
        JButton button = new JButton("START");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Welcome, chess player!"));
        button.setLocation(WIDTH / 1000 + 200, HEIGHT / 10000);
        button.setSize(120, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(button);
    }

    /**
     * 加入重新开始按钮
     */
    private void addRestartButton() {
        JButton button = new JButton("RESTART");
        button.setLocation(WIDTH / 10000 + 320, HEIGHT / 100000);
        button.setSize(120, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.addActionListener(e -> {
            this.dispose();
            ChessGameFrame chessGameFrame = new ChessGameFrame(800, 800);
            chessGameFrame.setVisible(true);
            Player.changeScoreBlack(-Player.scoreBlack);
            Player.changeScoreRed(-Player.scoreRed);
            ClickController.cnt = 0;
            labelTurn.setText("");
//            remove(chessboard);
//            repaint();
//            Player.changeScoreBlack(-Player.scoreBlack);
//            Player.changeScoreRed(-Player.scoreRed);
//            addChessboard();
        });
        add(button);
    }

    /**
     * 加入从外部导入按钮
     */
    private void addLoadButton() {
        JButton button = new JButton("LOAD");
        button.setLocation(WIDTH / 10000 + 440, HEIGHT / 10000);
        button.setSize(120, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        //button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            gameController.loadGameFromFile(path);
            chessboard.stack.pop();
        });
    }

    /**
     * 保存
     */
    private void addSaveButton() {
        JButton button = new JButton("SAVE");
        button.setLocation(WIDTH / 10000 + 560, HEIGHT / 10000);
        button.setSize(120, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click save");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            gameController.saveGameToFile("src/ChessboardData.txt");
        });
    }

    /**
     * 加入作弊模式按钮
     */
    public static int getCheckCheat() {
        return checkCheat;
    }

    private void addCheatButton() {
        JButton button = new JButton("CHEAT");
        button.setLocation(WIDTH / 10000 + 680, HEIGHT / 10000);
        button.setSize(120, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        //button.setBackground(Color.LIGHT_GRAY);
        add(button);


        button.addActionListener(e -> {
            if (checkCheat == 0) {
                System.out.println("Enter cheating mode");
                cheatLabel.setText("Cheating Mode on");
                checkCheat = 1;
            } else {
                System.out.println("Exit cheating mode");
                cheatLabel.setText("");
                checkCheat = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (chessboard.getChessComponents()[i][j].getCheckCheat() == 1) {
                            chessboard.getChessComponents()[i][j].setCheckCheat(0);
                            chessboard.getChessComponents()[i][j].setReversal(false);
                            chessboard.getChessComponents()[i][j].setReversal("0");
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
            }
        });
    }

    private void addRegretButton() {
        JButton button = new JButton("REGRET");
        button.setLocation(WIDTH / 10000 + 200, HEIGHT / 100000 + 50);
        button.setSize(120, 45);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(button);
        button.addActionListener(e -> {
            repaint();
            List<String> list;
            list = chessboard.stack.pop();
            chessboard.loadGame(list);
            int scoreRed = Integer.parseInt(list.get(9));
            Player.scoreRed = scoreRed;//因为scoreRed是静态变量，需要重置其值，不然会出现积分异常的问题
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
        cheatLabel.setLocation(WIDTH * 8 / 10 + 40, HEIGHT * 8 / 10 + 120);
        cheatLabel.setSize(180, 40);
        cheatLabel.setForeground(Color.RED);
        cheatLabel.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(cheatLabel);
    }

}
