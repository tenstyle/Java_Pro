package Lesson_5.Task_1;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final Semaphore maxCarsLimit;

    public Tunnel(int carsCount) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.maxCarsLimit = new Semaphore(carsCount / 2);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                maxCarsLimit.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                maxCarsLimit.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}