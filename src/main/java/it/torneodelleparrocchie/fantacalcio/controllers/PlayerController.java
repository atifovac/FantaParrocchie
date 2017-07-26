package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.enums.RealTeamEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RosterRoleEnum;
import it.torneodelleparrocchie.fantacalcio.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dsalvatore on 19/06/17.
 */

@RestController
@RequestMapping("/player")
public class PlayerController {
    private Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService service;

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        return service.getPlayer(id);
    }

    @PostMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id,
                               @RequestParam(name = "nome") String name,
                               @RequestParam(name = "cognome") String surname,
                               @RequestParam(name = "ruoloRosa") String rosterRole,
                               @RequestParam(name = "ruoloFormazione") String formationRole,
                               @RequestParam(name = "squadraReale") String realTeam,
                               @RequestParam(name = "quotazione") Integer value) {
        logger.info(rosterRole.trim().toUpperCase().substring(0, 2));
        return service.savePlayer(
                id,
                name,
                surname,
                RosterRoleEnum.valueOf(rosterRole.trim().toUpperCase().substring(0, 2)),
                formationRole,
                RealTeamEnum.valueOf(realTeam.trim().toUpperCase().replace(" ", "_")),
                value);
    }

    @PutMapping("/")
    public Player savePlayer(@RequestParam(name = "nome") String name,
                             @RequestParam(name = "cognome") String surname,
                             @RequestParam(name = "ruoloRosa") String rosterRole,
                             @RequestParam(name = "ruoloFormazione") String formationRole,
                             @RequestParam(name = "squadraReale") String realTeam,
                             @RequestParam(name = "quotazione") Integer value) {
        logger.info(rosterRole.trim().toUpperCase().substring(0, 2));
        return service.savePlayer(
                null,
                name,
                surname,
                RosterRoleEnum.valueOf(rosterRole.trim().toUpperCase().substring(0, 2)),
                formationRole,
                RealTeamEnum.valueOf(realTeam.trim().toUpperCase().replace(" ", "_")),
                value);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        service.deletePlayer(id);
    }

    @GetMapping("/")
    public List<Player> getPlayersList() {
        return service.getPlayersList();
    }
}
