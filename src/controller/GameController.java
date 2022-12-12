package controller;

import chessComponent.SquareComponent;
import view.ChessGameFrame;
import view.Chessboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.*;

/**
 * 这个类主要完成由窗体上组件触发的动作。
 * 例如点击button等
 * ChessGameFrame中组件调用本类的对象，在本类中的方法里完成逻辑运算，将运算的结果传递至chessboard中绘制
 */
public class GameController {
    private Chessboard chessboard;

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    public Player player1 = new Player();
    public Player player2 = new Player();

    public List<String> loadGameFromFile(String path) {
        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            for(int i=0;i<chessData.size();i++) {
                List<String> line = new ArrayList<>();
                String s= chessData.get(i);
                s=s.replace(" ","");
                s=s.replace("[","");
                s=s.replace("]","");
                System.out.println(s);
                String[] data = s.split(",");
                int cnt=0;
                for(int m=0;m<8;m++) {
                    StringBuilder sb = new StringBuilder();
                    for(int n=0;n<4;n++) {
                        sb.append(data[cnt]).append(",");
                        cnt++;
                    }
                    sb.setLength(sb.length()-1);
                    line.add(sb.toString());
                }
                line.add(data[32]);
                line.add(data[33]);
                line.add(data[34]);
                line.add(data[35]);
                chessboard.loadGame(line);
                chessboard.stack.push(line);
//                    List<String> chessDa = new ArrayList<>();
//                    n = i - 1;
//                    for (int j = m; j <= n; j++) {
//                        chessDa.add(chessData.get(j));
//                    }
//                    chessDa.add("change");
//                    m = i + 1;
//                    chessboard.loadGame(chessDa);
//                    chessboard.stack.push(chessDa);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> convertToList() {
        List<String> temp = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        chessboard.stack.push(convertToList(chessboard));
        Stack<List<String>> stack;
        stack = stackCopy(chessboard.stack);
        while(!stack.empty()) {
            List<String> list = stack.pop();
            temp.add(list.toString());
        }
        for(int i=temp.size()-1;i>=0;i--) {
            lines.add(temp.get(i));
        }
        return lines;
    }

    public List<String> saveGameToFile(String path) {
        try {
            Files.write(Path.of(path), this.convertToList(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> convertToList(Chessboard chessboard) {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (SquareComponent[] chessLine : chessboard.getChessComponents()) {
            sb.setLength(0);
            for (SquareComponent chess : chessLine) {
                sb.append(chess.getCode()).append(chess.getReversal()).append(",");
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString());
        }

        if (chessboard.getCurrentColor() == ChessColor.RED) {
            lines.add("0");
        } else {
            lines.add("1");
        }

        lines.add(Integer.toString(Player.scoreRed));
        lines.add(Integer.toString(Player.scoreBlack));
        lines.add(Integer.toString(ClickController.cnt));

        return lines;
    }

    public Stack<List<String>> stackCopy(Stack<List<String>> stack) {
        Stack<List<String>> temp = new Stack<>();
        Stack<List<String>> copy = new Stack<>();
        while(!stack.empty()) {
            List<String> list = stack.pop();
            temp.push(list);
        }
        while(!temp.empty()) {
            List<String> list = temp.pop();
            stack.push(list);
            copy.push(list);
        }
        return copy;
    }
}

