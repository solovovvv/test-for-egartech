package com.mycompany.testforegartech.services;

import com.mycompany.testforegartech.entities.Instrument;
import com.mycompany.testforegartech.repositories.InstrumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("instrumentService")
@Transactional
public class InstrumentServiceImpl implements InstrumentService {

    private static Logger logger = LoggerFactory.getLogger(InstrumentServiceImpl.class);

    private InstrumentRepository instrumentRepository;

    @Autowired
    public void setInstrumentRepository(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Instrument findById(Long id) {
        return instrumentRepository.findById(id).get();
    }

    @Override
    public Instrument save(Instrument instrument) {
        instrumentRepository.save(instrument);
        logger.info("Instrument saved with id: " + instrument.getId());
        return instrument;
    }

    @Override
    public void delete(Instrument instrument) {
        instrumentRepository.delete(instrument);
        logger.info("Instrument deleted with id: " + instrument.getId());
    }

}
