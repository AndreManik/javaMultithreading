package com.manik.multithreading.threadReusability.threadpool;


import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor tpe = new ScheduledThreadPoolExecutor(1);
        tpe.schedule(() -> System.out.println("Task 1"), 5, TimeUnit.SECONDS);

        tpe.shutdown();
        ScheduledFuture<?> future = tpe.scheduleAtFixedRate(() -> System.out.println("Task"), 5, 1, TimeUnit.SECONDS);
        tpe.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);

    }
}
