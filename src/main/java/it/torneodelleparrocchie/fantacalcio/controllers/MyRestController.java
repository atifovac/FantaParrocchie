package it.torneodelleparrocchie.fantacalcio.controllers;
/**
 * Created by dsalvatore on 17/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.enums.FormationModuleEnum;
import it.torneodelleparrocchie.fantacalcio.enums.RealTeamEnum;
import it.torneodelleparrocchie.fantacalcio.exceptions.FantaException;
import it.torneodelleparrocchie.fantacalcio.services.DailyFormationService;
import it.torneodelleparrocchie.fantacalcio.services.DayService;
import it.torneodelleparrocchie.fantacalcio.services.FantaTeamService;
import it.torneodelleparrocchie.fantacalcio.services.UserService;
import it.torneodelleparrocchie.fantacalcio.services.impl.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
@Deprecated
public class MyRestController {
    private Logger logger = LoggerFactory.getLogger(MyRestController.class);

    private final HelloServiceImpl service;

    private final PasswordEncoder encoder;

    private final UserService userService;

    private final FantaTeamService fantaTeamService;

    private DayService dayService;

    private DailyFormationService dailyFormationService;

    @Autowired
    public MyRestController(HelloServiceImpl service, PasswordEncoder encoder, UserService userService, FantaTeamService fantaTeamService, DayService dayService, DailyFormationService dailyFormationService) {
        this.service = service;
        this.encoder = encoder;
        this.userService = userService;
        this.fantaTeamService = fantaTeamService;
        this.dayService = dayService;
        this.dailyFormationService = dailyFormationService;
    }

    @GetMapping
    public String helloRestWorld(){
        return service.hello();
    }

    @PostMapping("/rtv")
    public RealTeamEnum getRealTeamValue(@RequestParam(name = "realTeam") String realTeam) {
        logger.info("getRealTeamValue called with realTeam={}", realTeam);
        return RealTeamEnum.valueOf(realTeam);
    }

    @PostMapping("/enc")
    public String encode(@RequestParam(name = "stringa") String s) {
        String first = encoder.encode(s);
        String second = encoder.encode(s);
        return ("first: " + first + " -> " + encoder.matches(s, first) + "\n") +
                "second: " + second + " -> " + encoder.matches(s, second);
    }

    @PostMapping("/init")
    public void init() throws FantaException {
        logger.info("Creating user");
        userService.newUser("root", "password", "root@email.it");
        logger.info("Creating FantaTeam");
        fantaTeamService.saveFantaTeam(null, "Celsi", "root", 125);
        logger.info("Adding players");
        fantaTeamService.addPlayer("Celsi", "Lorenzo", "MEMEO");
        fantaTeamService.addPlayer("Celsi", "Antonio", "D'AMORE");
        fantaTeamService.addPlayer("Celsi", "Antonio", "PAOLITTO");
        fantaTeamService.addPlayer("Celsi", "Daniele", "SALVATORE");
        fantaTeamService.addPlayer("Celsi", "Marco", "D'ABRAMO");
        fantaTeamService.addPlayer("Celsi", "Marco", "MASTROPAOLO");
        fantaTeamService.addPlayer("Celsi", "Fabio", "MINCHILLO");
        fantaTeamService.addPlayer("Celsi", "Gennaro", "CAPRIOLI");
        fantaTeamService.addPlayer("Celsi", "Luca", "CLISSA");
        fantaTeamService.addPlayer("Celsi", "Davide", "VERLENGIA");
        fantaTeamService.addPlayer("Celsi", "Pierluigi", "IZZI");
        fantaTeamService.addPlayer("Celsi", "Andrea", "LIGUORI");
        fantaTeamService.addPlayer("Celsi", "Godspower", "ABUYA");
        logger.info("Adding new day");
        dayService.addNextDay();
        logger.info("Adding module");
        dailyFormationService.setDailyFormationModule("Celsi", 1, FormationModuleEnum.F_2_3_1.name());
        logger.info("Creating formation");
        dailyFormationService.addPlayerToDailyFormation("Celsi", 1, "Lorenzo", "MEMEO");
        dailyFormationService.addPlayerToDailyFormation("Celsi", 1, "Antonio", "D'AMORE");
        dailyFormationService.addPlayerToDailyFormation("Celsi", 1, "Antonio", "PAOLITTO");
        dailyFormationService.addPlayerToDailyFormation("Celsi", 1, "Fabio", "MINCHILLO");
        dailyFormationService.addPlayerToDailyFormation("Celsi", 1, "Gennaro", "CAPRIOLI");
        dailyFormationService.addPlayerToDailyFormation("Celsi", 1, "Luca", "CLISSA");
        dailyFormationService.addPlayerToDailyFormation("Celsi", 1, "Pierluigi", "IZZI");
        logger.info("end");
    }
}
