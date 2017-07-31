package it.torneodelleparrocchie.fantacalcio.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DBDataLoaderService {
    int uploadPlayersFromFile(MultipartFile inputFile) throws IOException;

    int uploadRatingFromFile(MultipartFile file) throws IOException;

    int uploadRatingFromFile(MultipartFile inputFile, Integer day) throws IOException;
}
