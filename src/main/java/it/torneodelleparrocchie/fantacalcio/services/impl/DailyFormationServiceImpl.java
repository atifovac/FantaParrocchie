package it.torneodelleparrocchie.fantacalcio.services.impl;

import it.torneodelleparrocchie.fantacalcio.entities.*;
import it.torneodelleparrocchie.fantacalcio.enums.FormationModuleEnum;
import it.torneodelleparrocchie.fantacalcio.exceptions.FantaException;
import it.torneodelleparrocchie.fantacalcio.repositories.*;
import it.torneodelleparrocchie.fantacalcio.services.DailyFormationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyFormationServiceImpl implements DailyFormationService {
    private Logger logger = LoggerFactory.getLogger(DailyFormationServiceImpl.class);

    private final DailyFormationRepository dailyFormationRepository;
    private final DayRepository dayRepository;
    private final PlayerRepository playerRepository;
    private final FantaTeamRepository fantaTeamRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public DailyFormationServiceImpl(DayRepository dayRepository,
                                     DailyFormationRepository dailyFormationRepository,
                                     FantaTeamRepository fantaTeamRepository, PlayerRepository playerRepository, RatingRepository ratingRepository) {
        this.dayRepository = dayRepository;
        this.dailyFormationRepository = dailyFormationRepository;
        this.fantaTeamRepository = fantaTeamRepository;
        this.playerRepository = playerRepository;
        this.ratingRepository = ratingRepository;
    }

    public Float calculateForLastDayAndFantaTeam(FantaTeam fantaTeam) {
        return calculateForDayAndFantaTeam(dayRepository.findMaxByNumeroGiornata(), fantaTeam);
    }

    @Override
    public Float calculateForDayAndFantaTeam(Day day, FantaTeam fantaTeam) {
        final Float[] sum = {0.0F};
        DailyFormation dailyFormation = dailyFormationRepository.findByFantaSquadraAndGiornata(fantaTeam, day);
        dailyFormation.getFormazione()
                .forEach(rating -> sum[0] += rating.getVoto());
        dailyFormation.setPunti(sum[0]);
        dailyFormationRepository.save(dailyFormation);
        logger.info("Team {} have made {} points in day {}", fantaTeam.getName(), sum[0], day.getNumeroGiornata());
        return sum[0];
    }

    @Override
    public DailyFormation getFormationForFantaTeamAndDate(String fantasquadra,
                                                          Integer giornata) {
        return dailyFormationRepository.findByFantaSquadraAndGiornata(
                fantaTeamRepository.findByName(fantasquadra),
                dayRepository.findOne(giornata));
    }

    @Override
    public DailyFormation addPlayerToDailyFormation(String fantaTeamName, Integer dayNumber, String name, String surname) throws FantaException {
        FantaTeam fantaTeam = fantaTeamRepository.findByName(fantaTeamName);
        Day day = dayRepository.findOne(dayNumber);
        DailyFormation dailyFormation = dailyFormationRepository.findByFantaSquadraAndGiornata(
                fantaTeam,
                day);
        if (!dailyFormation.getGiornata().isInseribile()) {
            throw new FantaException("INS",
                    String.format("Il tempo per inserire la formazione per la giornata %s è scaduto", dayNumber));
        }
        Player player = playerRepository.getByNameAndSurname(name, surname);
        if (!fantaTeam.getRoster().contains(player)) {
            throw new FantaException("INS",
                    String.format("Il giocatore %s %s non appartiene alla squadra %s", name, surname, fantaTeamName));
        }
        List<Player> sameRolePlayers = dailyFormation
                .getFormazione()
                .stream()
                .filter(rating -> rating.getGiocatore().getFormationRole().equals(player.getFormationRole()))
                .map(Rating::getGiocatore)
                .collect(Collectors.toList());
        if (sameRolePlayers.contains(player)) {
            throw new FantaException("INS", String.format("Il giocatore %s %s è stato già inserito", name, surname));
        }
        logger.info("sameRolePlayers {}", sameRolePlayers);
        logger.info("dailyFormation {}", dailyFormation);
        logger.info("dailyFormation.getFormationModule() {}", dailyFormation.getFormationModule());
        logger.info("player {}", player);
        if (sameRolePlayers.size() >= dailyFormation.getFormationModule().getNumberForRole(player.getFormationRole())) {
            throw new FantaException("INS",
                    String.format("Sono stati inseriti troppi giocatori per il ruolo %s",
                            player.getFormationRole().getExtended()));
        }

        List<Rating> formation = dailyFormation.getFormazione();

        Rating rating = ratingRepository.findByGiornataAndGiocatore(day, player);
        if (rating == null) {
            rating = new Rating();
            rating.setGiornata(day);
            rating.setGiocatore(player);
            ratingRepository.save(rating);
        }

        formation.add(rating);
        dailyFormation.setFormazione(formation);

        return dailyFormationRepository.save(dailyFormation);
    }

    @Override
    public DailyFormation removePlayerToDailyFormation(String fantaTeamName, Integer dayNumber, String name, String surname) {
        DailyFormation dailyFormation = dailyFormationRepository.findByFantaSquadraAndGiornata(
                fantaTeamRepository.findByName(fantaTeamName),
                dayRepository.findOne(dayNumber));

        Player player = playerRepository.getByNameAndSurname(name, surname);
        Rating rating = dailyFormation
                .getFormazione()
                .stream()
                .filter(innerRating -> innerRating.getGiocatore().equals(player))
                .findFirst().orElseGet(null);
        if (rating != null) {
            dailyFormation.getFormazione().remove(rating);
            return dailyFormationRepository.save(dailyFormation);
        } else {
            logger.warn("Il giocatore {} {} non è presente nella formazione", player.getName(), player.getSurname());
        }
        return dailyFormation;
    }

    @Override
    public void setDailyFormationModule(String fantaTeam, Integer day, String stringModule) throws FantaException {
        DailyFormation dailyFormation = dailyFormationRepository.findByFantaSquadraAndGiornata(
                fantaTeamRepository.findByName(fantaTeam),
                dayRepository.findOne(day));
        if (!dailyFormation.getGiornata().isInseribile()) {
            throw new FantaException("INS", "Non è più possibile modificare la formazione");
        }
        dailyFormation.setFormationModule(FormationModuleEnum.valueOf(stringModule));
        dailyFormationRepository.save(dailyFormation);
    }
}
