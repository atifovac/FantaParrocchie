package it.torneodelleparrocchie.fantacalcio.services;

import it.torneodelleparrocchie.fantacalcio.entities.DailyFormation;
import it.torneodelleparrocchie.fantacalcio.entities.Day;
import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.exceptions.FantaException;

public interface DailyFormationService {
    Float calculateForDayAndFantaTeam(Day day, FantaTeam fantaTeam);

    DailyFormation getFormationForFantaTeamAndDate(String fantasquadra,
                                                   Integer giornata);

    DailyFormation addPlayerToDailyFormation(String fantaTeam, Integer day, String name, String surname) throws FantaException;

    void setDailyFormationModule(String fantaTeam, Integer day, String stringModule) throws FantaException;

    DailyFormation removePlayerToDailyFormation(String fantaTeam, Integer day, String name, String surname);
}
