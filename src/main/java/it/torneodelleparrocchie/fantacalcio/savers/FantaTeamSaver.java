package it.torneodelleparrocchie.fantacalcio.savers;
/**
 * Created by dsalvatore on 21/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.User;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FantaTeamSaver {

    private final Logger logger = LoggerFactory.getLogger(FantaTeamSaver.class);

    private FantaTeam fantaTeam;

    private FantaTeamRepository fantaTeamRepository;

    private UserRepository userRepository;

    private FantaTeamSaver(String fantaTeamName, FantaTeamRepository fantaTeamRepository, UserRepository userRepository) {
        this.fantaTeam = (fantaTeamName == null ? new FantaTeam() : fantaTeamRepository.findOne(fantaTeamName));
        this.fantaTeamRepository = fantaTeamRepository;
        this.userRepository = userRepository;
    }

    public static FantaTeamSaver create(String oldFantaTeamName,
                                        FantaTeamRepository fantaTeamRepository,
                                        UserRepository userRepository) {
        return new FantaTeamSaver(oldFantaTeamName, fantaTeamRepository, userRepository);
    }

    public FantaTeamSaver name(String name) {
        if (name != null) {
            if (fantaTeam.getName() != null) {
                fantaTeamRepository.delete(fantaTeam.getName());
            }
            fantaTeam.setName(name);
        }
        return this;
    }

    public FantaTeamSaver president(String presidentName) {
        if (presidentName != null) {
            User president = userRepository.findOne(presidentName);
            if (president == null) {
                throw new IllegalArgumentException(String.format("Can't find user with username %s", presidentName));
            }
            fantaTeam.setPresident(president);
        }
        return this;
    }

    public FantaTeamSaver fantaMoney(Long fantaMoney) {
        if (fantaMoney != null) {
            fantaTeam.setFantaMoney(fantaMoney);
        }
        return this;
    }

    public FantaTeam save() {
        if (fantaTeam.getName() == null || fantaTeamRepository.findOne(fantaTeam.getName()) == null) {
            logger.info(String.format("Can't find fantaTeam with name %s, a new fantaTeam will be created",
                    fantaTeam.getName()));
        }
        return fantaTeamRepository.save(fantaTeam);
    }
}
