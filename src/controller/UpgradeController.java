package controller;

import entities.Player;

public class UpgradeController {
    private GameController gameController;

    public UpgradeController(GameController gameController) {
        this.gameController = gameController;
    }

    public void applyUpgrade(String upgrade) {
        Player player = gameController.getPlayer();
        switch (upgrade) {
            case "Fire Rate":
                player.increaseFireRate();
                break;
            case "Multishot":
                player.enableMultishot();
                break;
            case "Health":
                player.increaseHealth();
                break;
            case "Size Up":
                player.increaseSize();
                break;
            case "Speed Up":
                player.increaseSpeed();
                break;
        }
        gameController.resumeGame();
    }
}