package me.skiincraft.pong.engine.input;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameKeyAdapter extends KeyAdapter {

    private final HashSet<Integer> keys = new HashSet<>();
    private final List<GameKey> gameKeys = new ArrayList<>();
    private final Timer timer;

    public GameKeyAdapter(int delay) {
        this.timer = new Timer(delay, (args) -> {
            if (keys.isEmpty()) {
                return;
            }
            for (int key : keys) {
                for (GameKey gameKey : gameKeys)
                    gameKey.onKeyPressed(key);
            }
        });
    }

    public HashSet<Integer> getKeys() {
        return keys;
    }

    public List<GameKey> getGameKeys() {
        return gameKeys;
    }

    public void addGameKey(GameKey gameKey){
        gameKeys.add(gameKey);
    }

    public void removeGameKey(GameKey gameKey){
        gameKeys.removeIf(gk -> gk == gameKey);
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
    }

    public void clear() {
        gameKeys.clear();
    }

    public void clearKeysPressed(){
        keys.clear();
    }
}
