package me.skiincraft.pong.engine;

import me.skiincraft.pong.engine.entity.GameEntity2D;
import me.skiincraft.pong.engine.input.GameKey;
import me.skiincraft.pong.engine.input.GameKeyAdapter;
import me.skiincraft.pong.engine.sound.GameSound;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameImplementation {

    private final List<GameSound> gameSounds = new ArrayList<>();
    private final GameKeyAdapter keyAdapter;
    protected int updateDelay;

    private GameAdapter gameAdapter;

    public GameImplementation(int updateDelay) {
        this.keyAdapter = new GameKeyAdapter(updateDelay*2);
        this.updateDelay = updateDelay;
    }

    public GameAdapter getGameAdapter() {
        if (gameAdapter == null)
            throw new RuntimeException("This implementation has not started with GameLauncher!");

        return gameAdapter;
    }

    public boolean isLaunched() {
        try {
            getGameAdapter();
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    public int getWidth(){
        return getGameAdapter().getWidth();
    }

    public int getHeight(){
        return getGameAdapter().getHeight();
    }

    public void addGameKey(GameKey gameKey){
        this.keyAdapter.addGameKey(gameKey);
    }

    public void removeGameKey(GameKey gameKey){
        this.keyAdapter.removeGameKey(gameKey);
    }

    public void clearGameKeys(){
        this.keyAdapter.clear();
    }

    public GameKeyAdapter getKeyAdapter() {
        return keyAdapter;
    }

    public List<GameSound> getGameSounds(){
        return gameSounds;
    }

    public GameSound getGameSound(String name){
        return gameSounds.stream().filter(gameSound -> gameSound.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void addGameSound(GameSound sound){
        gameSounds.removeIf(gs -> gs.getName().equalsIgnoreCase(sound.getName()));
        gameSounds.add(sound);
    }

    public void preload(){}

    public abstract void draw(Graphics2D graphics2D);

}
