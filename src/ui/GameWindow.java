package ui;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JFrame {
    private GameController gameController;
    private UpgradeWindow upgradeWindow;

    public GameWindow() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); // Center the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        gameController = new GameController(this);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (upgradeWindow == null || !upgradeWindow.isVisible()) {
                        upgradeWindow = new UpgradeWindow(new controller.UpgradeController(gameController));
                    } else {
                        upgradeWindow.dispose();
                    }
                }
            }
        });

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;

                setLocation(X, Y);
            }
        };

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);

        this.setVisible(true);

        gameController.startGameLoop();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        gameController.renderGame(g);
    }
}