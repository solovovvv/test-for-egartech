package com.mycompany.testforegartech.controllers;

import com.mycompany.testforegartech.entities.Instrument;
import com.mycompany.testforegartech.services.InstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RequestMapping("/instruments")
@Controller
public class InstrumentController {

    private static Logger logger = LoggerFactory.getLogger(InstrumentController.class);

    private InstrumentService instrumentService;

    @Autowired
    public void setInstrumentService(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("greeting", "Welcome to Stock Exchange");
        logger.info("Listing instruments");
        List<Instrument> instruments = instrumentService.findAll();
        model.addAttribute("instruments", instruments);
        logger.info("No. of instruments: " + instruments.size());
        return "instruments/list1";
    }


    @GetMapping("/instr")
    @ResponseBody
    public String update(@RequestParam(name = "id") String id,
                         @RequestParam(name = "placement_date") String placementDate,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "cost") String cost) {

        logger.info("request received: id=" + id + "&placement_date=" + placementDate + "&name=" + name + "&cost=" + cost);

        Instrument instrument = instrumentService.findById(Long.parseLong(id));

        Date placementDate1 = null;
        if (!placementDate.equals("")) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                placementDate1 = sdf.parse(placementDate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            instrument.setPlacementDate(placementDate1);
        }
        if (!name.equals("")) instrument.setName(name);
        if (!cost.equals("")) instrument.setCost(Long.parseLong(cost));
        instrumentService.save(instrument);

        instrumentService.findAll().forEach(System.out::println);

        return "response: id=" + id + "&placement_date=" + placementDate + "&name=" + name + "&cost=" + cost;
    }


    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("instrument", new Instrument());
        return "instruments/form";
    }

    @PostMapping
    public String save(@Valid Instrument instrument, Errors errors) {
        if (errors.hasErrors()) {
            return "instruments/form";
        }

        logger.info("Instrument submitted: " + instrument);

        Instrument instr = new Instrument();
        instr.setPlacementDate(instrument.getPlacementDate());
        instr.setName(instrument.getName());
        instr.setCost(instrument.getCost());
        instrumentService.save(instrument);

        instrumentService.findAll().forEach(System.out::println);

        return "redirect:/instruments";
    }

}
