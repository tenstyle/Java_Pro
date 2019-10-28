package Lesson_4.HomeWork.Task1;

public class ThreadsABC {
    private static boolean processedA = false;
    private static boolean processedB = false;
    private static boolean processedC = true;
    private static ThreadsABC threadsABC = new ThreadsABC();

    public static void threads() {
        Thread thread1 = new Thread(threadsABC::printA);
        Thread thread2 = new Thread(threadsABC::printB);
        Thread thread3 = new Thread(threadsABC::printC);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public synchronized void printA() {
        for (int i = 0; i < 5; i++) {
            while (!processedC) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("A");
            processedA = true;
            processedB = false;
            processedC = false;
            notifyAll();
        }
    }

    public synchronized void printB() {
        for (int i = 0; i < 5; i++) {
            while (!processedA) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("B");
            processedA = false;
            processedB = true;
            processedC = false;
            notifyAll();
        }
    }

    public synchronized void printC() {
        for (int i = 0; i < 5; i++) {
            while (!processedB) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("C ");
            processedA = false;
            processedB = false;
            processedC = true;
            notifyAll();
        }
    }

}