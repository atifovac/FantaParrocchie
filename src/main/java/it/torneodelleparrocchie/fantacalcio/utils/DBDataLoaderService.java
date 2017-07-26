package it.torneodelleparrocchie.fantacalcio.utils;

import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.enums.RealTeamEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RosterRoleEnum;
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
public class DBDataLoaderService {
    private Logger logger = LoggerFactory.getLogger(DBDataLoaderService.class);

    @Autowired
    private PlayerService playerService;

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
            player.setFormationRole(values.get(params.indexOf("Ruolo Formazione")));
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
}
