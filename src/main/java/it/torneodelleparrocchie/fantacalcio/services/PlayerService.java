package it.torneodelleparrocchie.fantacalcio.services;
/**
 * Created by dsalvatore on 19/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.Player;

import java.util.List;

public interface PlayerService {
    Player getPlayer(Long id);

    List<Player> getPlayersList();

    Long savePlayer(Player player);

    void deletePlayer(Long id);

    Player savePlayer(String name, String surname, String realTeam, String fantaTeam);
}
