package me.skiincraft.pong.engine.input;

@FunctionalInterface
public interface GameKey {
    void onKeyPressed(int key);
}
