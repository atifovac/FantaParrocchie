package it.torneodelleparrocchie.fantacalcio.services.impl;

import it.torneodelleparrocchie.fantacalcio.entities.DailyFormation;
import it.torneodelleparrocchie.fantacalcio.entities.Day;
import it.torneodelleparrocchie.fantacalcio.entities.Rating;
import it.torneodelleparrocchie.fantacalcio.repositories.*;
import it.torneodelleparrocchie.fantacalcio.services.DailyFormationService;
import it.torneodelleparrocchie.fantacalcio.services.DayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayServiceImpl implements DayService {
    private Logger logger = LoggerFactory.getLogger(DayServiceImpl.class);

    private final DayRepository dayRepository;

    private final DailyFormationRepository dailyFormationRepository;

    private final FantaTeamRepository fantaTeamRepository;

    private final DailyFormationService dailyFormationService;

    private final PlayerRepository playerRepository;

    private final RatingRepository ratingRepository;

    @Autowired
    public DayServiceImpl(DayRepository dayRepository, DailyFormationRepository dailyFormationRepository, FantaTeamRepository fantaTeamRepository, DailyFormationService dailyFormationService, PlayerRepository playerRepository, RatingRepository ratingRepository) {
        this.dayRepository = dayRepository;
        this.dailyFormationRepository = dailyFormationRepository;
        this.fantaTeamRepository = fantaTeamRepository;
        this.dailyFormationService = dailyFormationService;
        this.playerRepository = playerRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void addNextDay() {
        Day day = dayRepository.findMaxByNumeroGiornata();
        Day nextDay = new Day();
        nextDay.setNumeroGiornata((day == null) ? 1 : day.getNumeroGiornata() + 1);
        dayRepository.save(nextDay);
        fantaTeamRepository.findAll().forEach(fantaTeam -> {
            DailyFormation dailyFormation = new DailyFormation();
            dailyFormation.setFantaSquadra(fantaTeam);
            dailyFormation.setGiornata(nextDay);
            dailyFormationRepository.save(dailyFormation);
        });
        playerRepository
                .findAll()
                .forEach(player -> {
                    Rating rating = new Rating();
                    rating.setGiocatore(player);
                    rating.setGiornata(nextDay);
                    ratingRepository.save(rating);
                });
        logger.info("Day {} added", nextDay.getNumeroGiornata());
    }

    @Override
    public void calculate() {
        calculate(dayRepository.findMinNumeroGiornataCalcolabile());
    }

    @Override
    public void calculate(Integer day) {
        logger.info("Calculate for day {}", day);
        calculate(dayRepository.findOne(day));
    }

    @Override
    public void closeDay() {
        Day maxByNumeroGiornata = dayRepository.findMaxByNumeroGiornata();
        maxByNumeroGiornata.setInseribile(false);
        dayRepository.save(maxByNumeroGiornata);
        logger.info("Day {} closed", maxByNumeroGiornata.getNumeroGiornata());
    }

    private void calculate(Day day) {
        if (day == null) {
            logger.warn("No calculable days found");
            return;
        }
        logger.info("Calculating rating for day {}", day.getNumeroGiornata());
        fantaTeamRepository
                .findAll()
                .forEach(fantaTeam -> {
                    logger.info("Getting last inserted formation for team {}", fantaTeam.getName());

                    DailyFormation lastDailyFormation =
                            dailyFormationRepository.findLastByGiornataAndFantaSquadra(day, fantaTeam);

                    //TODO: chiedere se la formazione deve essere completa per essere valida o se basta un giocatore
                    if (lastDailyFormation.getFormazione().size() > 0) {
                        logger.info("Found inserted formation {}", lastDailyFormation.getFormazione());

                        if (day.getNumeroGiornata() != lastDailyFormation.getGiornata().getNumeroGiornata()) {
                            logger.warn(
                                    "The team {} have not inserted the formation for day {}. It will be taken the formation of day {}",
                                    fantaTeam.getName(),
                                    day.getNumeroGiornata(),
                                    lastDailyFormation.getGiornata().getNumeroGiornata());
                            DailyFormation dailyFormation = new DailyFormation();
                            dailyFormation.setGiornata(day);
                            dailyFormation.setFantaSquadra(lastDailyFormation.getFantaSquadra());
                            dailyFormation.setFormationModule(lastDailyFormation.getFormationModule());
                            dailyFormation.setFormazione(lastDailyFormation.getFormazione());
                            dailyFormationRepository.save(dailyFormation);
                        }

                    } else {
                        logger.warn(
                                "The team {} have never inserted a formation. It will be used an empty one for day {}",
                                fantaTeam.getName(),
                                day.getNumeroGiornata());
                    }

                    Float oldPoints = fantaTeam.getPoints();
                    Float dailyPoints = dailyFormationService
                            .calculateForDayAndFantaTeam(day, fantaTeam);
                    logger.info("Updating points for fantaTeam {} from {} to {}",
                            fantaTeam.getName(),
                            oldPoints,
                            oldPoints + dailyPoints);
                    fantaTeam.setPoints(oldPoints + dailyPoints);

                    fantaTeamRepository.save(fantaTeam);
                });

    }
}
