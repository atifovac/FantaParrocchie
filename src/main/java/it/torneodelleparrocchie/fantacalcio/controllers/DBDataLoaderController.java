package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.utils.DBDataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/loadData")
public class DBDataLoaderController {

    @Autowired
    private DBDataLoaderService dbDataLoaderService;

    @PostMapping(value = "/fromFile/player")
    public String loadPlayersFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        return String.format("Inseriti %s giocatori", dbDataLoaderService.uploadPlayersFromFile(file));
    }
}
