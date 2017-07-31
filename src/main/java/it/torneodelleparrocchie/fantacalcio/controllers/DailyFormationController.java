package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.entities.DailyFormation;
import it.torneodelleparrocchie.fantacalcio.exceptions.FantaException;
import it.torneodelleparrocchie.fantacalcio.services.DailyFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/formation")
public class DailyFormationController {
    private final DailyFormationService dailyFormationService;

    @Autowired
    public DailyFormationController(DailyFormationService dailyFormationService) {
        this.dailyFormationService = dailyFormationService;
    }

    @GetMapping("/{fantasquadra}/{giornata}")
    public DailyFormation getFormationForFantaTeamAndDate(@PathVariable("fantasquadra") String fantaTeam,
                                                          @PathVariable("giornata") Integer day) {
        return dailyFormationService.getFormationForFantaTeamAndDate(fantaTeam, day);
    }

    @PostMapping("/{fantasquadra}/{giornata}/add_player")
    public DailyFormation addPlayerToDailyFormation(@PathVariable("fantasquadra") String fantaTeam,
                                                    @PathVariable("giornata") Integer day,
                                                    @RequestParam("nome") String name,
                                                    @RequestParam("cognome") String surname) throws FantaException {
        return dailyFormationService.addPlayerToDailyFormation(fantaTeam, day, name, surname);
    }

    @PostMapping("/{fantasquadra}/{giornata}/remove_player")
    public DailyFormation removePlayerToDailyFormation(@PathVariable("fantasquadra") String fantaTeam,
                                                       @PathVariable("giornata") Integer day,
                                                       @RequestParam("nome") String name,
                                                       @RequestParam("cognome") String surname) throws FantaException {
        return dailyFormationService.removePlayerToDailyFormation(fantaTeam, day, name, surname);
    }

    @PostMapping("/{fantasquadra}/{giornata}")
    public void setDailyFormationModule(@PathVariable("fantasquadra") String fantaTeam,
                                        @PathVariable("giornata") Integer day,
                                        @RequestParam("modulo") String stringModule) throws FantaException {
        dailyFormationService.setDailyFormationModule(fantaTeam, day, stringModule);
    }
}
