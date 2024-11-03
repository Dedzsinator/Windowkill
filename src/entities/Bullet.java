package entities;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
    private int x, y;
    private int speed;

    public Bullet(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void update() {
        y -= speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 5, 10);
    }
}