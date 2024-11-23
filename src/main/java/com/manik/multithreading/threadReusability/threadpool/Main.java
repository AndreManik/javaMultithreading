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

    }

    static class CallableTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Pass";
        }
    }
}
