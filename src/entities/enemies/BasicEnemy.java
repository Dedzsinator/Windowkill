package entities.enemies;

import java.awt.*;

public class BasicEnemy extends Enemy {
    public BasicEnemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 10;
    }

    @Override
    public void update() {
        // Basic movement logic
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 20, 20);
    }
}