package entities.enemies;

import java.awt.*;

public abstract class Enemy {
    protected int x, y;
    protected int health;

    public abstract void update();
    public abstract void render(Graphics g);
}