package it.torneodelleparrocchie.fantacalcio.services.impl;
/**
 * Created by dsalvatore on 20/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.entities.User;
import it.torneodelleparrocchie.fantacalcio.exceptions.FantaException;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.PlayerRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.UserRepository;
import it.torneodelleparrocchie.fantacalcio.services.FantaTeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FantaTeamServiceImpl implements FantaTeamService {
    private Logger logger = LoggerFactory.getLogger(FantaTeamServiceImpl.class);

    private final FantaTeamRepository fantaTeamRepository;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public FantaTeamServiceImpl(FantaTeamRepository fantaTeamRepository, UserRepository userRepository, PlayerRepository playerRepository) {
        this.fantaTeamRepository = fantaTeamRepository;
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public FantaTeam getFantaTeam(String name) {
        FantaTeam fantaTeam = fantaTeamRepository.findOne(name);

        try {
            logger.info("getFantaTeam, result" + fantaTeam.getName());
        } catch (NullPointerException npe) {
            logger.warn("getFantaTeam, no FantaTeam found with name " + name);
        }

        return fantaTeam;
    }

    @Override
    public List<FantaTeam> getFantaTeamList() {
        List<FantaTeam> fantaTeamList = (List<FantaTeam>) fantaTeamRepository.findAll();

        logger.info("getFantaTeamList, result size" + fantaTeamList.size());

        return fantaTeamList;
    }

    @Override
    public FantaTeam saveFantaTeam(String oldName, String name, String presidentUsername, int fantaMoney) throws FantaException {
        FantaTeam savedFantaTeam;
        boolean newTeam = false;

        if (oldName == null) {
            savedFantaTeam = new FantaTeam();
            newTeam = true;
        } else {
            savedFantaTeam = fantaTeamRepository.findOne(oldName);
        }

        if (name.trim().length() > 0) {
            if (fantaTeamRepository.findOne(name) != null) {
                throw new FantaException("SAV", String.format("Esiste già una squadra chiamata %s", name));
            } else {
                savedFantaTeam.setName(name);

            }
        }

        if (presidentUsername.trim().length() > 0) {
            logger.info("Presidente: {}", presidentUsername);
            User president = userRepository.findByUsername(presidentUsername.trim());
            if (president == null) {
                throw new FantaException("SAV",
                        String.format("Non esiste un utente con username %s", presidentUsername));
            }

            if (fantaTeamRepository.findByPresident(president) != null) {
                throw new FantaException("SAV", String.format("L'utente %s ha già una squadra", presidentUsername));
            }

            savedFantaTeam.setPresident(president);
        }

        if (newTeam) {
            savedFantaTeam.setFantaMoney(125);
        } else if (fantaMoney >= 0) {
            savedFantaTeam.setFantaMoney(fantaMoney);
        } else {
            throw new FantaException("SAV", "Non è possibile andare in negativo con i crediti");
        }

        return fantaTeamRepository.save(savedFantaTeam);
    }

    @Override
    public void deleteFantaTeam(String name) {
        logger.info("saveFantaTeam, deleting FantaTeam " + name);
        fantaTeamRepository.delete(name);
    }

    @Override
    public void addPlayer(String teamName, String name, String surname) throws FantaException {
        logger.info("Trying to assign {} {} to team {}", name, surname, teamName);
        Player player = playerRepository.getByNameAndSurname(name, surname);
        if (player == null) {
            throw new FantaException("GET", String.format("Il giocatore %s %s non esiste", name, surname));
        }
        logger.info("Player found");
        FantaTeam fantaTeam = fantaTeamRepository.findByName(teamName);
        logger.info("Team found");
        if (fantaTeam != null) {
            List<Player> rosterByRole = fantaTeam.getRoster()
                    .stream()
                    .filter(player1 ->
                            player1.getRosterRole().getExtended().equals(player.getRosterRole().getExtended()))
                    .collect(Collectors.toList());
            logger.info(
                    String.format("Team %s have %s players for role %s",
                            teamName,
                            rosterByRole.size(),
                            player.getRosterRole().getExtended()));
            if (rosterByRole.size() < player.getRosterRole().getMaxInRoster()) {
                List<Player> roster = fantaTeam.getRoster();
                roster.add(player);
                fantaTeam.setRoster(roster);
                logger.info(String.format("Team's old money amount = %s", fantaTeam.getFantaMoney()));
                fantaTeam.setFantaMoney(fantaTeam.getFantaMoney() - player.getValue());
                logger.info("Team's new money amount = {}", fantaTeam.getFantaMoney());
                fantaTeamRepository.save(fantaTeam);
                logger.info("Player {} {} is added to team {}", name, surname, teamName);
            } else {
                throw new FantaException("SAV",
                        String.format("La squadra %s non ha più slot disponibili da %s",
                                fantaTeam.getName(),
                                player.getRosterRole().getExtended()));
            }
        } else {
            throw new FantaException("GET", String.format("La fantasquadra %s non esiste", teamName));
        }
    }

    @Override
    public void removePlayer(String teamName, String name, String surname) throws FantaException {
        logger.info("Trying to remove {} {} from team {}", name, surname, teamName);
        Player player = playerRepository.getByNameAndSurname(name, surname);
        if (player == null) {
            throw new FantaException("GET", String.format("Il giocatore %s %s non esiste", name, surname));
        }
        logger.info("Player found");
        FantaTeam fantaTeam = fantaTeamRepository.findByName(teamName);
        logger.info("Team found");
        if (fantaTeam != null) {
            logger.info(String.format("Team %s had %s player", teamName, fantaTeam.getRoster().size()));
            List<Player> newRoster = fantaTeam.getRoster()
                    .stream()
                    .filter(player1 ->
                            !player1.equals(player))
                    .collect(Collectors.toList());
            fantaTeam.setRoster(newRoster);
            logger.info("Now team {} have {} player", teamName, fantaTeam.getRoster().size());
            fantaTeam.setFantaMoney(fantaTeam.getFantaMoney() + player.getValue());
            logger.info("New team's money amount = {}", fantaTeam.getFantaMoney());
            fantaTeamRepository.save(fantaTeam);
            logger.info("Player {} {} is removed to team {}", name, surname, teamName);

        } else {
            throw new FantaException("GET", String.format("La fantasquadra %s non esiste", teamName));
        }
    }

    @Override
    public void closeMarket() {
        fantaTeamRepository.findAll().forEach(fantaTeam -> fantaTeam.setWritable(false));
    }
}
