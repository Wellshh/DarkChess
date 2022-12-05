import view.ChessGameFrame;
import view.StartScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartScreen startScreen = new StartScreen(720, 720); //jygg!!!!
            startScreen.setVisible(true); //jygg!!!!!
        });
    }
}
