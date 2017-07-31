package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.Day;
import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.entities.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {

    Rating findByGiornataAndGiocatore(@Param("giornata") Day giornata, @Param("giocatore") Player giocatore);

    List<Rating> findByGiornata(@Param("giornata") Day giornata);

}
