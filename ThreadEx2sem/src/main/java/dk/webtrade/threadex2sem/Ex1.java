/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.threadex2sem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author thomas
 */
public class Ex1 {
    public static void main(String[] args) {
       ExecutorService es = Executors.newFixedThreadPool(4);
        for (char i = 'A'; i < 'Z'; i++) {
//            System.out.print(i);
            es.submit(new Worker(i));
        }
    }
}
class Worker implements Runnable{
    char c;
    public Worker(char toWrite){
        this.c = toWrite;
    }
    @Override
    public void run() {
        System.out.println(""+c+c+c);
        
    }
    
}
