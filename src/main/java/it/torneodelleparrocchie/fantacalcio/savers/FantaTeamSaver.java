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
import org.springframework.transaction.annotation.Transactional;

@Deprecated
public class FantaTeamSaver {

    private final Logger logger = LoggerFactory.getLogger(FantaTeamSaver.class);

    private FantaTeam fantaTeam;
    private String name;
    private String presidentName;
    private Integer fantaMoney;

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
        this.name = name;
        return this;
    }

    public FantaTeamSaver president(String presidentName) {
        this.presidentName = presidentName;
        return this;
    }

    public FantaTeamSaver fantaMoney(int fantaMoney) {
        this.fantaMoney = fantaMoney;
        return this;
    }

    @Transactional
    public FantaTeam save() {
        if (fantaMoney != null) {
            fantaTeam.setFantaMoney(fantaMoney);
        }

        if (presidentName != null) {
            User president = userRepository.findByUsername(presidentName);
            if (president == null) {
                throw new IllegalArgumentException(String.format("Can't find user with username %s", presidentName));
            }
            fantaTeam.setPresident(president);
        }

        if (name != null) {
            if (fantaTeam.getName() != null) {
                fantaTeamRepository.delete(fantaTeam.getName());
            }
            fantaTeam.setName(name);
        }

        if (fantaTeam.getName() == null || fantaTeamRepository.findOne(fantaTeam.getName()) == null) {
            logger.info(String.format("Can't find fantaTeam with name %s, a new fantaTeam will be created",
                    fantaTeam.getName()));
        }

        return fantaTeamRepository.save(fantaTeam);
    }
}
