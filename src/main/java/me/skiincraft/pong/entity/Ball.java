package me.skiincraft.pong.entity;

import me.skiincraft.ousucanvas.ImageBuilder;
import me.skiincraft.ousucanvas.elements.ElementAlignment;
import me.skiincraft.ousucanvas.elements.ElementContainer;
import me.skiincraft.ousucanvas.shape.ShapeElement;
import me.skiincraft.pong.engine.entity.GameEntity2D;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball implements GameEntity2D {

    private int x;
    private int y;

    private int speedX;
    private int speedY;

    private final int radius;
    private final Dimension2D size;

    public Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.speedX = 4;
        this.speedY = 4;
        this.radius = radius;
        this.size = new Dimension(radius*2, radius*2);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getDiameter(){
        return radius * 2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, size.getWidth(), size.getHeight());
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillOval(getX(), getY(),  (int) size.getWidth(), (int) size.getHeight());

    }
}
