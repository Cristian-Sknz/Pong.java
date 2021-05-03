package me.skiincraft.pong.entity;

import me.skiincraft.ousucanvas.ImageBuilder;
import me.skiincraft.ousucanvas.elements.ElementAlignment;
import me.skiincraft.ousucanvas.elements.ElementContainer;
import me.skiincraft.ousucanvas.shape.ShapeElement;
import me.skiincraft.pong.engine.entity.GameEntity2D;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

public class Racket implements GameEntity2D {

    private int x;
    private int y;
    private final Dimension2D size;

    private int speed;
    private int points;

    private ElementContainer container;

    public Racket(int x, int y, int speed, Dimension2D size) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dimension2D getSize() {
        return size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, size.getWidth(), size.getHeight());
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(getBounds());
    }
}
