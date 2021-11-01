package com.pranitpatil.latestPrice.dto;

import java.math.BigDecimal;

public class InstrumentDetails {

    public InstrumentDetails(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "InstrumentDetails{" +
                "price=" + price +
                '}';
    }
}
