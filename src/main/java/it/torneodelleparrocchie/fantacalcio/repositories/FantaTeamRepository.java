package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dsalvatore on 19/06/17.
 */
public interface FantaTeamRepository extends CrudRepository<FantaTeam, String> {

    FantaTeam findByPresident(User president);
}
