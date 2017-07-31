package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.DailyFormation;
import it.torneodelleparrocchie.fantacalcio.entities.Day;
import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyFormationRepository extends CrudRepository<DailyFormation, Long> {


    DailyFormation findByFantaSquadraAndGiornata(@Param("fantaSquadra") FantaTeam fantaSquadra,
                                                 @Param("giornata") Day giornata);

    List<DailyFormation> findAllByFantaSquadra(@Param("fantaSquadra") FantaTeam fantaSquadra);

    List<DailyFormation> findAllByGiornata(@Param("giornata") Day giornata);

    DailyFormation findLastByGiornataAndFantaSquadra(@Param("giornata") Day giornata,
                                                     @Param("fantaSquadra") FantaTeam fantaSquadra);
}
