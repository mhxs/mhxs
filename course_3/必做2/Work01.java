package java0.test;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask
 */
public class Work01 {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName());
            return new Random().nextInt();
        });
        new Thread(task).start();

        try {
            System.out.println("result: " + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}