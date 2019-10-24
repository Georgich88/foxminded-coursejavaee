package com.foxminded.rodin.formulaone;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LapsDetails {

    private static final String LOG_START_DEFAULT_NAME = "start.log";
    private static final String LOG_END_DEFAULT_NAME = "end.log";
    private static final String LOG_ABBREVIATION_DEFAULT_NAME = "abbreviations.txt";

    Stream<String> startLogStream;
    Stream<String> endLogStream;
    Stream<String> abbreviationLogStream;

    public LapsDetails() throws IOException {

        this.startLogStream = readLogFile(LOG_START_DEFAULT_NAME);
        this.endLogStream = readLogFile(LOG_END_DEFAULT_NAME);
        this.abbreviationLogStream = readLogFile(LOG_ABBREVIATION_DEFAULT_NAME);
    }

    public LapsDetails(String startLogFile, String endtLogFile, String abbreviationLogFile) throws IOException {

        this.startLogStream = readLogFile(startLogFile);
        this.endLogStream = readLogFile(endtLogFile);
        this.abbreviationLogStream = readLogFile(abbreviationLogFile);
    }

    public Stream<String> getStartLogStream() {
        return startLogStream;
    }

    public Stream<String> getEndLogStream() {
        return endLogStream;
    }

    public Stream<String> getAbbreviationLogStream() {
        return abbreviationLogStream;
    }

    private Stream<String> readLogFile(String logFileName) throws IOException {

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
