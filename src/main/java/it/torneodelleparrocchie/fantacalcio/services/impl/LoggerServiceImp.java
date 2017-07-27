package it.torneodelleparrocchie.fantacalcio.services.impl;

import it.torneodelleparrocchie.fantacalcio.entities.LogEntity;
import it.torneodelleparrocchie.fantacalcio.repositories.LogRepository;
import it.torneodelleparrocchie.fantacalcio.services.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImp implements LoggerService {
    private Logger logger = LoggerFactory.getLogger(LoggerServiceImp.class);

    private final LogRepository logRepository;

    @Autowired
    public LoggerServiceImp(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void trace(String operation,
                      String information,
                      String message) {
        logger.trace(String.format("op: \"%s\", info: \"%s\", message: \"%s\"", operation, information, message));
        log("TRACE", operation, information, message);
    }

    @Override
    public void debug(String operation,
                      String information,
                      String message) {
        logger.debug(String.format("op: \"%s\", info: \"%s\", message: \"%s\"", operation, information, message));
        log("DEBUG", operation, information, message);
    }

    @Override
    public void info(String operation,
                     String information,
                     String message) {
        logger.info(String.format("op: \"%s\", info: \"%s\", message: \"%s\"", operation, information, message));
        log("INFO", operation, information, message);
    }

    @Override
    public void warn(String operation,
                     String information,
                     String message) {
        logger.warn(String.format("op: \"%s\", info: \"%s\", message: \"%s\"", operation, information, message));
        log("WARN", operation, information, message);
    }

    @Override
    public void error(String operation,
                      String information,
                      String message) {
        logger.error(String.format("op: \"%s\", info: \"%s\", message: \"%s\"", operation, information, message));
        log("ERROR", operation, information, message);
    }

    private void log(String level,
                     String operation,
                     String information,
                     String message) {
        logRepository.save(new LogEntity(level, operation, information, message));
    }
}
