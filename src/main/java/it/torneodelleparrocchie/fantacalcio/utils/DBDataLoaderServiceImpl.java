package it.torneodelleparrocchie.fantacalcio.utils;

import it.torneodelleparrocchie.fantacalcio.entities.Day;
import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.entities.Rating;
import it.torneodelleparrocchie.fantacalcio.enums.FormationRoleEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RealTeamEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RosterRoleEnum;
import it.torneodelleparrocchie.fantacalcio.repositories.DayRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.RatingRepository;
import it.torneodelleparrocchie.fantacalcio.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class DBDataLoaderServiceImpl implements DBDataLoaderService {
    private Logger logger = LoggerFactory.getLogger(DBDataLoaderServiceImpl.class);

    private final PlayerService playerService;

    private final DayRepository dayRepository;

    private final RatingRepository ratingRepository;

    @Autowired
    public DBDataLoaderServiceImpl(PlayerService playerService, DayRepository dayRepository, RatingRepository ratingRepository) {
        this.playerService = playerService;
        this.dayRepository = dayRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public int uploadPlayersFromFile(MultipartFile inputFile) throws IOException {
        int rowCounter = 0;
        logger.info("Processing file {}", inputFile.getOriginalFilename());
        Scanner scanner = new Scanner(inputFile.getInputStream());
        List<String> params = Arrays.asList(scanner.nextLine().split(","));
        while (scanner.hasNext()) {
            String row = scanner.nextLine();
            List<String> values = Arrays.asList(row.split(",", -1));
            Player player = new Player();
            player.setName(values.get(params.indexOf("Nome")));
            player.setSurname(values.get(params.indexOf("Cognome")));
            player.setFormationRole(FormationRoleEnum.valueOf(values.get(params.indexOf("Ruolo Formazione"))));
            player.setRosterRole(RosterRoleEnum.valueOf(values.get(params.indexOf("Ruolo Rosa"))));
            player.setRealTeam(RealTeamEnum.valueOf(values.get(params.indexOf("Squadra"))
                    .trim()
                    .toUpperCase()
                    .replace(" ", "_")));
            player.setValue(Integer.valueOf(values.get(params.indexOf("Quotazione"))));
            playerService.savePlayer(player);
            rowCounter += 1;
        }

        scanner.close();
        return rowCounter;
    }

    @Override
    public int uploadRatingFromFile(MultipartFile file) throws IOException {
        Integer day = dayRepository.findMinNumeroGiornataCalcolabile().getNumeroGiornata();
        return uploadRatingFromFile(file, day);
    }

    @Override
    public int uploadRatingFromFile(MultipartFile inputFile, Integer dayNumber) throws IOException {
        logger.info("Uploading rating from file for day {}", dayNumber);
        Day day = dayRepository.findOne(dayNumber);
        int rowCounter = 0;
        logger.info("Processing file {}", inputFile.getOriginalFilename());
        Scanner scanner = new Scanner(inputFile.getInputStream());
        List<String> params = Arrays.asList(scanner.nextLine().split(","));
        while (scanner.hasNext()) {
            String row = scanner.nextLine();
            List<String> values = Arrays.asList(row.split(",", -1));
            logger.info(values.toString());
            String name = values.get(params.indexOf("Nome"));
            String surname = values.get(params.indexOf("Cognome"));
            Rating rating = ratingRepository.findByGiornataAndGiocatore(
                    day,
                    playerService.getByNameAndSurname(name, surname));
            rating.setVoto(Float.valueOf(values.get(params.indexOf("Voto"))));
            ratingRepository.save(rating);
            rowCounter += 1;
        }
        scanner.close();
        day.setCalcolabile(true);
        dayRepository.save(day);
        return rowCounter;
    }
}
