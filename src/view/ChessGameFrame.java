package view;

//import Socket.Client;
import UI.AudioPlayer;
import chessComponent.*;
import controller.AIController;
import controller.ClickController;
import controller.GameController;
import model.ChessColor;
import model.Player;
//import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    public static JPanel redPanel, blackPanel;
    private static int checkCheat = 0, checkAI = 0;
    public AdvisorChessComponent REDAdvisorChessComponent;
    public AdvisorChessComponent BLACKAdvisorChessComponent;
    public CannonChessComponent REDCannonChessComponent;
    public CannonChessComponent BLACKCannonCHessComponent;
    public ChariotChessComponent REDChariotChessComponent;
    public ChariotChessComponent BLACKChariotChessComponent;
    public GeneralChessComponent REDGeneralChessComponent;
    public GeneralChessComponent BLACKGeneralChessComponent;
    public HorseChessComponent REDHorseChessComponent;
    public HorseChessComponent BLACKHorseChessComponent;
    public MinisterChessComponent REDMinisterChessComponent;
    public MinisterChessComponent BLACKMinisterChessComponent;
    public SoldierChessComponent REDSoldierChessComponent;
    public SoldierChessComponent BLACKSoldierChessComponent;
    public static JNumber 红士, 黑士, 红炮, 黑炮, 红车, 黑车, 红帅, 黑帅, 红马, 黑马, 红相, 黑相, 红兵, 黑兵;
    public JLabel RegretLabel = new JLabel();
    public JLabel BgLabel = new JLabel();
    public static int BgCount = 0;


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
//        addPanel();
        addRegretButton();
        addREDAdvisorChessComponent();
        addBLACKAdvisorChessComponent();
        addREDCannonChessComponent();
        addBLACKCannonChessComponent();
        addREDChariotChessComponent();
        addBLACKChariotChessComponent();
        addREDGeneralChessComponent();
        addBLACKGeneralChessComponent();
        addREDHorseChessComponent();
        addBLACKHorseChessComponent();
        addREDMinisterChessComponent();
        addBLACKMinisterChessComponent();
        addREDSoldierChessComponent();
        addBLACKSoldierChessComponent();
        addJNumber();
        addChangeSkinButton();
        addMusicButton();
        addRevisionButton();
        addlabel("src/assets/0cf21f8122dc4d5b4697deed6b70df4f.jpeg",BgLabel);

//        new Client(chessboard);
//        JSONObject info = new JSONObject();
//        info.put("op",0);
//        System.out.println(GameController.convertToList(chessboard));
//        info.put("ini",GameController.convertToList(chessboard));
//        Client.sendInfo(info);
    }

    /**
     * 在游戏窗体中添加装棋子的面板
     */
    private void addPanel() {
        redPanel = new JPanel(new FlowLayout());
        blackPanel = new JPanel(new FlowLayout());
        redPanel.setBorder(BorderFactory.createBevelBorder(100, Color.RED, Color.BLUE));//给面板添加边框
        blackPanel.setOpaque(true);
        redPanel.setOpaque(true);
        blackPanel.setSize(230, 900);
        redPanel.setSize(240, 900);
        blackPanel.setBorder(BorderFactory.createBevelBorder(100, Color.RED, Color.BLUE));
        blackPanel.setLocation(WIDTH * 7 / 10, HEIGHT * 1 / 7);
        redPanel.setLocation(2, HEIGHT / 7);
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
        chessboard.setLocation(HEIGHT / 4 + 25, HEIGHT / 10 + 40);
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
        button.setContentAreaFilled(false);
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
        JButton button = new JButton("RULE");
        String message = "规则如下：\n" + "\n" + "首先，把棋子打乱反面向上摆放在4*8的棋盘中\n"+"然后，两个玩家摇骰子看谁大谁就是先手（先手就是第一个翻棋的人）；接着，翻出的棋子可以互吃；最后，棋子被吃完的那方就是失败了。\n"
                +
                "棋子等级：兵（卒）是等级最低的棋子，只能吃对方的将（帅）；\n"+
                "炮可以吃任何棋子，但中间必须隔一个棋子；车可以吃除象（相）、士（仕）、将（帅）以外的棋子；马可以吃兵（卒）、炮；\n"
                +"象（相）可以吃除士（仕）、将（帅）以外的棋子；士（仕）可以吃除将（帅）以外的棋子；将（帅）是等级最高的棋子，可以吃任何棋子，但不可以吃兵（卒）。";
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, message));
        button.setLocation(WIDTH / 1000 + 200, HEIGHT / 10000);
        button.setSize(120, 45);
        ImageIcon icon = new ImageIcon("src/assets/类三/ipad_tishi-btn@2x.png");
        Image temp = icon.getImage().getScaledInstance(button.getWidth()+40,button.getHeight()+30,icon.getImage().SCALE_AREA_AVERAGING);
        icon = new ImageIcon(temp);
//        button.setIcon(icon);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.setContentAreaFilled(false);
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
        ImageIcon icon = new ImageIcon("src/assets/类三/ipad_restart-btn@2x.png");
        Image temp = icon.getImage().getScaledInstance(button.getWidth()+40,button.getHeight()+30,icon.getImage().SCALE_AREA_AVERAGING);
        icon = new ImageIcon(temp);
//        button.setIcon(icon);
        button.setContentAreaFilled(false);
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
        ImageIcon icon = new ImageIcon("src/assets/类三/ipad_load-new-fen-btn@2x.png");
        Image temp = icon.getImage().getScaledInstance(button.getWidth()+40,button.getHeight()+30,icon.getImage().SCALE_AREA_AVERAGING);
        icon = new ImageIcon(temp);
//        button.setIcon(icon);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.setContentAreaFilled(false);
        //button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            try{
            gameController.loadGameFromFile(path);}
            catch(Exception k){
                JOptionPane.showMessageDialog(this,"101");}
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
        button.setContentAreaFilled(false);

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
        button.setContentAreaFilled(false);


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
        button.setContentAreaFilled(false);
        button.addActionListener(e -> {
            repaint();
            List<String> list;
            list = chessboard.stack.pop();
            chessboard.loadGame(list);
            int scoreRed = Integer.parseInt(list.get(9));
//            Player.scoreRed = scoreRed;//因为scoreRed是静态变量，需要重置其值，不然会出现积分异常的问题
//            labelScoreRed.setText(String.format("RED's score: %d", scoreRed));
//            int scoreBlack = Integer.parseInt(list.get(10));
//            Player.scoreBlack = scoreBlack;
//            labelScoreBlack.setText(String.format("BLACK's score: %d", scoreBlack));
//            ClickController.cnt = Integer.parseInt(list.get(11));
            repaint();
        });
    }
    private void addChangeSkinButton(){
        JButton button = new JButton("ChangSkin");
        button.setLocation(440,50);
        button.setSize(120,45);
        button.setFont(new Font("Rockwell",Font.BOLD,15));
        add(button);
        button.setContentAreaFilled(false);
        button.addActionListener(e -> {
            Random r = new Random();
            Random t = new Random();
            Random s = new Random();
            ChessComponent.chessSkin = r.nextInt(3);
            SquareComponent.ChessboardSkin = t.nextInt(3);
            BgCount = s.nextInt(3);
            if(BgCount ==0){
                addlabel("src/assets/0cf21f8122dc4d5b4697deed6b70df4f.jpeg",BgLabel);}
            else if(BgCount==1){addlabel("src/assets/b62a82c49d08c91d4426e82ef9206d96.jpeg",BgLabel);}
            else {addlabel("src/assets/72e7b8b668635736c7c35acb95eea199.jpeg",BgLabel);}
           repaint();
        });

    }
    public void addMusicButton(){
        JButton MusicButton = new JButton();
        MusicButton.setLocation(650,500);
        MusicButton.setSize(60,60);
        ImageIcon icon = new ImageIcon(ChessGameFrame.class.getResource("/assets/类三/music.png"));
        Image temp = icon.getImage().getScaledInstance(75,75,icon.getImage().SCALE_AREA_AVERAGING);
//        button.setOpaque(false);
        icon = new ImageIcon(temp);
        MusicButton.setIcon(icon);
        add(MusicButton);
        MusicButton.addActionListener(e -> {
            System.out.println("shit");
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(100); //1000 milliseconds
                } catch (InterruptedException k) {
                    k.printStackTrace();
                }
                AudioPlayer.playBgm("src/assets/类二/BackMusic.wav");
            });
            t.start();
        });

    }
    public void addRevisionButton(){
        JButton button = new JButton("REVISION");
        button.setLocation(560,50);
        button.setFont(new Font("Rockwell",Font.BOLD,15));
        button.setSize(120,45);
        button.setContentAreaFilled(false);
        add(button);
        button.addActionListener(e -> {
            List<String> currentStatus = GameController.convertToList(chessboard);
            chessboard.reverseStack.push(currentStatus);//需要多记录一次当前棋盘的状态，不然会和悔棋一样复盘到上一次
            while(!chessboard.stack.isEmpty()){
                List<String> list = chessboard.stack.pop();
                chessboard.reverseStack.push(list);
            }
//            while(chessboard.stack.isEmpty()==false)
                try {
                    while(!chessboard.reverseStack.isEmpty()){
                        List<String> list;
                        list = chessboard.reverseStack.pop();
                        chessboard.loadGame(list);
                        //System.out.printf("                       %d", chessboard.stack.capacity());
                        Thread.currentThread().sleep(1000);
                    }
//                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
//            }
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

    public void addREDAdvisorChessComponent() {
        REDAdvisorChessComponent = new AdvisorChessComponent(ChessColor.RED);
        REDAdvisorChessComponent.hasGrid = false;
        REDAdvisorChessComponent.setReversal(true);
        REDAdvisorChessComponent.setLocation(0, 110);
        REDAdvisorChessComponent.setSize(30, 30);
        REDAdvisorChessComponent.setVisible(true);
        REDAdvisorChessComponent.setEnabled(false);
        add(REDAdvisorChessComponent);
    }

    public void addBLACKAdvisorChessComponent() {
        BLACKAdvisorChessComponent = new AdvisorChessComponent(ChessColor.BLACK);
        BLACKAdvisorChessComponent.hasGrid = false;
        BLACKAdvisorChessComponent.setReversal(true);
        BLACKAdvisorChessComponent.setLocation(580, 115);
        BLACKAdvisorChessComponent.setSize(30, 30);
        BLACKAdvisorChessComponent.setVisible(true);
        BLACKAdvisorChessComponent.setEnabled(false);
        add(BLACKAdvisorChessComponent);
    }

    public void addREDCannonChessComponent() {
        REDCannonChessComponent = new CannonChessComponent(ChessColor.RED);
        REDCannonChessComponent.hasGrid = false;
        REDCannonChessComponent.setReversal(true);
        REDCannonChessComponent.setLocation(0, 150);
        REDCannonChessComponent.setSize(30, 30);
        REDCannonChessComponent.setVisible(true);
        REDCannonChessComponent.setEnabled(false);
        add(REDCannonChessComponent);
    }

    public void addBLACKCannonChessComponent() {
        BLACKCannonCHessComponent = new CannonChessComponent(ChessColor.BLACK);
        BLACKCannonCHessComponent.hasGrid = false;
        BLACKCannonCHessComponent.setReversal(true);
        BLACKCannonCHessComponent.setLocation(580, 155);
        BLACKCannonCHessComponent.setSize(30, 30);
        BLACKCannonCHessComponent.setVisible(true);
        BLACKCannonCHessComponent.setEnabled(false);
        add(BLACKCannonCHessComponent);
    }

    public void addREDChariotChessComponent() {
        REDChariotChessComponent = new ChariotChessComponent(ChessColor.RED);
        REDChariotChessComponent.hasGrid = false;
        REDChariotChessComponent.setReversal(true);
        REDChariotChessComponent.setLocation(0, 190);
        REDChariotChessComponent.setSize(30, 30);
        REDChariotChessComponent.setVisible(true);
        REDChariotChessComponent.setEnabled(false);
        add(REDChariotChessComponent);
    }

    public void addBLACKChariotChessComponent() {
        BLACKChariotChessComponent = new ChariotChessComponent(ChessColor.BLACK);
        BLACKChariotChessComponent.hasGrid = false;
        BLACKChariotChessComponent.setReversal(true);
        BLACKChariotChessComponent.setLocation(580, 195);
        BLACKChariotChessComponent.setSize(30, 30);
        BLACKChariotChessComponent.setVisible(true);
        BLACKChariotChessComponent.setEnabled(false);
        add(BLACKChariotChessComponent);
    }

    public void addREDGeneralChessComponent() {
        REDGeneralChessComponent = new GeneralChessComponent(ChessColor.RED);
        REDGeneralChessComponent.hasGrid = false;
        REDGeneralChessComponent.setReversal(true);
        REDGeneralChessComponent.setLocation(0, 230);
        REDGeneralChessComponent.setSize(30, 30);
        REDGeneralChessComponent.setVisible(true);
        REDGeneralChessComponent.setEnabled(false);
        add(REDGeneralChessComponent);
    }

    public void addBLACKGeneralChessComponent() {
        BLACKGeneralChessComponent = new GeneralChessComponent(ChessColor.BLACK);
        BLACKGeneralChessComponent.hasGrid = false;
        BLACKGeneralChessComponent.setReversal(true);
        BLACKGeneralChessComponent.setLocation(580, 235);
        BLACKGeneralChessComponent.setSize(30, 30);
        BLACKGeneralChessComponent.setVisible(true);
        BLACKGeneralChessComponent.setEnabled(false);
        add(BLACKGeneralChessComponent);
    }

    public void addREDHorseChessComponent() {
        REDHorseChessComponent = new HorseChessComponent(ChessColor.RED);
        REDHorseChessComponent.hasGrid = false;
        REDHorseChessComponent.setReversal(true);
        REDHorseChessComponent.setLocation(0, 270);
        REDHorseChessComponent.setSize(30, 30);
        REDHorseChessComponent.setVisible(true);
        REDHorseChessComponent.setEnabled(false);
        add(REDHorseChessComponent);
    }

    public void addBLACKHorseChessComponent() {
        BLACKHorseChessComponent = new HorseChessComponent(ChessColor.BLACK);
        BLACKHorseChessComponent.hasGrid = false;
        BLACKHorseChessComponent.setReversal(true);
        BLACKHorseChessComponent.setLocation(580, 275);
        BLACKHorseChessComponent.setSize(30, 30);
        BLACKHorseChessComponent.setVisible(true);
        BLACKHorseChessComponent.setEnabled(false);
        add(BLACKHorseChessComponent);
    }

    public void addREDMinisterChessComponent() {
        REDMinisterChessComponent = new MinisterChessComponent(ChessColor.RED);
        REDMinisterChessComponent.hasGrid = false;
        REDMinisterChessComponent.setReversal(true);
        REDMinisterChessComponent.setLocation(0, 310);
        REDMinisterChessComponent.setSize(30, 30);
        REDMinisterChessComponent.setVisible(true);
        REDMinisterChessComponent.setEnabled(false);
        add(REDMinisterChessComponent);
    }

    public void addBLACKMinisterChessComponent() {
        BLACKMinisterChessComponent = new MinisterChessComponent(ChessColor.BLACK);
        BLACKMinisterChessComponent.hasGrid = false;
        BLACKMinisterChessComponent.setReversal(true);
        BLACKMinisterChessComponent.setLocation(580, 315);
        BLACKMinisterChessComponent.setSize(30, 30);
        BLACKMinisterChessComponent.setVisible(true);
        BLACKMinisterChessComponent.setEnabled(false);
        add(BLACKMinisterChessComponent);
    }

    public void addREDSoldierChessComponent() {
        REDSoldierChessComponent = new SoldierChessComponent(ChessColor.RED);
        REDSoldierChessComponent.hasGrid = false;
        REDSoldierChessComponent.setReversal(true);
        REDSoldierChessComponent.setLocation(0, 350);
        REDSoldierChessComponent.setSize(30, 30);
        REDSoldierChessComponent.setVisible(true);
        REDSoldierChessComponent.setEnabled(false);
        add(REDSoldierChessComponent);
    }

    public void addBLACKSoldierChessComponent() {
        BLACKSoldierChessComponent = new SoldierChessComponent(ChessColor.BLACK);
        BLACKSoldierChessComponent.hasGrid = false;
        BLACKSoldierChessComponent.setReversal(true);
        BLACKSoldierChessComponent.setLocation(580, 355);
        BLACKSoldierChessComponent.setSize(30, 30);
        BLACKSoldierChessComponent.setVisible(true);
        BLACKSoldierChessComponent.setEnabled(false);
        add(BLACKSoldierChessComponent);
    }

    public void addJNumber() {
        红士 = new JNumber();
        红士.setText(String.format("* %d", 红士.num));
        红士.setFont(new Font("宋体", Font.BOLD, 20));
        红士.setForeground(Color.RED);
        红士.setLocation(40, 100);
        红士.setSize(50, 50);
        add(红士);
        黑士 = new JNumber();
        黑士.setText(String.format("* %d", 黑士.num));
        黑士.setFont(new Font("宋体", Font.BOLD, 20));
        黑士.setForeground(Color.BLACK);
        黑士.setLocation(620, 105);
        黑士.setSize(50, 50);
        add(黑士);
        红炮 = new JNumber();
        红炮.setText(String.format("* %d", 红炮.num));
        红炮.setFont(new Font("宋体", Font.BOLD, 20));
        红炮.setForeground(Color.RED);
        红炮.setLocation(40, 140);
        红炮.setSize(50, 50);
        add(红炮);
        黑炮 = new JNumber();
        黑炮.setText(String.format("* %d", 黑炮.num));
        黑炮.setFont(new Font("宋体", Font.BOLD, 20));
        黑炮.setForeground(Color.BLACK);
        黑炮.setLocation(620, 145);
        黑炮.setSize(50, 50);
        add(黑炮);
        红车 = new JNumber();
        红车.setText(String.format("* %d", 红车.num));
        红车.setFont(new Font("宋体", Font.BOLD, 20));
        红车.setForeground(Color.RED);
        红车.setLocation(40, 180);
        红车.setSize(50, 50);
        add(红车);
        黑车 = new JNumber();
        黑车.setText(String.format("* %d", 黑车.num));
        黑车.setFont(new Font("宋体", Font.BOLD, 20));
        黑车.setForeground(Color.BLACK);
        黑车.setLocation(620, 185);
        黑车.setSize(50, 50);
        add(黑车);
        红帅 = new JNumber();
        红帅.setText(String.format("* %d", 红帅.num));
        红帅.setFont(new Font("宋体", Font.BOLD, 20));
        红帅.setForeground(Color.RED);
        红帅.setLocation(40, 220);
        红帅.setSize(50, 50);
        add(红帅);
        黑帅 = new JNumber();
        黑帅.setText(String.format("* %d", 黑帅.num));
        黑帅.setFont(new Font("宋体", Font.BOLD, 20));
        黑帅.setForeground(Color.BLACK);
        黑帅.setLocation(620, 225);
        黑帅.setSize(50, 50);
        add(黑帅);
        红马 = new JNumber();
        红马.setText(String.format("* %d", 红马.num));
        红马.setFont(new Font("宋体", Font.BOLD, 20));
        红马.setForeground(Color.RED);
        红马.setLocation(40, 260);
        红马.setSize(50, 50);
        add(红马);
        黑马 = new JNumber();
        黑马.setText(String.format("* %d", 黑马.num));
        黑马.setFont(new Font("宋体", Font.BOLD, 20));
        黑马.setForeground(Color.BLACK);
        黑马.setLocation(620, 265);
        黑马.setSize(50, 50);
        add(黑马);
        红相 = new JNumber();
        红相.setText(String.format("* %d", 红相.num));
        红相.setFont(new Font("宋体", Font.BOLD, 20));
        红相.setForeground(Color.RED);
        红相.setLocation(40, 300);
        红相.setSize(50, 50);
        add(红相);
        黑相 = new JNumber();
        黑相.setText(String.format("* %d", 黑相.num));
        黑相.setFont(new Font("宋体", Font.BOLD, 20));
        黑相.setForeground(Color.BLACK);
        黑相.setLocation(620, 305);
        黑相.setSize(50, 50);
        add(黑相);
        红兵 = new JNumber();
        红兵.setText(String.format("* %d", 红兵.num));
        红兵.setFont(new Font("宋体", Font.BOLD, 20));
        红兵.setForeground(Color.RED);
        红兵.setLocation(40, 340);
        红兵.setSize(50, 50);
        add(红兵);
        黑兵 = new JNumber();
        黑兵.setText(String.format("* %d", 黑兵.num));
        黑兵.setFont(new Font("宋体", Font.BOLD, 20));
        黑兵.setForeground(Color.BLACK);
        黑兵.setLocation(620, 345);
        黑兵.setSize(50, 50);
        add(黑兵);
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
    void addlabel(String filename, JLabel label) {
        ImageIcon bg = new ImageIcon(filename);
        ImageIcon imageIcon = new ImageIcon(filename);
        Image temp = imageIcon.getImage().getScaledInstance(WIDTH, HEIGHT, imageIcon.getImage().SCALE_DEFAULT);
        imageIcon = new ImageIcon(temp);
        label.setIcon(imageIcon);
        label.setVisible(true);
        add(label);
        label.setSize(this.getWidth(), this.getHeight());
    }
}

