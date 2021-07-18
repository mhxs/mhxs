package java0.test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * ExecutorService
 */
public class Work02 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Callable<Integer> callable = () -> {
            System.out.println(Thread.currentThread().getName());
            return new Random().nextInt();
        };
        Future<Integer> submit = executor.submit(callable);
        try {
            System.out.println("result: " + submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

