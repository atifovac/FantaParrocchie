package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by dsalvatore on 19/06/17.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {

    List<Player> getAllByRealTeam(@Param("realTeam") String realTeam);

    List<Player> getAllByFantaTeam(@Param("fantaTeam") String fantaTeam);
}
