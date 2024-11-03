package entities.enemies;

import java.awt.*;

public class LaserEnemy extends Enemy {
    @Override
    public void update() {
        // Laser enemy specific update logic
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 20, 20);
    }
}