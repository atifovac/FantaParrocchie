package it.torneodelleparrocchie.fantacalcio.services;
/**
 * Created by dsalvatore on 19/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.enums.FormationRoleEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RealTeamEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RosterRoleEnum;

import java.util.List;

public interface PlayerService {
    Player getPlayer(Long id);

    List<Player> getPlayersList();

    Long savePlayer(Player player);

    void deletePlayer(Long id);

    Player savePlayer(Long id, String name, String surname, RosterRoleEnum rosterRole, FormationRoleEnum formationRole, RealTeamEnum realTeam, int value);

    Player getByNameAndSurname(String name, String surname);
}
