package com.pranitpatil.latestPrice.repository;

import com.pranitpatil.latestPrice.dto.Instrument;

public interface InstrumentRepository {

    /**
     * This method finds the instrument from repository by id.
     * @param id
     * @return Instrument
     * @throws com.pranitpatil.latestPrice.exception.NotFoundException if id is not found
     */
    Instrument getInstrumentById(long id);

    /**
     * Saves or updates the provided instrument in repository.
     * @param instrument
     * @return Instrument
     */
    Instrument saveInstrument(Instrument instrument);

    /**
     * Saves the instrument if the provided instrument is latest instrument.
     * It uses instrument's asOf field for comparison.
     * @param instrument
     * @return
     */
    Instrument saveIfLatest(Instrument instrument);
}
