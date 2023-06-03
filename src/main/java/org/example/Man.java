package org.example;

public class Man {
    private static int x;
    private static int y;
    static boolean hasGarbage;
    private int delay;

    public Man(int startX, int startY) {
        x = startX;
        y = startY;
        hasGarbage = false;
        this.delay = delay;
    }

    public void move(int dx, int dy) {
        if (x + dx > 0 && x + dx < 450) {
            x += dx;
        }
        if (y + dy > 0 && y + dy < 450) {
            y += dy;
        }
    }

    public static void throwGarbage() {
        if (!hasGarbage) {
            Garbage garbage = new Garbage(x, y);
            hasGarbage = true;

            // Меняем координаты объекта мусора на координаты мужчины
            garbage.setX(x);
            garbage.setY(y);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDelay() {
        return delay;
    }

    public void removeGarbage() {
        hasGarbage = false;
    }
}