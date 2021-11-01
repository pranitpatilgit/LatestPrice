package com.pranitpatil.latestPrice.producer;

import com.pranitpatil.latestPrice.consumer.api.InstrumentProcessor;
import com.pranitpatil.latestPrice.dto.Instrument;
import com.pranitpatil.latestPrice.dto.InstrumentDetails;
import com.pranitpatil.latestPrice.exception.MessagingException;
import com.pranitpatil.latestPrice.queue.MessagingService;
import com.pranitpatil.latestPrice.queue.MessagingServiceBQImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer {

    private static final int chunkSize = 50;
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private MessagingService messagingService = MessagingServiceBQImpl.getInstance();

    /**
     * Creates random instruments and adds them to the list.
     * Once the chunk size is reached it processes the chunk and moves to next chunk.
     */
    public void process(){
        logger.debug("Starting Producer");
        List<Instrument> instruments = new ArrayList<>();
        Random random = new Random();
        for (int i=0 ; i < 200 ; i++ ){
            Instrument instrument = new Instrument(random.nextInt(10), LocalDateTime.now(),
                    new InstrumentDetails(new BigDecimal(random.nextDouble())));
            instruments.add(instrument);

            if(instruments.size() >= chunkSize){
                sendChunk(instruments);
                instruments.clear();
            }
        }
    }

    /**
     * Sends all the instruments to messaging queue.
     * This process is done in parallel for efficiency.
     * @param instruments
     */
    public void sendChunk(List<Instrument> instruments){
        instruments.parallelStream().forEach(inst -> {
            try {
                logger.debug("Sending Instrument {}", inst.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                messagingService.send(inst);
            } catch (MessagingException e) {
                logger.error("Error while sending message.", e);
                //TODO handle
            }
        });
    }
}
