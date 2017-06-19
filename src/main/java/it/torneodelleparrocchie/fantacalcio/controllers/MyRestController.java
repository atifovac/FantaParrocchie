package it.torneodelleparrocchie.fantacalcio.controllers;
/**
 * Created by dsalvatore on 17/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.services.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MyRestController {
    private Logger logger = LoggerFactory.getLogger(MyRestController.class);

    private final HelloService service;

    @Autowired
    public MyRestController(HelloService service) {
        this.service = service;
    }

    @GetMapping("/ciao")
    public String HelloRestWorld(){
        return service.hello();
    }
}
