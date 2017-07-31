package it.torneodelleparrocchie.fantacalcio.services.impl;

import it.torneodelleparrocchie.fantacalcio.entities.LogEntity;
import it.torneodelleparrocchie.fantacalcio.repositories.LogRepository;
import it.torneodelleparrocchie.fantacalcio.services.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService {
    private Logger logger = LoggerFactory.getLogger(LoggerServiceImpl.class);

    private final LogRepository logRepository;

    @Autowired
    public LoggerServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void trace(String operation,
                      String information,
                      String message) {
        logger.trace("op: \"{}\", info: \"{}\", message: \"{}\"", operation, information, message);
        log("TRACE", operation, information, message);
    }

    @Override
    public void debug(String operation,
                      String information,
                      String message) {
        logger.debug("op: \"{}\", info: \"{}\", message: \"{}\"", operation, information, message);
        log("DEBUG", operation, information, message);
    }

    @Override
    public void info(String operation,
                     String information,
                     String message) {
        logger.info("op: \"{}\", info: \"{}\", message: \"{}\"", operation, information, message);
        log("INFO", operation, information, message);
    }

    @Override
    public void warn(String operation,
                     String information,
                     String message) {
        logger.warn("op: \"{}\", info: \"{}\", message: \"{}\"", operation, information, message);
        log("WARN", operation, information, message);
    }

    @Override
    public void error(String operation,
                      String information,
                      String message) {
        logger.error("op: \"{}\", info: \"{}\", message: \"{}\"", operation, information, message);
        log("ERROR", operation, information, message);
    }

    private void log(String level,
                     String operation,
                     String information,
                     String message) {
        logRepository.save(new LogEntity(level, operation, information, message));
    }
}
