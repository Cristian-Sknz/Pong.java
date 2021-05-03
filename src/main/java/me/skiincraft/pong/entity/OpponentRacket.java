package me.skiincraft.pong.entity;

import java.awt.geom.Dimension2D;

public class OpponentRacket extends Racket {

    private int error;

    public OpponentRacket(int x, int y, int speed, Dimension2D size) {
        super(x, y, speed, size);
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
