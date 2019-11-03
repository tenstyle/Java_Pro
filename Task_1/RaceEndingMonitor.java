package Lesson_5.Task_1;

import java.util.concurrent.CountDownLatch;

public class RaceEndingMonitor {
    private final CountDownLatch countDownLatch;

    public RaceEndingMonitor(int carsCount) {
        this.countDownLatch = new CountDownLatch(carsCount);
    }

    public void waitingAllCarFinished() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notifyAboutFinished() {
        countDownLatch.countDown();
    }
}