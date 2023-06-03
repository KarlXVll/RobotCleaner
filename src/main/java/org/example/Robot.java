package org.example;

import java.util.ArrayList;
import java.util.Iterator;

public class Robot {
    private int x;
    private int y;
    private final int height;
    public Robot(int startX, int startY) {
        x = startX;
        y = startY;
        height = 50;
    }

    public void move() {
        if (getY() % (getHeight() * 2) <= 0) {
            // Двигаемся вправо
            setX(getX() + 50);
        } else {
            // Двигаемся влево
            setX(getX() - 50);
        }

        if (getX() >= 450 || getX() <= 0) {
            // Проверяем, достиг ли робот нижней границы окна
            if (getY() >= 550) {
                // Двигаемся вверх
                setY(getY() - getHeight());
            } else {
                // Двигаемся вниз
                setY(getY() + getHeight());
            }
        }
    }



    public void collectGarbage() {
        Iterator<Garbage> iterator = Garbage.getGarbageList().iterator();
        while (iterator.hasNext()) {
            Garbage garbage = iterator.next();
            if (garbage.getX() == x && garbage.getY() == y) {
                iterator.remove();
                System.out.println("Мусор собран");
            }
        }
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }

    public void setY(int i) {
        y = i;
    }

    public void setX(int i) {
        x = i;
    }
}