package controller;

import chessComponent.SquareComponent;
import view.Chessboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
            chessboard.loadGame(chessData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> convertToList() {
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
}

