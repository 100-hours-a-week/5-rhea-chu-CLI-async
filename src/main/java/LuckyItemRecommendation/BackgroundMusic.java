package LuckyItemRecommendation;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic implements Runnable {
    @Override
    public void run() {
        File bgm = new File("bgSound.wav");
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;

        try {
            stream = AudioSystem.getAudioInputStream(bgm);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("지원되지 않는 오디오 파일입니다.");
            throw new UnsupportedOperationException(e);
        } catch (LineUnavailableException e) {
            System.err.println("오디오 라인이 사용 불가능합니다.");
            e.fillInStackTrace();
        } catch (IOException e) {
            System.err.println("오디오 파일 재생 중 오류가 발생했습니다.");
            e.fillInStackTrace();
        }
    }

}
