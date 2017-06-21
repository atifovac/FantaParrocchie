package it.torneodelleparrocchie.fantacalcio.integration;

import it.torneodelleparrocchie.fantacalcio.FantacalcioApplication;
import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.entities.User;
import it.torneodelleparrocchie.fantacalcio.repositories.FantaTeamRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.PlayerRepository;
import it.torneodelleparrocchie.fantacalcio.repositories.UserRepository;
import it.torneodelleparrocchie.fantacalcio.savers.FantaTeamSaver;
import it.torneodelleparrocchie.fantacalcio.savers.PlayerSaver;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by dsalvatore on 21/06/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FantacalcioApplication.class)
@EnableConfigurationProperties
public class SaversIT {

    @Autowired
    private FantaTeamRepository fantaTeamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void fantaTeamSaverTest() throws Exception {
        User user = new User();
        user.setUsername("UsernameTest");
        user.setPassword("PasswordTest");
        user = userRepository.save(user);

        FantaTeam oldTeam = FantaTeamSaver.create(null, fantaTeamRepository, userRepository)
                .name("OldNameTest")
                .president(user.getUsername())
                .fantaMoney(0L)
                .save();

        FantaTeam newTeam = FantaTeamSaver.create(oldTeam.getName(), fantaTeamRepository, userRepository)
                .name("NameTest")
                .save();

        assertThat("Error while deleting old FantaTeam",
                fantaTeamRepository.findOne("OldNameTest"),
                is(nullValue()));
        assertThat("Error while updating FantaTeam's id",
                newTeam.getName(),
                is("NameTest"));
        assertThat("Error while updating FantaTeam",
                newTeam.getPresident().getUsername(),
                is(user.getUsername()));
    }

    @Test
    public void PlayerSaverTest() throws Exception {

        User user = new User();
        user.setUsername("UsernameTest");
        user.setPassword("PasswordTest");
        user = userRepository.save(user);

        FantaTeam oldTeam = FantaTeamSaver.create(null, fantaTeamRepository, userRepository)
                .name("OldNameTest")
                .president(user.getUsername())
                .fantaMoney(0L)
                .save();

        Player player = PlayerSaver.create(null, fantaTeamRepository, playerRepository)
                .name("TestName")
                .surname("TestSurname")
                .role("TestRole")
                .realTeam("TestRealTeam")
                .fantaTeam(oldTeam.getName())
                .save();

        player = PlayerSaver.create(player.getId(), fantaTeamRepository, playerRepository)
                .role("Defender")
                .realTeam("Carmelo")
                .save();

        assertThat("Error while updating player",
                player.getName(),
                is("TestName"));
        assertThat("Error while updating player",
                player.getRole(),
                is("Defender"));
    }

}
