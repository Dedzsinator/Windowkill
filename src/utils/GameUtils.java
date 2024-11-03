package utils;

import java.awt.Rectangle;

public class GameUtils {
    public static boolean checkCollision(Rectangle r1, Rectangle r2) {
        return r1.intersects(r2);
    }
}