package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
import it.torneodelleparrocchie.fantacalcio.exceptions.FantaException;
import it.torneodelleparrocchie.fantacalcio.services.FantaTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dsalvatore on 20/06/17.
 */

@RestController
@RequestMapping("/fantasquadra")
public class FantaTeamController {

    @Autowired
    private FantaTeamService service;

    @GetMapping("/{nome}")
    public FantaTeam getFantaTeam(@PathVariable("nome") String name) {
        return service.getFantaTeam(name);
    }

    @GetMapping("/")
    public List<FantaTeam> getFantaTeamList() {
        return service.getFantaTeamList();
    }

    @PutMapping("/")
    public FantaTeam newFantaTeam(@RequestParam(value = "nome") String name,
                                  @RequestParam(value = "presidente") String president,
                                  @RequestParam(value = "fantasoldi", required = false) int fantaMoney)
            throws FantaException {
        return service.saveFantaTeam(null, name, president, fantaMoney);
    }

    @DeleteMapping("/{nome}")
    public void deleteFantaTeam(@PathVariable("nome") String name) {
        service.deleteFantaTeam(name);
    }

    @PostMapping("/{vecchioNome}")
    public FantaTeam updateFantaTeam(@PathVariable(value = "vecchioNome") String oldName,
                                     @RequestParam(value = "nome", required = false) String name,
                                     @RequestParam(value = "presidente", required = false) String president,
                                     @RequestParam(value = "fantasoldi", required = false) int fantaMoney)
            throws FantaException {
        return service.saveFantaTeam(oldName, name, president, fantaMoney);
    }

    @PostMapping("/{nomeSquadra}/add")
    public void addPlayer(@PathVariable(value = "nomeSquadra") String teamName,
                          @RequestParam(value = "nome") String name,
                          @RequestParam(value = "cognome") String surname) throws FantaException {
        service.addPlayer(teamName, name, surname);
    }

    @PostMapping("/{nomeSquadra}/remove")
    public void removePlayer(@PathVariable(value = "nomeSquadra") String teamName,
                             @RequestParam(value = "nome") String name,
                             @RequestParam(value = "cognome") String surname) throws FantaException {
        service.removePlayer(teamName, name, surname);
    }
}
