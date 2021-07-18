package java0.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 */
public class Work09 {
    private static volatile Integer result;
    final static Lock lock = new ReentrantLock();
    final static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(Work09::writeResult);
        thread.start();
        System.out.println(Work09.getResult());
    }


    private static void writeResult() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            result = new Random().nextInt();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    private static Integer getResult() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
            return result;
        } finally {
            lock.unlock();
        }
    }
}

