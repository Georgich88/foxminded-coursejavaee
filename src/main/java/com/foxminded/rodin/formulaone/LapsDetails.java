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
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.platform.commons.util.StringUtils;

public class LapsDetails {

    private static final String LOG_START_DEFAULT_NAME = "start.log";
    private static final String LOG_END_DEFAULT_NAME = "end.log";
    private static final String LOG_ABBREVIATION_DEFAULT_NAME = "abbreviations.txt";
    private static final String LOG_FIELDS_DELIMITER = "_";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";

    private static final int LOG_START_END_ABBREVICATION_POSITION = 3;

    private static final int LOG_ABBREVIATION_FIELDS_NUMBER = 3;

    private static final int ABBREVIATION_FIELD_INDEX = 0;
    private static final int RACER_NAME_FIELD_INDEX = 1;
    private static final int RACER_TEAM_NAME_FIELD_INDEX = 2;

    private List<String> startLogLines;
    private List<String> endLogLines;
    private List<String> abbreviationLogLines;

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
        if (StringUtils.isBlank(logFileName)) {
            throw new IllegalArgumentException("Path to the log file should not be empty");
        }
        try {
            Path path = new File(FastestRacersReportMaker.class.getResource("/" + logFileName).getFile()).toPath();
            return Files.lines(path).collect(Collectors.toList());
        } catch (Exception exception) {
            String exceptionMessage = "Cannot proccess the log file: " + logFileName + "; " + exception.getMessage();
            throw new IllegalArgumentException(exceptionMessage, exception);
        }
    }

    public List<Lap> getLaps() {

        List<Racer> racers = getRacers(abbreviationLogLines);

        Map<String, LocalDateTime> startsByAbbreviation = getTimeByAbbreviation(startLogLines);
        Map<String, LocalDateTime> endsByAbbreviation = getTimeByAbbreviation(endLogLines);

        List<Lap> laps = new ArrayList<Lap>(racers.size());

        racers.stream().forEach(racer -> {
            laps.add(createLap(racer, startsByAbbreviation, endsByAbbreviation));
        });

        Collections.sort(laps);

        return laps;
    }

    private Lap createLap(Racer racer, Map<String, LocalDateTime> startsByAbbreviation,
            Map<String, LocalDateTime> endsByAbbreviation) {

        LocalDateTime startTime = startsByAbbreviation.get(racer.getAbbreviation());
        LocalDateTime endTime = endsByAbbreviation.get(racer.getAbbreviation());

        Duration duration = Duration.between(startTime, endTime);

        Lap lap = new Lap(duration, racer);
        return lap;
    }

    private List<Racer> getRacers(List<String> abbreviationLogLines) {

        List<Racer> racers = new ArrayList<Racer>();

        Function<String, Racer> mapper = v -> {
            String[] racerData = v.split(LOG_FIELDS_DELIMITER);
            return new Racer(racerData[RACER_NAME_FIELD_INDEX], racerData[RACER_TEAM_NAME_FIELD_INDEX],
                    racerData[ABBREVIATION_FIELD_INDEX]);
        };

        racers = abbreviationLogLines.stream().map(mapper).collect(Collectors.toList());

        return racers;

    }

    private Map<String, LocalDateTime> getTimeByAbbreviation(List<String> durationParts) {

        Map<String, LocalDateTime> timeByAbbreviation = new HashMap<String, LocalDateTime>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        timeByAbbreviation = durationParts.stream()
                .collect(Collectors.toMap(line -> line.substring(0, LOG_START_END_ABBREVICATION_POSITION),
                        line -> LocalDateTime.parse(line.substring(LOG_START_END_ABBREVICATION_POSITION), formatter)));


        return timeByAbbreviation;

    }

}
