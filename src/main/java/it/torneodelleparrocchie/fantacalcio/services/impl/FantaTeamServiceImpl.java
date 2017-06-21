package it.torneodelleparrocchie.fantacalcio.services.impl;
/**
 * Created by dsalvatore on 20/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.PlayerRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.UserRepository;
import it.torneodelleparrocchie.fantacalcio.savers.FantaTeamSaver;
import it.torneodelleparrocchie.fantacalcio.services.FantaTeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FantaTeamServiceImpl implements FantaTeamService {
    private Logger logger = LoggerFactory.getLogger(FantaTeamServiceImpl.class);

    private final FantaTeamRepository fantaTeamRepository;

    private final PlayerRepository playerRepository;

    private final UserRepository userRepository;

    @Autowired
    public FantaTeamServiceImpl(FantaTeamRepository fantaTeamRepository, PlayerRepository playerRepository, UserRepository userRepository) {
        this.fantaTeamRepository = fantaTeamRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
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
    public FantaTeam saveFantaTeam(String oldName, String name, String presidentUsername, Long fantaMoney) {
        return FantaTeamSaver.create(oldName, fantaTeamRepository, userRepository)
                .name(name)
                .president(presidentUsername)
                .fantaMoney(fantaMoney)
                .save();
    }

    @Override
    public void deleteFantaTeam(String name) {
        logger.info("saveFantaTeam, removing dependencies");
        playerRepository.getAllByFantaTeamName(name).forEach(player -> player.setFantaTeam(new FantaTeam()));
        logger.info("saveFantaTeam, deleting FantaTeam " + name);
        fantaTeamRepository.delete(name);
    }
}
