package java0.test;

import java.util.Random;

/**
 * thread.sleep()
 */
public class Work04 {
    private static volatile Integer result;


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            result = new Random().nextInt();

        });
        thread.start();
        Thread.sleep(1000);
        System.out.println(Work04.result);

    }
}

