package com.pranitpatil.latestPrice.dto;

import java.math.BigDecimal;

public class InstrumentDetails {

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
