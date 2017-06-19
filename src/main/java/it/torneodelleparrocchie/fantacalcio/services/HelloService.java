package it.torneodelleparrocchie.fantacalcio.services;
/**
 * Created by dsalvatore on 17/06/17.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private Logger logger = LoggerFactory.getLogger(HelloService.class);

    public String hello(){
        String hello = "Hello, World!!";
        logger.info(hello);
        return hello;
    }
}
