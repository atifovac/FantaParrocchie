package it.torneodelleparrocchie.fantacalcio.controllers;

import it.torneodelleparrocchie.fantacalcio.utils.DBDataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/loadData/fromFile")
public class DBDataLoaderController {

    private final DBDataLoaderService dbDataLoaderService;

    @Autowired
    public DBDataLoaderController(DBDataLoaderService dbDataLoaderService) {
        this.dbDataLoaderService = dbDataLoaderService;
    }

    @PostMapping(value = "/player")
    public String loadPlayersFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        return String.format("Inseriti %s giocatori", dbDataLoaderService.uploadPlayersFromFile(file));
    }

    @PostMapping(value = "/rating")
    public String loadRatingFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        return String.format("Inseriti %s voti", dbDataLoaderService.uploadRatingFromFile(file));
    }

    @PostMapping(value = "/rating/{giornata}")
    public String loadRatingFromFile(@PathVariable("giornata") Integer day,
                                     @RequestParam("file") MultipartFile file) throws IOException {
        return String.format("Inseriti %s voti", dbDataLoaderService.uploadRatingFromFile(file, day));
    }
}
