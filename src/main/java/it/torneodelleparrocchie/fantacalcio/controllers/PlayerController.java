package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.entities.Player;
import it.torneodelleparrocchie.fantacalcio.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dsalvatore on 19/06/17.
 */

@RestController
@RequestMapping("/player")
public class PlayerController {

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
                               @RequestParam(name = "quotazione") Long value) {
        return service.savePlayer(id, name, surname, rosterRole, formationRole, realTeam, value);
    }

    @PutMapping("/")
    public Player savePlayer(@RequestParam(name = "nome") String name,
                             @RequestParam(name = "cognome") String surname,
                             @RequestParam(name = "ruoloRosa") String rosterRole,
                             @RequestParam(name = "ruoloFormazione") String formationRole,
                             @RequestParam(name = "squadraReale") String realTeam,
                             @RequestParam(name = "quotazione") Long value) {
        return service.savePlayer(null, name, surname, rosterRole, formationRole, realTeam, value);
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
