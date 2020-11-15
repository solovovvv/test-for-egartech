package com.mycompany.testforegartech.services;

import com.mycompany.testforegartech.entities.Instrument;

import java.util.List;

public interface InstrumentService {
    List<Instrument> findAll();
    Instrument findById(Long id);
    Instrument save(Instrument instrument);
    void delete(Instrument instrument);
}