package com.foxminded.rodin.formulaone;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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

    private static final int LOG_START_END_ABBREVICATION_POSITION = 3;

    private static final int LOG_ABBREVIATION_FIELDS_NUMBER = 3;

    private static final int LOG_ABBREVIATION_ABBREVIATION_FIELD_INDEX = 0;
    private static final int LOG_ABBREVIATION_RACER_NAME_FIELD_INDEX = 1;
    private static final int LOG_ABBREVIATION_RACER_TEAM_NAME_FIELD_INDEX = 2;



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
            Path path = new File(FastestRacersReportMaker.class.getResource("/" + logFileName).getFile()).toPath();
            Stream<String> streamLogFile = Files.lines(path);
            return streamLogFile.collect(Collectors.toList());
        } catch (Exception error) {
            throw new IllegalArgumentException("Cannot proccess the log file: " + logFileName + error.getMessage());
        }
    }

    public List<Lap> getLaps() {

        List<Racer> racers = getRacers(abbreviationLogLines);

        Map<String, LocalDateTime> startsByAbbreviation = getStartEndTimeByAbbreviation(startLogLines);
        Map<String, LocalDateTime> endsByAbbreviation = getStartEndTimeByAbbreviation(endLogLines);

        List<Lap> laps = new ArrayList<Lap>(racers.size());

        racers.forEach(racer -> {

            LocalDateTime startTime = startsByAbbreviation.get(racer.getAbbreviation());
            LocalDateTime endTime = endsByAbbreviation.get(racer.getAbbreviation());

            Duration duration = Duration.between(startTime, endTime);

            laps.add(createLap(racer, duration));

        });

        Collections.sort(laps);

        return laps;
    }

    private Lap createLap(Racer racer, Duration duration) {
        Lap lap = new Lap(duration, racer);
        return lap;
    }

    private List<Racer> getRacers(List<String> abbreviationLogLines) {

        List<Racer> racers = new ArrayList<Racer>();

        abbreviationLogLines.forEach(v -> {
            String[] racerData = v.split(LOG_FIELDS_DELIMITER);
            if (racerData.length == LOG_ABBREVIATION_FIELDS_NUMBER) {
                racers.add(new Racer(racerData[LOG_ABBREVIATION_RACER_NAME_FIELD_INDEX],
                        racerData[LOG_ABBREVIATION_RACER_TEAM_NAME_FIELD_INDEX],
                        racerData[LOG_ABBREVIATION_ABBREVIATION_FIELD_INDEX]));
            }
        });

        return racers;

    }

    private Map<String, LocalDateTime> getStartEndTimeByAbbreviation(List<String> durationParts) {

        Map<String, LocalDateTime> timeByAbbreviation = new HashMap<String, LocalDateTime>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        timeByAbbreviation = durationParts.stream()
                .collect(Collectors.toMap(line -> line.substring(0, LOG_START_END_ABBREVICATION_POSITION),
                        line -> LocalDateTime.parse(line.substring(LOG_START_END_ABBREVICATION_POSITION), formatter)));


        return timeByAbbreviation;

    }

}
