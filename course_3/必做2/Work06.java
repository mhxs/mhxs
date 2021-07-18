package java0.test;

import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * 阻塞getResult方法
 */
public class Work06 {
    private static volatile Integer result;


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(Work06::writeResult);
        thread.start();
        LockSupport.parkNanos(1000);
        System.out.println(Work06.getResult());

    }

    private static void writeResult() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        result = new Random().nextInt();
    }

    private static Integer getResult() {
        while (result == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getResult();
        }
        return result;
    }
}

