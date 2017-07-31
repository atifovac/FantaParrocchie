package it.torneodelleparrocchie.fantacalcio.repositories;

import it.torneodelleparrocchie.fantacalcio.entities.Day;
import org.springframework.data.repository.CrudRepository;

public interface DayRepository extends CrudRepository<Day, Integer> {

    Day findMaxByNumeroGiornata();

    Day findMinNumeroGiornataCalcolabile();
}
