package it.torneodelleparrocchie.fantacalcio.services;

public interface LoggerService {
    void trace(String operation, String information, String message);

    void debug(String operation, String information, String message);

    void info(String operation, String information, String message);

    void warn(String operation, String information, String message);

    void error(String operation, String information, String message);
}
