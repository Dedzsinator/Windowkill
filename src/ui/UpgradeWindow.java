package ui;

import controller.UpgradeController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpgradeWindow extends JFrame {
    private UpgradeController upgradeController;

    public UpgradeWindow(UpgradeController upgradeController) {
        this.upgradeController = upgradeController;

        this.setTitle("Upgrades");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null); // Center the window
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(5, 1));

        String[] upgrades = {"Fire Rate", "Multishot", "Health", "Size Up", "Speed Up"};
        for (String upgrade : upgrades) {
            JButton upgradeButton = new JButton(upgrade);
            upgradeButton.addActionListener(e -> upgradeController.applyUpgrade(upgrade));
            this.add(upgradeButton);
        }

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
    }
}