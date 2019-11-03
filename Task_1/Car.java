package Lesson_5.Task_1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static volatile boolean winnerExist = false;
    private static final Object lock = new Object();

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier waitForPrepare;
    private RaceEndingMonitor raceEndingMonitor;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier waitForPrepare, RaceEndingMonitor raceEndingMonitor) {
        this.race = race;
        this.speed = speed;
        this.waitForPrepare = waitForPrepare;
        this.raceEndingMonitor = raceEndingMonitor;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            prepare();
            waitAllCarPrepareFinished();
        } catch (Exception e) {
            e.printStackTrace();
        }
        startRace();
    }

    private void waitAllCarPrepareFinished() throws InterruptedException, BrokenBarrierException {
        waitForPrepare.await();
    }

    private void startRace() {
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        checkWinner();
        raceEndingMonitor.notifyAboutFinished();
    }

    private void prepare() throws InterruptedException {
        System.out.println(this.name + " готовится");
        Thread.sleep(500 + (int)(Math.random() * 800));
        System.out.println(this.name + " готов");
    }

    private void checkWinner() {
        synchronized (lock) {
            if (winnerExist)
                return;
            winnerExist = true;
            System.out.println(this.name + " - ПОБЕДИТЕЛЬ!");
        }
    }
}