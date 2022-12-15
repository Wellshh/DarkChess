import UI.AudioPlayer;
import view.ChessGameFrame;
import view.StartScreen;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartScreen startScreen = null;
            try {
                startScreen = new StartScreen(720, 720);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            startScreen.setVisible(true);
//            AudioPlayer audioPlayer = new AudioPlayer();
//            audioPlayer.play("C:\\Users\\Wells\\Music\\Ed Sheeran - Tides.wav");
        });
//        AudioPlayer audioPlayer = new AudioPlayer();
//        audioPlayer.play("src/view/Ed Sheeran - Tides.wav");
    }
}
