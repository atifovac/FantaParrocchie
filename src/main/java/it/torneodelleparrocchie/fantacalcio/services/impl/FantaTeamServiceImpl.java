package it.torneodelleparrocchie.fantacalcio.services.impl;
/**
 * Created by dsalvatore on 20/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.PlayerRepository;
import it.torneodelleparrocchie.fantacalcio.services.FantaTeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FantaTeamServiceImpl implements FantaTeamService {
    private Logger logger = LoggerFactory.getLogger(FantaTeamServiceImpl.class);

    @Autowired
    private FantaTeamRepository repository;

    @Autowired
    private PlayerRepository playerRepository;

    public FantaTeam getFantaTeam(String name) {
        FantaTeam fantaTeam = repository.findOne(name);

        try {
            logger.info("getFantaTeam, result" + fantaTeam.getName());
        } catch (NullPointerException npe) {
            logger.warn("getFantaTeam, no FantaTeam found with name " + name);
        }

        return fantaTeam;
    }

    public List<FantaTeam> getFantaTeamList() {
        List<FantaTeam> fantaTeamList = (List<FantaTeam>) repository.findAll();

        logger.info("getFantaTeamList, result size" + fantaTeamList.size());

        return fantaTeamList;
    }

    public FantaTeam saveFantaTeam(String name, String president, Long fantaMoney) {
        FantaTeam fantaTeam = new FantaTeam();
        if (name != null && !name.equals("")) {
            fantaTeam.setName(name);
        }
        fantaTeam.setPresident(president);
        fantaTeam.setFantaMoney(fantaMoney);
        logger.info("saveFantaTeam, " + fantaTeam.toString());
        return repository.save(fantaTeam);
    }

    public void deleteFantaTeam(String name) {
        logger.info("deleteFantaTeam, removing dependencies");
        playerRepository.getAllByFantaTeamName(name).forEach(player -> player.setFantaTeam(new FantaTeam()));
        logger.info("deleteFantaTeam, deleting FantaTeam " + name);
        repository.delete(name);
    }
}
