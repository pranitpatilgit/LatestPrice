package com.pranitpatil.latestPrice.repository;

import com.pranitpatil.latestPrice.dto.Instrument;

public interface InstrumentRepository {

    /**
     * This method finds the instrument from repository by id, if not found then @throws {@link com.pranitpatil.latestPrice.exception.NotFoundException}
     * @param id
     * @return Instrument
     * @throws com.pranitpatil.latestPrice.exception.NotFoundException
     */
    Instrument getInstrumentById(int id);

    /**
     * Saves or updates the provided instrument in repository.
     * @param instrument
     * @return Instrument
     */
    Instrument saveInstrument(Instrument instrument);
}
