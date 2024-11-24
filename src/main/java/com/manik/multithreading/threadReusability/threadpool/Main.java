package com.manik.multithreading.threadReusability.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        System.out.println("[1] Thread pool size: " + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.submit(new SleepingTask(1));
        threadPoolExecutor.submit(new SleepingTask(2));

        System.out.println("[2] Thread pool size: " + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.submit(new SleepingTask(3));
        threadPoolExecutor.submit(new SleepingTask(4));

        threadPoolExecutor.submit(new SleepingTask(5));
        System.out.println("[3] Thread pool size: " + threadPoolExecutor.getPoolSize());

        System.out.println("Queue size: " + threadPoolExecutor.getQueue().remainingCapacity());
        threadPoolExecutor.submit(new SleepingTask(6));
        threadPoolExecutor.submit(new SleepingTask(7));
    }

    static class SleepingTask implements Runnable {

        private final Integer taskId;

        public SleepingTask(Integer taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {

            System.out.println("Task id: " + taskId);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
