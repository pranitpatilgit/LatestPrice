package com.pranitpatil.latestPrice.consumer.api;

import com.pranitpatil.latestPrice.dto.Instrument;
import com.pranitpatil.latestPrice.dto.InstrumentDetails;
import com.pranitpatil.latestPrice.repository.InstrumentRepository;
import com.pranitpatil.latestPrice.repository.impl.InstrumentRepositoryInMemImpl;

import java.math.BigDecimal;

public class ConsumerService {

    private InstrumentRepository instrumentRepository;

    private ConsumerService(){
        this.instrumentRepository = InstrumentRepositoryInMemImpl.getInstance();
    }

    private static final ConsumerService instance = new ConsumerService();

    public static ConsumerService getInstance() {
        return instance;
    }

    public Instrument getLatestInstrumentDetails(long id){
        return instrumentRepository.getInstrumentById(id);
    }

    public BigDecimal getLatestInstrumentPrice(long id){
        InstrumentDetails instrumentDetails =
                (InstrumentDetails) instrumentRepository.getInstrumentById(id).getPayload();
        return instrumentDetails.getPrice();
    }
}
