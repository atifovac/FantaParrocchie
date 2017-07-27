package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoggerController {

    private final LoggerService loggerService;

    @Autowired
    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @PostMapping("/trace")
    public void trace(@RequestParam("operation") String operation,
                      @RequestParam("information") String information,
                      @RequestParam("message") String message) {
        loggerService.trace(operation, information, message);
    }

    @PostMapping("/debug")
    public void debug(@RequestParam("operation") String operation,
                      @RequestParam("information") String information,
                      @RequestParam("message") String message) {
        loggerService.debug(operation, information, message);
    }

    @PostMapping("/info")
    public void info(@RequestParam("operation") String operation,
                     @RequestParam("information") String information,
                     @RequestParam("message") String message) {
        loggerService.info(operation, information, message);
    }

    @PostMapping("/warn")
    public void warn(@RequestParam("operation") String operation,
                     @RequestParam("information") String information,
                     @RequestParam("message") String message) {
        loggerService.warn(operation, information, message);
    }

    @PostMapping("/error")
    public void error(@RequestParam("operation") String operation,
                      @RequestParam("information") String information,
                      @RequestParam("message") String message) {
        loggerService.error(operation, information, message);
    }
}
