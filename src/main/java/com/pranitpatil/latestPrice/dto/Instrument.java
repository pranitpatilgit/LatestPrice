package com.pranitpatil.latestPrice.dto;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return id == that.id && Objects.equals(asOf, that.asOf) && Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, asOf, payload);
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "id=" + id +
                ", asOf=" + asOf +
                ", payload=" + payload +
                '}';
    }
}
