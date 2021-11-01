package com.pranitpatil.latestPrice.dto;

import java.time.LocalDateTime;

public class Instrument implements Cloneable{

    private long id;
    private LocalDateTime asOf;
    private Object payload;

    public Instrument(long id, LocalDateTime asOf, Object payload) {
        this.id = id;
        this.asOf = asOf;
        this.payload = payload;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getAsOf() {
        return asOf;
    }

    public void setAsOf(LocalDateTime asOf) {
        this.asOf = asOf;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
