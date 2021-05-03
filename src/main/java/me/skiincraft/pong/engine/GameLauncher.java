package me.skiincraft.pong.engine;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class GameLauncher {

    private final String name;
    private final GameImplementation game;
    private final int width;
    private final int height;

    public GameLauncher(String name, GameImplementation game, int width, int height) {
        this.name = name;
        this.game = game;
        this.width = width;
        this.height = height;
    }

    public void launch() {
        GameAdapter adapter = new GameAdapter(game, width, height);
        reflect(adapter);
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            //frame.getContentPane().add(new BarVi, BorderLayout.LINE_START);
            frame.getContentPane().add(adapter, BorderLayout.LINE_END);
            //frame.add(adapter);
            frame.setTitle(name);
            frame.setResizable(false);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            adapter.start();
        });
    }

    private void reflect(GameAdapter gameAdapter){
        try {
            Field field = GameImplementation.class.getDeclaredField("gameAdapter");
            field.setAccessible(true);
            field.set(game, gameAdapter);
        } catch (Exception ignored){
            ignored.printStackTrace();
        }
    }
}
