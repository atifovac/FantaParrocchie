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

    List<Player> getAllByFantaTeamName(@Param("fantaTeam") String fantaTeamName);

    Player getByNameAndSurnameAndRoleAndRealTeam(@Param("name") String name, @Param("surname") String surname,
                                                 @Param("role") String role, @Param("realTeam") String realTeam);
}
