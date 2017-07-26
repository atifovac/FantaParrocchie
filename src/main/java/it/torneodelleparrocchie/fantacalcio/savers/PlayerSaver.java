package it.torneodelleparrocchie.fantacalcio.savers;

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.enums.RealTeamEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RosterRoleEnum;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dsalvatore on 21/06/17.
 * <p>
 * rischia di diventare obsoleto con le validazioni di hibernate
 */

@Deprecated
public class PlayerSaver {

    private final Logger logger = LoggerFactory.getLogger(PlayerSaver.class);

    private Player player;

    private FantaTeam fantaTeam;

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

    public PlayerSaver formationRole(String role) {
        if (role != null) {
            player.setFormationRole(role);
        }
        return this;
    }

    public PlayerSaver rosterRole(RosterRoleEnum role) {
        if (role != null) {
            player.setRosterRole(role);
        }
        return this;
    }

    public PlayerSaver realTeam(RealTeamEnum realTeam) {
        if (realTeam != null) {
            player.setRealTeam(realTeam);
        }
        return this;
    }

    //TODO: occhio che abbiamo spostato l'appartenenza alla fantasquadra a livello di fantasquadra
    public PlayerSaver fantaTeam(String fantaTeamName) {
        if (fantaTeamName != null) {
            FantaTeam tmpFantaTeam = fantaTeamRepository.findOne(fantaTeamName);
            if (tmpFantaTeam == null) {
                logger.warn(String.format("Can't find fantaTeam %s, the player will be released",
                        fantaTeamName));
                tmpFantaTeam = fantaTeamRepository.findOne("svincolato");
            }
            tmpFantaTeam.getRoster().add(player);
        }
        return this;
    }

    public Player save() {
        if (player.getId() == null || playerRepository.findOne(player.getId()) == null) {
            logger.info(String.format("Can't find player with id %s, a new player will be created", player.getId()));
            player.setId(null);
        }
        Player finalPlayer = playerRepository.save(player);
        return finalPlayer;
    }
}
