package me.skiincraft.pong.engine.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DefaultGameSound implements GameSound {

    private final AudioInputStream audioInputStream;
    private final String name;
    private Clip clip;

    public DefaultGameSound(String name, InputStream stream) throws IOException, UnsupportedAudioFileException {
        this.audioInputStream = AudioSystem.getAudioInputStream(stream);
        this.name = name;
        try {
            this.clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
            this.clip.open(audioInputStream);
        } catch (LineUnavailableException ignored) {}
    }

    public DefaultGameSound(String name, File file) throws IOException, UnsupportedAudioFileException {
        this(name, new FileInputStream(file));
    }

    public DefaultGameSound(File file) throws IOException, UnsupportedAudioFileException {
        this(file.getName(), file);
    }

    @Override
    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Clip getClip() {
        return clip;
    }

    @Override
    public void play() {
        stop();
        clip.start();
    }

    @Override
    public void loop() {
        stop();
        clip.loop(-1);
        clip.start();
    }

    @Override
    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }
}
