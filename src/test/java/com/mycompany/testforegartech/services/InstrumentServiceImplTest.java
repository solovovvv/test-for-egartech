package com.mycompany.testforegartech.services;

import com.mycompany.testforegartech.config.DataJpaConfig;
import com.mycompany.testforegartech.config.ServicesConfig;
import com.mycompany.testforegartech.entities.Instrument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing of class {@link InstrumentServiceImpl}
 *
 * @author Solovov Vasiliy
 * @version 1.0
 */
@DisplayName("Instrument Service Implementation Test")
class InstrumentServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(InstrumentServiceImpl.class);
    private GenericApplicationContext ctx;
    private InstrumentService instrumentService;

    @BeforeEach
    void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class, ServicesConfig.class);
        instrumentService = ctx.getBean(InstrumentService.class);
        assertNotNull(instrumentService);
    }

    @AfterEach
    void tearDown() {
        ctx.close();
    }

    @Test
    void findAllTest() {
        List<Instrument> instruments = instrumentService.findAll();
        assertEquals(instruments.size(), 6);
        printList(instruments);
    }

    private static <T> void printList(List<T> list) {
        logger.info("Printing List: ");
        list.forEach(t -> logger.info(t.toString()));
    }

    @Test
    void findByIdTest() {
        Instrument instrument = instrumentService.findById(2L);
        assertNotNull(instrument);
        printList(Collections.singletonList(instrument));
    }

    @Test
    void saveTest() {
        Instrument instrument = new Instrument();
        instrument.setPlacementDate(new Date((new GregorianCalendar(2019, 10, 15)).getTime().getTime()));
        instrument.setName("Альф-Банк");
        instrument.setCost(1000L);
        instrumentService.save(instrument);

        List<Instrument> instruments = instrumentService.findAll();
        assertEquals(instruments.size(), 7);
        printList(instruments);
    }

    @Test
    void updateTest() {
        Instrument instrument = instrumentService.findById(2L);
        assertNotNull(instrument);

        instrument.setPlacementDate(new Date((new GregorianCalendar(2019, 5, 3)).getTime().getTime()));
        instrument.setName("Альфа-Банк");
        instrument.setCost(5500L);
        instrumentService.save(instrument);
        assertEquals(instrument.getId(), 2);

        List<Instrument> instruments = instrumentService.findAll();
        assertEquals(instruments.size(), 6);
        printList(instruments);
    }

    @Test
    void deleteTest() {
        Instrument instrument = instrumentService.findById(2L);
        assertNotNull(instrument);
        instrumentService.delete(instrument);
        assertEquals(instrument.getId(), 2);

        List<Instrument> instruments = instrumentService.findAll();
        assertEquals(instruments.size(), 5);
        printList(instruments);
    }

}