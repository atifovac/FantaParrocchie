package it.torneodelleparrocchie.fantacalcio.services.impl;
/**
 * Created by dsalvatore on 17/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.services.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{
    private Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    public String hello(){
        String hello = "Hello, World!!";
        logger.info(hello);
        return hello;
    }
}
