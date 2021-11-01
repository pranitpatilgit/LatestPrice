package com.pranitpatil.latestPrice.repository.impl;

import com.pranitpatil.latestPrice.consumer.api.ConsumerService;
import com.pranitpatil.latestPrice.consumer.api.InstrumentProcessor;
import com.pranitpatil.latestPrice.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Runnable producerTask = () -> {
            new Producer().process();
        };

        Runnable processorTask = () -> {
            new InstrumentProcessor().process();
        };

        Runnable consumerApiTask = () -> {
            Random random = new Random();
            while(true) {
                int id = random.nextInt(10);
                BigDecimal price = ConsumerService.getInstance().getLatestInstrumentPrice(id);
                logger.debug("Price for {} is {}.", id, price);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executorService.submit(producerTask);
        executorService.submit(processorTask);
        executorService.submit(consumerApiTask);

        executorService.shutdown();
    }
}
