package it.torneodelleparrocchie.fantacalcio.controllers;
/**
 * Created by dsalvatore on 17/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.enums.RealTeamEnum;
import it.torneodelleparrocchie.fantacalcio.services.impl.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
public class MyRestController {
    private Logger logger = LoggerFactory.getLogger(MyRestController.class);

    @Autowired
    private HelloServiceImpl service;

    @GetMapping
    public String helloRestWorld(){
        return service.hello();
    }

    @PostMapping("/rtv")
    public RealTeamEnum getRealTeamValue(@RequestParam(name = "realTeam") String realTeam) {
        logger.info(String.format("getRealTeamValue called with realTeam=%s", realTeam));
        return RealTeamEnum.valueOf(realTeam);
    }
}
