package ru.geekbrains;

public class Main {
    private static final Object monitor = new Object();
    public static volatile char currentLetter = 'A';

    public static void main(String[] args) {
        Thread thread1 = new Thread( () -> {
            printA();
        });

        Thread thread2 = new Thread( () -> {
            printB();
        });

        Thread thread3 = new Thread( () -> {
            printC();
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }

    public static void printA() {
        synchronized (monitor) {
            try {
                for(int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printB() {
        synchronized (monitor) {
            try {
                for(int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printC() {
        synchronized (monitor) {
            try {
                for(int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        monitor.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
