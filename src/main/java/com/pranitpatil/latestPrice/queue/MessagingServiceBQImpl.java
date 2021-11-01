package com.pranitpatil.latestPrice.queue;

import com.pranitpatil.latestPrice.exception.MessagingException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessagingServiceBQImpl implements MessagingService{

    private static final MessagingServiceBQImpl instance = new MessagingServiceBQImpl();

    private MessagingServiceBQImpl() {
    }

    public static MessagingServiceBQImpl getInstance() {
        return instance;
    }

    private BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    @Override
    public boolean send(Object message) throws MessagingException {
        try {
            return queue.add(message);
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException | ClassCastException e) {
            //TODO log
            throw new MessagingException(e);
        }
    }

    @Override
    public Object poll() throws MessagingException {
        if(queue.isEmpty()){
            //TODO log
            throw new MessagingException("Queue is empty.");
        }
        return queue.poll();
    }
}
