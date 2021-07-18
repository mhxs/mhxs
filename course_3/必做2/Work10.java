package java0.test;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized
 */
public class Work10 {
    private static volatile Integer result;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                writeResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println(Work10.getResult());
    }


    private static void writeResult() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName());
            result = new Random().nextInt();
            lock.notify();
        }
    }

    private static Integer getResult() throws InterruptedException {
        synchronized (lock) {
            lock.wait();
            return result;
        }
    }
}

