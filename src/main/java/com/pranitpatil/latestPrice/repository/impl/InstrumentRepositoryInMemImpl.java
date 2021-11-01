package com.pranitpatil.latestPrice.repository.impl;

import com.pranitpatil.latestPrice.dto.Instrument;
import com.pranitpatil.latestPrice.exception.NotFoundException;
import com.pranitpatil.latestPrice.repository.InstrumentRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class InstrumentRepositoryInMemImpl implements InstrumentRepository {

    //Private Constructor to refrain others to create and object of this class.
    private InstrumentRepositoryInMemImpl(){};

    private static final InstrumentRepository instance = new InstrumentRepositoryInMemImpl();

    public static InstrumentRepository getInstance(){
        return instance;
    }

    private ConcurrentHashMap<Long, Instrument> instruments = new ConcurrentHashMap<>();

    @Override
    public Instrument getInstrumentById(long id) {
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
        instruments.put(instrument.getId(), instrument);

        return getInstrumentById(instrument.getId());
    }

    @Override
    public Instrument saveIfLatest(Instrument instrument) {
        instruments.merge(instrument.getId(), instrument,
                (oldIns, newIns) -> newIns.getAsOf().isAfter(oldIns.getAsOf()) ? newIns : oldIns);

        return getInstrumentById(instrument.getId());
    }
}
