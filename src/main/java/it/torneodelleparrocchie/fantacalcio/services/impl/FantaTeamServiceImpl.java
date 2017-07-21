package it.torneodelleparrocchie.fantacalcio.services.impl;
/**
 * Created by dsalvatore on 20/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.User;
import it.torneodelleparrocchie.fantacalcio.exceptions.FantaException;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public FantaTeamServiceImpl(FantaTeamRepository fantaTeamRepository, UserRepository userRepository) {
        this.fantaTeamRepository = fantaTeamRepository;
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
    public FantaTeam saveFantaTeam(String oldName, String name, String presidentUsername, Long fantaMoney) throws FantaException {
        FantaTeam savedFantaTeam = fantaTeamRepository.findOne(oldName);
        boolean newTeam = false;

        if (savedFantaTeam == null) {
            savedFantaTeam = new FantaTeam();
            newTeam = true;
        }

        if (name.trim().length() > 0) {
            if (fantaTeamRepository.findOne(name) != null) {
                throw new FantaException("[SAV]", String.format("Esiste già una squadra chiamata %s", name));
            } else {
                savedFantaTeam.setName(name);

            }
        }

        if (presidentUsername.trim().length() > 0) {
            User president = userRepository.findOne(presidentUsername);
            if (president == null) {
                throw new FantaException("[SAV]",
                        String.format("Non esiste un utente con username %s", presidentUsername));
            }

            if (fantaTeamRepository.findByPresident(president) != null) {
                throw new FantaException("[SAV]", String.format("L'utente %s ha già una squadra", presidentUsername));
            }

            savedFantaTeam.setPresident(president);
        }

        if (newTeam) {
            savedFantaTeam.setFantaMoney(85L);
        } else if (fantaMoney >= 0) {
            savedFantaTeam.setFantaMoney(fantaMoney);
        } else {
            throw new FantaException("[SAV]", "Non è possibile andare in negativo con i crediti");
        }

        return fantaTeamRepository.save(savedFantaTeam);
    }

    @Override
    public void deleteFantaTeam(String name) {
        logger.info("saveFantaTeam, removing dependencies");
        logger.info("saveFantaTeam, deleting FantaTeam " + name);
        fantaTeamRepository.delete(name);
    }
}
