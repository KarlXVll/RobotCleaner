package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Hall extends JFrame {
    private final Robot robot;
    private final ArrayList<Man> men;
    private final JPanel panel;

    private final Image manImage;
    private final Image robotImage;
    private final Image garbageImage;
    final int seconds = 5;


    public Hall() {
        // Создаем объекты робота и людей
        robot = new Robot(0, 0);
        men = new ArrayList<>();


        // Загружаем изображения
        manImage = new ImageIcon("C:\\Users\\user\\IdeaProjects\\LastTask\\src\\main\\java\\org\\example\\man.png").getImage();
        robotImage = new ImageIcon("C:\\Users\\user\\IdeaProjects\\LastTask\\src\\main\\java\\org\\example\\robot.png").getImage();
        garbageImage = new ImageIcon("C:\\Users\\user\\IdeaProjects\\LastTask\\src\\main\\java\\org\\example\\garbage.png").getImage();

        // Создаем панель для отрисовки
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Отрисовываем робота и всех людей
                g.drawImage(robotImage, robot.getX(), robot.getY(), 50, 50, null);
                g.setColor(Color.RED);
                int numMen = (int) (Math.random() * 4) + 1;

                for (int i = 0; i < numMen; i++) {
                    int startX = (int) (Math.random() * 450);
                    int startY = (int) (Math.random() * 450);
                    Man man = new Man(startX, startY);
                    g.drawImage(manImage, man.getX(), man.getY(), 50, 50, null);
                }

                // Отрисовываем мусор
                ArrayList<Garbage> garbageList = Garbage.getGarbageList();
                for (Garbage garbage : garbageList) {
                    g.drawImage(garbageImage, garbage.getX(), garbage.getY(), 25, 25, null);
                }
            }
        };



        // Добавляем панель на окно
        add(panel);

        // Устанавливаем размеры окна и делаем его видимым
        setSize(500, 500);
        setVisible(true);



        // Устанавливаем операцию закрытия окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Запускаем анимацию
        animate();
    }

    private void animate() {

        while (true) {
            robot.move();
            robot.collectGarbage();

            // Люди двигаются случайным образом
            moveMen();

            // Генерируем мусор
            ArrayList<Man> menList = men.getClass();
            for (Man man : menList) {
                if (!man.hasGarbage) {
                    new Thread(() -> {
                        Garbage garbage = new Garbage(man.getX(), man.getY());
                        man.throwGarbage();
                        panel.repaint();
                        try {
                            Thread.sleep(man.getDelay() * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        garbage.remove();
                        man.removeGarbage();
                        panel.repaint();
                    }).start();
                }
            }

            // Перерисовываем окно после каждой итерации
            panel.repaint();

            // Задержка для создания эффекта анимации
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveMen() {
        Random random = new Random();

        for (Man man : men) {
            // Определяем случайное направление движения для каждого человека
            int direction = random.nextInt(4);
            switch (direction) {
                case 0 -> { // направо
                    if (man.getX() < 450) {
                        man.move(50, 0);
                    }
                }
                case 1 -> { // вниз
                    if (man.getY() < 450) {
                        man.move(0, 50);
                    }
                }
                case 2 -> { // налево
                    if (man.getX() > 50) {
                        man.move(-50, 0);
                    }
                }
                case 3 -> { // вверх
                    if (man.getY() > 50) {
                        man.move(0, -50);
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        new Hall();
    }
}