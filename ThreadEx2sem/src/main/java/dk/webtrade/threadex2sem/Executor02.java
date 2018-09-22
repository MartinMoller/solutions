/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.threadex2sem;

/**
 *
 * @author thomas
 */

import java.util.concurrent.Executors;


public class Executor02 {

    public static void main( String[] args ) {
        java.util.concurrent.ExecutorService workingJack = Executors.newSingleThreadExecutor();
        for ( int count = 0; count < 25; count++ ) {
            workingJack.submit( () -> {
                // Det er en rød opgave at forklare hvad denne fejl skyldes
                // Fjern udkommenteringen i næste linje
//                System.out.println( "Hello "+ count + " to us" );
            } );
        }
        workingJack.shutdown();
    }
}

