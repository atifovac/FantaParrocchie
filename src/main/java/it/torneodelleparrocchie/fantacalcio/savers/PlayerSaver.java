package it.torneodelleparrocchie.fantacalcio.savers;

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dsalvatore on 21/06/17.
 */

public class PlayerSaver {

    private final Logger logger = LoggerFactory.getLogger(PlayerSaver.class);

    private Player player;

    private final PlayerRepository playerRepository;

    private final FantaTeamRepository fantaTeamRepository;

    private PlayerSaver(Long playerId, FantaTeamRepository fantaTeamRepository, PlayerRepository playerRepository) {
        Player oldPlayer = (playerId == null ? new Player() : playerRepository.findOne(playerId));
        if (oldPlayer == null) {
            throw new IllegalArgumentException(String.format("Can't find player with id %s", playerId));
        }
        this.player = oldPlayer;
        this.playerRepository = playerRepository;
        this.fantaTeamRepository = fantaTeamRepository;
    }

    public static PlayerSaver create(Long oldPlayerId,
                                     FantaTeamRepository fantaTeamRepository,
                                     PlayerRepository playerRepository) {
        return new PlayerSaver(oldPlayerId, fantaTeamRepository, playerRepository);
    }

    public PlayerSaver name(String name) {
        if (name != null) {
            player.setName(name);
        }
        return this;
    }

    public PlayerSaver surname(String surname) {
        if (surname != null) {
            player.setSurname(surname);
        }
        return this;
    }

    public PlayerSaver role(String role) {
        if (role != null) {
            player.setRole(role);
        }
        return this;
    }

    public PlayerSaver realTeam(String realTeam) {
        if (realTeam != null) {
            player.setRealTeam(realTeam);
        }
        return this;
    }

    public PlayerSaver fantaTeam(String fantaTeamName) {
        if (fantaTeamName != null) {
            FantaTeam tmpFantaTeam = fantaTeamRepository.findOne(fantaTeamName);
            if (tmpFantaTeam == null) {
                logger.warn(String.format("Can't find fantaTeam %s, the player will be released",
                        fantaTeamName));
                tmpFantaTeam = fantaTeamRepository.findOne("svincolato");
            }
            player.setFantaTeam(tmpFantaTeam);
        }
        return this;
    }

    public Player save() {
        if (player.getId() == null || playerRepository.findOne(player.getId()) == null) {
            logger.info(String.format("Can't find player with id %s, a new player will be created", player.getId()));
        }
        return playerRepository.save(player);
    }
}
