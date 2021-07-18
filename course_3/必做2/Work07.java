package java0.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * CountDownLatch
 */
public class Work07 {
    private static volatile Integer result;
    static CountDownLatch countDownLatch = new CountDownLatch(1);


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(Work07::writeResult);
        thread.start();
        countDownLatch.await();
        System.out.println(Work07.getResult());

    }

    private static void writeResult() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        result = new Random().nextInt();
        countDownLatch.countDown();
    }

    private static Integer getResult() {
        return result;
    }
}

