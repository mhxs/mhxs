package java0.test;

import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.parkNanos
 */
public class Work05 {
    private static volatile Integer result;


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            result = new Random().nextInt();

        });
        thread.start();
        LockSupport.parkNanos(1000);
        System.out.println(Work05.result);

    }
}

