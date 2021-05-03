package me.skiincraft.pong;

import me.skiincraft.pong.engine.GameLauncher;

public class PongLauncher {

    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher("Pong Game", new PongGame(10), 600, 400);
        gameLauncher.launch();
    }

}
