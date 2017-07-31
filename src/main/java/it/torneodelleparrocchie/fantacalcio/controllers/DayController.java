package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.services.DayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/day")
public class DayController {
    private Logger logger = LoggerFactory.getLogger(DayController.class);

    private final DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @PostMapping("/add")
    public void addNextDay() {
        dayService.addNextDay();
    }

    @PostMapping("/close")
    public void closeDay() {
        dayService.closeDay();
    }

    @PostMapping("/calculate")
    public void calculate() {
        dayService.calculate();
    }

    @PostMapping("/calculate/{giornata}")
    public void calculate(@PathVariable("giornata") Integer day) {
        dayService.calculate(day);
    }
}
