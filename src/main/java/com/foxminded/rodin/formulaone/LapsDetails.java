package com.foxminded.rodin.formulaone;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LapsDetails {

    private static final String LOG_START_DEFAULT_NAME = "start.log";
    private static final String LOG_END_DEFAULT_NAME = "end.log";
    private static final String LOG_ABBREVIATION_DEFAULT_NAME = "abbreviations.txt";
    private static final String LOG_FIELDS_DELIMITER = "_";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";

    List<String> startLogLines;
    List<String> endLogLines;
    List<String> abbreviationLogLines;

    public LapsDetails() {

        this.startLogLines = readLogFile(LOG_START_DEFAULT_NAME);
        this.endLogLines = readLogFile(LOG_END_DEFAULT_NAME);
        this.abbreviationLogLines = readLogFile(LOG_ABBREVIATION_DEFAULT_NAME);
    }

    public LapsDetails(String startLogFile, String endtLogFile, String abbreviationLogFile) {

        this.startLogLines = readLogFile(startLogFile);
        this.endLogLines = readLogFile(endtLogFile);
        this.abbreviationLogLines = readLogFile(abbreviationLogFile);
    }


    private List<String> readLogFile(String logFileName) {

        if (logFileName == null || logFileName.isEmpty()) {
            throw new IllegalArgumentException("Path to the log file should not be empty");
        }

        try {
            Path path = new File(FastestRacersReport.class.getResource("/" + logFileName).getFile()).toPath();
            Stream<String> streamLogFile = Files.lines(path);
            return streamLogFile.collect(Collectors.toList());
        } catch (Exception error) {
            throw new IllegalArgumentException("Cannot proccess the log file: " + logFileName + error.getMessage());
        }
    }

    public List<Lap> getLaps() {

        List<Racer> racers = getRacers(abbreviationLogLines);

        Map<String, LocalDateTime> startsByAbbreviation = getDateTimeByAbbreviation(startLogLines);
        Map<String, LocalDateTime> endsByAbbreviation = getDateTimeByAbbreviation(endLogLines);

        List<Lap> laps = new ArrayList<Lap>(racers.size());

        racers.forEach(racer -> {

            LocalDateTime startTime = startsByAbbreviation.get(racer.getAbbreviation());
            LocalDateTime endTime = endsByAbbreviation.get(racer.getAbbreviation());

            Duration duration = Duration.between(startTime, endTime);

            Lap lap = new Lap(duration, racer);
            laps.add(lap);

        });

        laps.sort(Comparator.comparing(lap -> lap.getDuration()));

        return laps;
    }

    private List<Racer> getRacers(List<String> abbreviationLogLines) {

        List<Racer> racers = new ArrayList<Racer>();

        abbreviationLogLines.forEach(v -> {
            String[] racerData = v.split(LOG_FIELDS_DELIMITER);
            if (racerData.length == 3) {
                racers.add(new Racer(racerData[1], racerData[2], racerData[0]));
            }
        });

        return racers;

    }

    private static Map<String, LocalDateTime> getDateTimeByAbbreviation(List<String> durationParts) {

        Map<String, LocalDateTime> dateTimeByAbbreviation = new HashMap<String, LocalDateTime>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        dateTimeByAbbreviation = durationParts.stream().collect(Collectors.toMap(line -> line.substring(0, 3),
                line -> LocalDateTime.parse(line.substring(3), formatter)));


        return dateTimeByAbbreviation;

    }

}
