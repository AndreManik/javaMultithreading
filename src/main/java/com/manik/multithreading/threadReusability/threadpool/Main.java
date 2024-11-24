package com.manik.multithreading.threadReusability.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutorExtention(
                2,
                2,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2)
        );


        Thread fread = new Thread(() -> { throw new RuntimeException("Hello");});
        fread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("i'm from exception handler");
        });
        threadPoolExecutor.execute(fread);
        /*Future<String> future = threadPoolExecutor.submit(() -> {
           throw new RuntimeException("Hello");
        });


        try {
            future.get();
        } catch (InterruptedException | ExecutionException | RuntimeException e) {
            e.printStackTrace();
        }*/

        //threadPoolExecutor.shutdown();

    }

    static class ThreadPoolExecutorExtention extends ThreadPoolExecutor {

        public ThreadPoolExecutorExtention(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public ThreadPoolExecutorExtention(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public ThreadPoolExecutorExtention(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            //super.afterExecute(r, t);

            if (r instanceof Future) {
                try{
                    Future<String> future = (Future) r;
                    future.get();
                } catch (Exception e){
                    System.out.println("It is future");
                }
            } else if (t != null) {
                System.out.println("Hello from afterExecute");
            }
        }

        public ThreadPoolExecutorExtention(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }
    }

    static class CallableTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Pass";
        }
    }
}
