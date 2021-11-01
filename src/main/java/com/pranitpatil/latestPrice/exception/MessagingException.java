package com.pranitpatil.latestPrice.exception;

public class MessagingException extends Exception{

    public MessagingException(String message) {
        super(message);
    }

    public MessagingException(Throwable cause) {
        super(cause);
    }
}
