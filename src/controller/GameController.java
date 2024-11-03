package controller;

import ui.GameWindow;
import ui.UpgradeWindow;
import entities.Player;
import javax.swing.Timer;
import java.awt.Graphics;

public class GameController {
    private boolean isPaused = false;
    private Player player;
    private GameWindow gameWindow;

    public GameController(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        player = new Player();
    }

    public void startGameLoop() {
        Timer timer = new Timer(16, e -> {
            if (!isPaused) {
                updateGame();
                gameWindow.repaint();
            }
        });
        timer.start();
    }

    public void openUpgradeWindow() {
        isPaused = true;
        new UpgradeWindow(new UpgradeController(this));
    }

    public void resumeGame() {
        isPaused = false;
    }

    private void updateGame() {
        player.update();
        // Update player, enemies, bullets, and check collisions
    }

    public void renderGame(Graphics g) {
        player.render(g);
        // Render other game components
    }

    public Player getPlayer() {
        return player;
    }
}