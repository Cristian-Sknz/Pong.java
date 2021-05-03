package me.skiincraft.pong;

import me.skiincraft.pong.engine.GameImplementation;
import me.skiincraft.pong.engine.sound.DefaultGameSound;
import me.skiincraft.pong.entity.Ball;
import me.skiincraft.pong.entity.OpponentRacket;
import me.skiincraft.pong.entity.Racket;
import me.skiincraft.pong.util.PongTask;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PongGame extends GameImplementation {

    private final List<Racket> players = new ArrayList<>();
    private Ball ball;
    private boolean point;
    private PongTask task;

    public PongGame(int updateDelay) {
        super(updateDelay);
    }

    @Override
    public void preload() {
        createObjects();
        createRackets();
        createInput();
        loadSounds();
    }

    public void createObjects(){
        this.ball = new Ball(getWidth()/2, getHeight()/2, 12);
        this.task = new PongTask(1, () -> point = false);
    }

    private void createRackets(){
        this.players.add(new Racket(6, getHeight()/2 - 40, 5, new Dimension(14, 80)));
        this.players.add(new OpponentRacket(585 - 5, getHeight()/2 - 40, 5, new Dimension(14, 80)));
    }

    public void createInput(){
        Racket racket = players.get(0);
        addGameKey((key) -> {
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
                racket.setY(racket.getY() - racket.getSpeed());
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
                racket.setY(racket.getY() + racket.getSpeed());
        });
    }

    public void loadSounds(){
        try {
            addGameSound(new DefaultGameSound("music", PongGame.class.getResourceAsStream("/music.wav")));
            addGameSound(new DefaultGameSound("fail", PongGame.class.getResourceAsStream("/fail.wav")));
            addGameSound(new DefaultGameSound("point", PongGame.class.getResourceAsStream("/point.wav")));
            addGameSound(new DefaultGameSound("pong", PongGame.class.getResourceAsStream("/pong.wav")));
        } catch (Exception e){
            e.printStackTrace();
        }
        getGameSound("music").loop();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        background(graphics2D);
        scoreboard(graphics2D);

        ball.draw(graphics2D);
        moveBall();
        checkCollisions();
        moveRacket();

        for (Racket p : players) {
            p.draw(graphics2D);
        }
    }

    private void background(Graphics2D graphics2D){
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(getWidth()/2 - 5, 0, 5, getHeight());
    }

    public void moveBall(){
        ball.setX(ball.getX() + ball.getSpeedX());
        ball.setY(ball.getY() + ball.getSpeedY());
    }

    public void moveRacket(){
        OpponentRacket racket = (OpponentRacket) players.get(1);
        if (ball.getX() > 300) {
            if (ball.getY() < racket.getY() + racket.getError()) {
                racket.setY(racket.getY() - racket.getSpeed());
            }
            if (ball.getY() > racket.getY() + 60 + racket.getError()) {
                racket.setY(racket.getY() + racket.getSpeed());
            }
        }
        errorChance(racket);
    }

    private void errorChance(OpponentRacket racket){
        if (racket.getPoints() > players.get(0).getPoints()){
            racket.setError(racket.getError() + 1);
            if (racket.getError() > 40){
                racket.setError(40);
            }
        } else {
            racket.setError(racket.getError() - 1);
            if (racket.getError() < 10) {
                racket.setError(0);
            }
        }
    }

    public void checkCollisions(){
        if (ball.getX() > getWidth() - ball.getDiameter() || ball.getX() < 0){
            ball.setSpeedX(ball.getSpeedX() * -1);
            scorePoint();
            point = true;
        }
        if (ball.getY() > getHeight() - ball.getDiameter()|| ball.getY() < 0){
            ball.setSpeedY(ball.getSpeedY() * -1);
        }
        if (!point) {
            if (hasRacketCollisions()) {
                ball.setSpeedX(ball.getSpeedX() * -1);
                getGameSound("pong").play();
            }
        }
        task();
    }

    public void task(){
        if (task.isEnd()){
            task.restart(1);
        }
        task.update();
    }

    public void scorePoint(){
        if (ball.getX() > getWidth() - ball.getDiameter()) {
            Racket racket = players.get(0);
            racket.setPoints(racket.getPoints() + 1);
            getGameSound("point").play();
        } else if (ball.getX() < 0){
            Racket racket = players.get(1);
            racket.setPoints(racket.getPoints() + 1);
            getGameSound("fail").play();
        }
    }

    public void scoreboard(Graphics2D g){
        drawPoints(g, 200, 12, players.get(0));
        drawPoints(g, 335, 12, players.get(1));
    }

    public void drawPoints(Graphics2D graphics2D, int x, int y, Racket racket){
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fillRect(x, y, 60, 30);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 20));
        graphics2D.drawString(racket.getPoints() + "", x + 25, y + 22);
    }

    public boolean hasRacketCollisions(){
        return players.stream().anyMatch(racket -> racket.getBounds().intersects(ball.getBounds()));
    }
}
