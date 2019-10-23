package com.foxminded.rodin.formulaone;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public static Stream<String> readLogFile(String logFileName) throws IOException {

        if (logFileName == null || logFileName.isEmpty()) {
            throw new IllegalArgumentException("Path to the log file should not be empty");
        }

        try {
            String logFilePath = FastestRacersReport.class.getResource("/" + logFileName).getPath();
            Path path = Paths.get("/" + logFileName);
            Stream<String> streamLogFile = Files.lines(path);
            return streamLogFile;
        } catch (Exception error) {
            throw new FileNotFoundException("Cannot proccess the log file: " + logFileName + error.getMessage());
        }
    }


}
