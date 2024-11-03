package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainMenu {
    private List<MovingWindow> floatingWindows = new ArrayList<>();
    private Random random = new Random();
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MainMenu() {
        createFloatingWindow("WindowKill Game", 100, 100, false);
        createFloatingWindow("New Run", 200, 250, true);
        createFloatingWindow("Options", 200, 350, true);
        createFloatingWindow("Credits", 200, 450, true);

        Timer animationTimer = new Timer(16, e -> updateWindows());
        animationTimer.start();
    }

    private void createFloatingWindow(String title, int x, int y, boolean isButton) {
        JFrame floatingWindow = new JFrame(title);
        floatingWindow.setSize(600, 400);
        floatingWindow.setLocationRelativeTo(null); // Center the window
        floatingWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        if (title.equals("WindowKill Game")) {
            floatingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        if (isButton) {
            JButton button = new JButton(title);
            button.addActionListener(e -> handleMenuAction(title));
            button.setPreferredSize(new Dimension(200, 100));
            floatingWindow.setLayout(new GridBagLayout());
            floatingWindow.add(button);
        } else {
            JLabel label = new JLabel(title, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 24));
            floatingWindow.add(label, BorderLayout.CENTER);
        }

        floatingWindow.setVisible(true);
        floatingWindows.add(new MovingWindow(floatingWindow));

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                floatingWindow.getComponentAt(initialClick);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = floatingWindow.getLocation().x;
                int thisY = floatingWindow.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;

                // Prevent the window from being pushed outside of the monitor screen
                if (X < 0) X = 0;
                if (Y < 0) Y = 0;
                if (X + floatingWindow.getWidth() > screenSize.width) X = screenSize.width - floatingWindow.getWidth();
                if (Y + floatingWindow.getHeight() > screenSize.height) Y = screenSize.height - floatingWindow.getHeight();

                floatingWindow.setLocation(X, Y);
                checkCollision();
            }
        };

        floatingWindow.addMouseListener(mouseAdapter);
        floatingWindow.addMouseMotionListener(mouseAdapter);
    }

    private void updateWindows() {
        for (MovingWindow movingWindow : floatingWindows) {
            movingWindow.update();
        }
        checkCollision();
    }

    private void checkCollision() {
        for (int i = 0; i < floatingWindows.size(); i++) {
            for (int j = i + 1; j < floatingWindows.size(); j++) {
                MovingWindow w1 = floatingWindows.get(i);
                MovingWindow w2 = floatingWindows.get(j);
                if (w1.getBounds().intersects(w2.getBounds())) {
                    handleElasticCollision(w1, w2);
                }
            }
        }
    }

    private void handleElasticCollision(MovingWindow w1, MovingWindow w2) {
        Rectangle r1 = w1.getBounds();
        Rectangle r2 = w2.getBounds();

        int overlapX = Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x);
        int overlapY = Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y);

        if (overlapX > r1.width * 0.75 || overlapY > r1.height * 0.75) {
            int dx = r1.x - r2.x;
            int dy = r1.y - r2.y;

            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    w1.setLocation(r2.x + r2.width, r1.y);
                    w2.setLocation(r1.x - r2.width, r2.y);
                } else {
                    w1.setLocation(r2.x - r1.width, r1.y);
                    w2.setLocation(r1.x + r1.width, r2.y);
                }
                w1.reverseVelocityX();
                w2.reverseVelocityX();
            } else {
                if (dy > 0) {
                    w1.setLocation(r1.x, r2.y + r2.height);
                    w2.setLocation(r2.x, r1.y - r2.height);
                } else {
                    w1.setLocation(r1.x, r2.y - r1.height);
                    w2.setLocation(r2.x, r1.y + r1.height);
                }
                w1.reverseVelocityY();
                w2.reverseVelocityY();
            }
        }
    }

    private void handleMenuAction(String action) {
        switch (action) {
            case "New Run":
                for (MovingWindow window : floatingWindows) {
                    window.dispose();
                }
                floatingWindows.clear();
                new GameWindow();
                break;
            case "Options":
                // Open options window
                break;
            case "Credits":
                // Open credits window
                break;
        }
    }

    private class MovingWindow {
        private JFrame window;
        private int velocityX;
        private int velocityY;

        public MovingWindow(JFrame window) {
            this.window = window;
            this.velocityX = random.nextInt(10) - 5;
            this.velocityY = random.nextInt(10) - 5;
        }

        public void update() {
            Point location = window.getLocation();
            location.translate(velocityX, velocityY);
            window.setLocation(location);

            // Prevent the window from being pushed outside of the monitor screen
            if (location.x < 0) {
                location.x = 0;
                reverseVelocityX();
            }
            if (location.y < 0) {
                location.y = 0;
                reverseVelocityY();
            }
            if (location.x + window.getWidth() > screenSize.width) {
                location.x = screenSize.width - window.getWidth();
                reverseVelocityX();
            }
            if (location.y + window.getHeight() > screenSize.height) {
                location.y = screenSize.height - window.getHeight();
                reverseVelocityY();
            }

            // Slow down the velocity
            velocityX *= 0.95;
            velocityY *= 0.95;

            // Stop the window when the velocity is low enough
            if (Math.abs(velocityX) < 1) velocityX = 0;
            if (Math.abs(velocityY) < 1) velocityY = 0;
        }

        public Rectangle getBounds() {
            return window.getBounds();
        }

        public void setLocation(int x, int y) {
            window.setLocation(x, y);
        }

        public void reverseVelocityX() {
            velocityX = -velocityX;
        }

        public void reverseVelocityY() {
            velocityY = -velocityY;
        }

        public void dispose() {
            window.dispose();
        }
    }
}