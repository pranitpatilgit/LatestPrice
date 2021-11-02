package com.pranitpatil.latestPrice.helper;

public class ThreadHelper {

    public static void sleep(int durationInMilis){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
