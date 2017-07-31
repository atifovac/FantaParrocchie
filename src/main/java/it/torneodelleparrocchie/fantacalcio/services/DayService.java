package it.torneodelleparrocchie.fantacalcio.services;

public interface DayService {

    void addNextDay();

    void calculate();

    void calculate(Integer day);

    void closeDay();
}
