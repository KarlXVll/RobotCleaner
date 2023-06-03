package org.example;

import java.util.ArrayList;
import java.util.Random;
public class Garbage {
    private static int x;
    private static int y;

    // Список всех экземпляров класса Garbage
    private static ArrayList<Garbage> garbageList = new ArrayList<>();

    public Garbage(int x, int y) {
        Garbage.x = x;
        Garbage.y = y;

        // Добавляем экземпляр класса Garbage в список
        garbageList.add(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void throwGarbage() {
        // выбираем случайный объект мусора из списка
        Random random = new Random();
        int index = random.nextInt(garbageList.size());
        Garbage garbage = garbageList.get(index);

        // меняем координаты объекта мусора на новые случайные
        garbage.x = random.nextInt(450);
        garbage.y = random.nextInt(450);
    }

    // метод для добавления объекта мусора в список
    public static void addGarbage(Garbage garbage) {
        garbageList.add(garbage);
    }



    public static ArrayList<Garbage> getGarbageList() {
        return garbageList;
    }

    public void setX(int x_0) {
        x = x_0;
    }

    public void setY(int y_0) {
        y = y_0;
    }
}