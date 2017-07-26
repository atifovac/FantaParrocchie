package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by dsalvatore on 19/06/17.
 */
public interface FantaTeamRepository extends CrudRepository<FantaTeam, String> {

    FantaTeam findByPresident(@Param("president") User president);

    FantaTeam findByName(@Param("name") String name);
}
