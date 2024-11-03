package entities;

import java.awt.*;

public class Player {
    private int x, y;
    private int health;
    private int speed;
    private int fireRate;

    public Player() {
        this.x = 400;
        this.y = 300;
        this.health = 100;
        this.speed = 5;
        this.fireRate = 10;
    }

    public void update() {
        // Update player position and shooting
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 20, 20);
    }

    public void increaseFireRate() {
        fireRate += 1;
    }

    public void enableMultishot() {
        // Enable multishot logic
    }

    public void increaseHealth() {
        health += 10;
    }

    public void increaseSize() {
        // Increase player size logic
    }

    public void increaseSpeed() {
        speed += 1;
    }
}