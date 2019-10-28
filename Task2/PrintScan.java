package Lesson_4.HomeWork.Task2;

public class PrintScan {

    public static void main(String[] args) {
        Object printer = new Object();
        Object scanner = new Object();

        Thread user1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь1 подошел к принтеру");
                synchronized (printer) {
                    System.out.println("Пользователь1 напечатал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь1 освободил принтер");
                }
            }
        });
        user1.start();

        Thread user2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь2 подошел к принтеру");
                synchronized (printer) {
                    System.out.println("Пользователь2 напечатал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь2 освободил принтер");
                }
            }
        });
        user2.start();

        Thread user3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь3 подошел к принтеру");
                synchronized (printer) {
                    System.out.println("Пользователь3 напечатал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь3 освободил принтер");
                }
            }
        });
        user3.start();

        Thread user4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь4 подошел к принтеру");
                synchronized (printer) {
                    System.out.println("Пользователь4 напечатал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь4 освободил принтер");
                }
            }
        });
        user4.start();

        Thread user5 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь5 подошел к принтеру");
                synchronized (printer) {
                    System.out.println("Пользователь5 напечатал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь5 освободил принтер");
                }
            }
        });
        user5.start();

        Thread user6 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь6 подошел к сканеру");
                synchronized (scanner) {
                    System.out.println("Пользователь6 отсканировал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь6 освободил сканер");
                }
            }
        });
        user6.start();

        Thread user7 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь7 подошел к сканеру");
                synchronized (scanner) {
                    System.out.println("Пользователь7 отсканировал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь7 освободил сканер");
                }
            }
        });
        user7.start();

        Thread user8 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь8 подошел к сканеру");
                synchronized (scanner) {
                    System.out.println("Пользователь8 отсканировал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь8 освободил сканер");
                }
            }
        });
        user8.start();

        Thread user9 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь9 подошел к сканеру");
                synchronized (scanner) {
                    System.out.println("Пользователь9 отсканировал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь9 освободил сканер");
                }
            }
        });
        user9.start();

        Thread user10 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Пользователь10 подошел к сканеру");
                synchronized (scanner) {
                    System.out.println("Пользователь10 отсканировал документ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Пользователь10 освободил сканер");
                }
            }
        });
        user10.start();
    }
}