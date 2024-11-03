package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;

public class CellBoss extends Enemy {
    @Override
    public void update() {
        // Cell boss specific update logic
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, 40, 40);
    }
}