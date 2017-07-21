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

    private final PlayerRepository playerRepository;

    private final FantaTeamRepository fantaTeamRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, FantaTeamRepository fantaTeamRepository) {
        this.playerRepository = playerRepository;
        this.fantaTeamRepository = fantaTeamRepository;
    }

    @Override
    public Player getPlayer(Long id) {
        logger.info("Searching for player with id = " + id);
        return playerRepository.findOne(id);
    }

    @Override
    public List<Player> getPlayersList() {
        logger.info("Searching for all players");
        return (List<Player>) playerRepository.findAll();
    }

    @Override
    public Long savePlayer(Player player) {
        Long id = playerRepository.save(player).getId();
        logger.info("Inserting player with id = " + id);
        return id;
    }

    @Override
    public void deletePlayer(Long id) {
        logger.info("Deleting player with id = ", id);
        playerRepository.delete(id);
    }

    @Override
    public Player savePlayer(Long id, String name, String surname, String rosterRole, String formationRole, String realTeam, Long value) {
        Player player = new Player();
        player.setId(id);
        player.setName(name);
        player.setSurname(surname);
        player.setRosterRole(rosterRole);
        player.setFormationRole(formationRole);
        player.setRealTeam(realTeam);
        player.setValue(value);
        logger.info(String.format("saved player %s %s", name, surname));
        return playerRepository.save(player);
    }
}
