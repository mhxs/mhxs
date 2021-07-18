package java0.test;

import java.util.Random;

/**
 * thread.join()
 */
public class Work03 {
    private static volatile Integer result;


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            result = new Random().nextInt();
        });
        thread.start();
        thread.join();
        System.out.println(Work03.result);

    }
}

