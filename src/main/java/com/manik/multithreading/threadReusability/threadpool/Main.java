package com.manik.multithreading.threadReusability.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(100)
        );
        System.out.println("Pool size: " + threadPoolExecutor.getPoolSize());
        threadPoolExecutor.prestartAllCoreThreads();
        System.out.println("Pool size: " + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.execute(() -> System.out.println("Task 1 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 2 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 3 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 1 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 2 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 3 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 1 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 2 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 3 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 1 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 2 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 3 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 1 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 2 " + Thread.currentThread().getName()));
        threadPoolExecutor.execute(() -> System.out.println("Task 3 " + Thread.currentThread().getName()));

        System.out.println("Pool size: " + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.shutdownNow();
        threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);

        //threadPoolExecutor.shutdown();


        /*Future<String> future = threadPoolExecutor.submit(new CallableTask());
future.ca
        String result = future.get();*/
    }

    static class CallableTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Pass";
        }
    }
}
