/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.threadex2sem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author thomas
 */
public class UseAllCores {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        for (int count = 0; count < 50; count++) {
            Runnable task = new Task(count);
            es.submit(task);
        }
        System.out.println("Main is done");
        es.shutdown();
//        boolean done = es.awaitTermination(10, TimeUnit.SECONDS);
    }

}

class Task implements Runnable {

    private int input = 0;

    Task(int in) {
        this.input = in;
    }

    @Override
    public void run() {
        fibonacci(input);
    }

    public static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
