package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by dsalvatore on 19/06/17.
 */
public interface FantaTeamRepository extends CrudRepository<FantaTeam, String> {
}
