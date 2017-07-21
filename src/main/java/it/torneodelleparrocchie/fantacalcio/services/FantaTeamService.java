package it.torneodelleparrocchie.fantacalcio.services;

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.exceptions.FantaException;

import java.util.List;

/**
 * Created by dsalvatore on 20/06/17.
 */

public interface FantaTeamService {

    FantaTeam getFantaTeam(String name);

    List<FantaTeam> getFantaTeamList();

    FantaTeam saveFantaTeam(String oldName, String name, String president, Long fantaMoney) throws FantaException;

    void deleteFantaTeam(String name);
}
