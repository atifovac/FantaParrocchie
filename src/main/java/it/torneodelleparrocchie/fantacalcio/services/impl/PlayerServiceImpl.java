package it.torneodelleparrocchie.fantacalcio.services.impl;
/**
 * Created by dsalvatore on 19/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.PlayerRepository;
import it.torneodelleparrocchie.fantacalcio.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{
    private Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FantaTeamRepository fantaTeamRepository;

    public Player getPlayer(Long id) {
        logger.info("Searching for player with id = " + id);
        return playerRepository.findOne(id);
    }

    public List<Player> getPlayersList() {
        logger.info("Searching for all players");
        return (List<Player>) playerRepository.findAll();
    }

    public Long savePlayer(Player player) {
        Long id = playerRepository.save(player).getId();
        logger.info("Inserting player with id = " + id);
        return id;
    }

    public void deletePlayer(Long id) {
        logger.info("Deleting player with id = ", id);
        playerRepository.delete(id);
    }

    public void deletePlayer(Player player) {
        logger.info("Deleting player with id = ", player.getId());
        playerRepository.delete(player);
    }

    public Player savePlayer(String name, String surname, String realTeam, String fantaTeamName) {
        Player player = new Player();
        player.setName(name);
        player.setSurname(surname);
        player.setRealTeam(realTeam);
        player.setFantaTeam(fantaTeamRepository.findOne(fantaTeamName));
        return playerRepository.save(player);
    }
}
