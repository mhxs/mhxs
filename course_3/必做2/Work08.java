package java0.test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * CompletableFuture
 */
public class Work08 {
    private static Integer result;


    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(()->{Work08.writeResult();return null;})
                .thenAccept(v -> System.out.println(">>>>" + Work08.getResult())).join();
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
        return result;
    }
}

