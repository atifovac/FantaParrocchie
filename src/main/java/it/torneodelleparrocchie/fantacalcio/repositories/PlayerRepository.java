package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.Player;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dsalvatore on 19/06/17.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
