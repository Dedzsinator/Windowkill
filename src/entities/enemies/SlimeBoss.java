package entities.enemies;

import java.awt.Color;
import java.awt.Graphics;

public class SlimeBoss extends Enemy {
    @Override
    public void update() {
        // Slime boss specific update logic
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 40, 40);
    }
}