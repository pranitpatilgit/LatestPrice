package com.pranitpatil.latestPrice.consumer.api;

import com.pranitpatil.latestPrice.dto.Instrument;
import com.pranitpatil.latestPrice.exception.MessagingException;
import com.pranitpatil.latestPrice.queue.MessagingService;
import com.pranitpatil.latestPrice.queue.MessagingServiceBQImpl;
import com.pranitpatil.latestPrice.repository.InstrumentRepository;
import com.pranitpatil.latestPrice.repository.impl.InstrumentRepositoryInMemImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InstrumentProcessor {

    private static final Logger logger = LoggerFactory.getLogger(InstrumentProcessor.class);

    private MessagingService messagingService = MessagingServiceBQImpl.getInstance();
    private static final int threadPoolSize = 10;

    public void process(){
        logger.debug("Starting Processor.");
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        for (int i=0 ; i < threadPoolSize ; i++){
            executorService.submit(new InstrumentProcessTask());
        }

        executorService.shutdown();
    }

    public static class InstrumentProcessTask implements Runnable{

        private static final Logger logger = LoggerFactory.getLogger(InstrumentProcessTask.class);

        private MessagingService messagingService = MessagingServiceBQImpl.getInstance();
        private InstrumentRepository instrumentRepository = InstrumentRepositoryInMemImpl.getInstance();

        @Override
        public void run() {
            while (true) {
                try {
                    Instrument instrument = (Instrument) messagingService.poll();
                    logger.debug("Processing instrument {}.", instrument.toString());
                    instrumentRepository.saveIfLatest(instrument);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (MessagingException e) {
                    //logger.error("Error while polling from messaging service.", e);
                    //break;
                }
            }
        }
    }
}
