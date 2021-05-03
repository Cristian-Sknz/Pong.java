package me.skiincraft.pong.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameAdapter extends JComponent implements ActionListener {

    private final GameImplementation game;
    private final Timer timer;
    private boolean started;

    public GameAdapter(GameImplementation game, int width, int height) {
        this.game = game;
        this.timer = new Timer(game.updateDelay, this);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(width, height));
        this.addKeyListener(game.getKeyAdapter());
    }

    public void start(){
        if (started){
            return;
        }
        game.preload();
        timer.start();
        game.getKeyAdapter().getTimer().start();
        this.started = true;
    }

    public void stop(){
        if (!started){
            return;
        }
        game.getKeyAdapter().getTimer().stop();
        timer.stop();
        this.started = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw((Graphics2D) g);
    }

    public GameImplementation getGame() {
        return game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public Timer getTimer() {
        return timer;
    }
}
