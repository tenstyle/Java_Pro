package Lesson_5.Task_1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SteadyCar {
    private final CyclicBarrier waitForPrepare;
    private RaceEndingMonitor raceEndingMonitor;

    public SteadyCar(int carCount, RaceEndingMonitor raceEndingMonitor) {
        this.waitForPrepare = new CyclicBarrier(carCount + 1);
        this.raceEndingMonitor = raceEndingMonitor;
    }

    public Car createCar (Race race, int speed) {
        return new Car(race, speed, waitForPrepare, raceEndingMonitor);
    }

    public void awaitingAllCarsStarted() {
        try {
            waitForPrepare.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}