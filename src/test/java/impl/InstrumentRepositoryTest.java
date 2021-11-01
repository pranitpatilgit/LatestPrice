package impl;

import com.pranitpatil.latestPrice.dto.Instrument;
import com.pranitpatil.latestPrice.exception.NotFoundException;
import com.pranitpatil.latestPrice.repository.InstrumentRepository;
import com.pranitpatil.latestPrice.repository.impl.InstrumentRepositoryInMemImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentRepositoryTest {

    private InstrumentRepository instrumentRepository;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp(){
        instrumentRepository = InstrumentRepositoryInMemImpl.getInstance();

        Instrument instrument = new Instrument(1 , LocalDateTime.now(), new BigDecimal(1.23));
        instrumentRepository.saveInstrument(instrument);
    }

    @Test
    public void givenInstrumentId_whenGetInstrumentByID_thenReturnInstrument(){
        Instrument instrument = instrumentRepository.getInstrumentById(1);

        Assert.assertEquals(1, instrument.getId());
    }

    @Test
    public void givenWrongInstrumentId_whenGetInstrumentByID_thenThrowNotFoundException(){
        expectedEx.expect(NotFoundException.class);
        expectedEx.expectMessage("Instrument with id - 3 is not found.");

        instrumentRepository.getInstrumentById(3);
    }

    @Test
    public void givenInstrument_whenSaveInstrument_thenSaveAndReturnInstrument(){
        Instrument instrument = new Instrument(2 , LocalDateTime.now(), new BigDecimal(1.24));
        Instrument savedInstrument = instrumentRepository.saveInstrument(instrument);

        Assert.assertEquals(instrument.getId(), savedInstrument.getId());
        Assert.assertEquals(instrument.getAsOf(), savedInstrument.getAsOf());
        Assert.assertEquals(instrument.getPayload(), savedInstrument.getPayload());
    }

    @Test
    public void givenUpdatedInstrument_whenSaveInstrument_thenSaveAndReturnUpdatedInstrument(){
        Instrument instrument = new Instrument(1 , LocalDateTime.now(), new BigDecimal(1.24));
        Instrument savedInstrument = instrumentRepository.saveInstrument(instrument);

        Assert.assertEquals(instrument.getId(), savedInstrument.getId());
        Assert.assertEquals(instrument.getAsOf(), savedInstrument.getAsOf());
        Assert.assertEquals(instrument.getPayload(), savedInstrument.getPayload());
    }

    @Test
    public void givenUpdatedInstrumentOfLaterAsOf_whenSaveIfLatest_thenSaveAndReturnUpdatedInstrument(){
        Instrument instrument = new Instrument(1 , LocalDateTime.now(), new BigDecimal(1.24));
        Instrument savedInstrument = instrumentRepository.saveIfLatest(instrument);

        Assert.assertEquals(instrument.getId(), savedInstrument.getId());
        Assert.assertEquals(instrument.getAsOf(), savedInstrument.getAsOf());
        Assert.assertEquals(instrument.getPayload(), savedInstrument.getPayload());
    }

    @Test
    public void givenUpdatedInstrumentofOlderAsOf_whenSaveIfLatest_thenReturnOldInstrument(){
        Instrument oldInstrument = instrumentRepository.getInstrumentById(1);
        Instrument instrument = new Instrument(1 , LocalDateTime.now().minus(Duration.ofSeconds(10)), new BigDecimal(1.24));
        Instrument savedInstrument = instrumentRepository.saveIfLatest(instrument);

        Assert.assertEquals(oldInstrument.getId(), savedInstrument.getId());
        Assert.assertEquals(oldInstrument.getAsOf(), savedInstrument.getAsOf());
        Assert.assertEquals(oldInstrument.getPayload(), savedInstrument.getPayload());
    }

    @Test
    public void givenNewInstrument_whenSaveIfLatest_thenReturnSavedInstrument(){
        Instrument instrument = new Instrument(2 , LocalDateTime.now(), new BigDecimal(1.24));
        Instrument savedInstrument = instrumentRepository.saveIfLatest(instrument);

        Assert.assertEquals(instrument.getId(), savedInstrument.getId());
        Assert.assertEquals(instrument.getAsOf(), savedInstrument.getAsOf());
        Assert.assertEquals(instrument.getPayload(), savedInstrument.getPayload());
    }
}
