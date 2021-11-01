package com.pranitpatil.latestPrice.repository.impl;

import com.pranitpatil.latestPrice.dto.Instrument;
import com.pranitpatil.latestPrice.exception.NotFoundException;
import com.pranitpatil.latestPrice.repository.InstrumentRepository;

import java.util.concurrent.ConcurrentHashMap;

public class InstrumentRepositoryInMemImpl implements InstrumentRepository {

    //Private Constructor to refrain others to create and object of this class.
    private InstrumentRepositoryInMemImpl(){};

    private static final InstrumentRepository instance = new InstrumentRepositoryInMemImpl();

    public static InstrumentRepository getInstance(){
        return instance;
    }

    private ConcurrentHashMap<Integer, Instrument> instruments = new ConcurrentHashMap<>();

    @Override
    public Instrument getInstrumentById(int id) {
        Instrument instrument = instruments.get(id);

        if(instrument == null){
            throw new NotFoundException("Instrument with id - " + id + " is not found.");
        }

        try {
            return (Instrument) instrument.clone();
        } catch (CloneNotSupportedException e) {
            return new Instrument(instrument.getId(), instrument.getAsOf(), instrument.getPayload());
        }
    }

    @Override
    public Instrument saveInstrument(Instrument instrument) {
        return null;
    }
}
