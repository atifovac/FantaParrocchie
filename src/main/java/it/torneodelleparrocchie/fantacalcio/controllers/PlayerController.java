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

    @PutMapping("/")
    public Player savePlayer(@RequestParam(name = "nome") String name, @RequestParam(name = "cognome") String surname,
                             @RequestParam(name = "squadraReale") String realTeam,
                             @RequestParam(name = "nomeFantaSquadra", required = false) String fantaTeamName) {
        return service.savePlayer(name, surname, realTeam, fantaTeamName);
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
