package com.mycompany.testforegartech;

import com.mycompany.testforegartech.config.DataJpaConfig;
import com.mycompany.testforegartech.config.ServicesConfig;
import com.mycompany.testforegartech.entities.Instrument;
import com.mycompany.testforegartech.services.InstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class, ServicesConfig.class);
        InstrumentService instrumentService = ctx.getBean(InstrumentService.class);

        List<Instrument> instruments = instrumentService.findAll();
        logger.info("size: " + instruments.size());
        print(instruments);

        ctx.close();
    }

    private static <T> void print(List<T> list) {
        logger.info("Printing instruments:");
        list.forEach(System.out::println);
    }

}
