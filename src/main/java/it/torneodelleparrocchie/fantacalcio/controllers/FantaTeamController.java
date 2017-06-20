package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.entities.FantaTeam;
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
    FantaTeamService service;

    @GetMapping("/{nome}")
    public FantaTeam getFantaTeam(@PathVariable("nome") String name) {
        return service.getFantaTeam(name);
    }

    @GetMapping("/")
    public List<FantaTeam> getFantaTeamList() {
        return service.getFantaTeamList();
    }

    @PutMapping("/")
    public FantaTeam saveFantaTeam(@RequestParam(value = "nome", required = false) String name,
                                   @RequestParam(value = "presidente", required = false) String president,
                                   @RequestParam(value = "fantasoldi", required = false) Long fantaMoney) {
        return service.saveFantaTeam(name, president, fantaMoney);
    }

    @DeleteMapping("/{nome}")
    public void saveFantaTeam(@PathVariable("nome") String name) {
        service.deleteFantaTeam(name);
    }
}
