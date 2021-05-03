package me.skiincraft.pong.engine.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public interface GameSound {

    AudioInputStream getAudioInputStream();
    String getName();
    Clip getClip();
    void play();
    void loop();
    void stop();
}
