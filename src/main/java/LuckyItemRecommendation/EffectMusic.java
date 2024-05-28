package LuckyItemRecommendation;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;

public class EffectMusic implements Runnable {
    private AdvancedPlayer player;
    private Thread playerThread;

    @Override
    public void run() {
        try {
            EffectSoundPlay();
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void EffectSoundPlay() throws IOException, JavaLayerException {
        FileInputStream fileInputStream = new FileInputStream("effectSound.mp3");
        player = new AdvancedPlayer(fileInputStream);
        playerThread = new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        });
        playerThread.start();
    }

    public void StopEffectSound() {
        if (player != null) {
            player.close();
        }
    }
}
