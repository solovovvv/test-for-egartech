package com.mycompany.testforegartech;

import com.mycompany.testforegartech.entities.Instrument;
import com.mycompany.testforegartech.services.InstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class AppSpringBoot {

    private static Logger logger = LoggerFactory.getLogger(AppSpringBoot.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(AppSpringBoot.class, args);
        logger.info("Application started...");
        InstrumentService instrumentService = ctx.getBean(InstrumentService.class);

        List<Instrument> instruments = instrumentService.findAll();
        logger.info("size: " + instruments.size());
        print(instruments);

        System.in.read();
        ctx.close();
    }

    private static <T> void print(List<T> list) {
        logger.info("Printing instruments:");
        list.forEach(System.out::println);
    }
}
