package com.pranitpatil.latestPrice.queue;

import com.pranitpatil.latestPrice.exception.MessagingException;

public interface MessagingService {

    /**
     * Sends a message to queue.
     * Used by producer to send messages.
     * @param message
     * @return boolean to specify if send is successful
     * @throws com.pranitpatil.latestPrice.exception.MessagingException if there is error in sending
     */
    boolean send(Object message) throws MessagingException;

    /**
     * Gets message from queue
     * @return Object
     * @throws MessagingException if there is error in receiving message or if queue is empty
     */
    Object poll() throws MessagingException;
}
