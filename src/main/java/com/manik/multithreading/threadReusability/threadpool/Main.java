package com.manik.multithreading.threadReusability.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                20,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        System.out.println("[1] Thread pool size: " + threadPoolExecutor.getPoolSize());
        System.out.println("[1] Active tasks: " + threadPoolExecutor.getActiveCount());
        threadPoolExecutor.submit(new SleepingTask(1));
        threadPoolExecutor.submit(new SleepingTask(2));

        System.out.println("[2] Thread pool size: " + threadPoolExecutor.getPoolSize());
        System.out.println("[2] Active tasks: " + threadPoolExecutor.getActiveCount());

        threadPoolExecutor.submit(new SleepingTask(3));
        threadPoolExecutor.submit(new SleepingTask(4));
        System.out.println("[3] Active tasks: " + threadPoolExecutor.getActiveCount());

        threadPoolExecutor.submit(new SleepingTask(5));
        System.out.println("[3] Thread pool size: " + threadPoolExecutor.getPoolSize());
        System.out.println("[4] Active tasks: " + threadPoolExecutor.getActiveCount());

        System.out.println("Queue size: " + threadPoolExecutor.getQueue().remainingCapacity());
        threadPoolExecutor.submit(new SleepingTask(6));
        threadPoolExecutor.submit(new SleepingTask(7));

        threadPoolExecutor.allowsCoreThreadTimeOut();
        while(true){
            System.out.println("[5] Active tasks: " + threadPoolExecutor.getActiveCount());
            System.out.println("Task count " + threadPoolExecutor.getTaskCount());
            System.out.println("Completed tasks " + threadPoolExecutor.getCompletedTaskCount());
            System.out.println("Pool size " + threadPoolExecutor.getPoolSize());
            Thread.sleep(1000);
        }

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
