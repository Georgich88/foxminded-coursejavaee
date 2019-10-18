package com.foxminded.rodin.formulaone;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

public class FastestRacersReport {

    private static final String LOG_FIELDS_DELIMITER = "_";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd_HH:mm:ss.SSS";
    private static final String REPORT_LINE_FORMAT = "%2d.%-17s|%-25s|%d:%02d.%03d";
    private static final String TOP_RACERS_SEPARATOR = "-------------------------------------------------------";

    public static String getFastestRacersReport(String startLogFile, String endtLogFile, String abbreviationLogFile,
            int topLapsNumber) throws IOException {

        StringBuilder boardBuilder = new StringBuilder();

        List<Lap> laps = getFastesLaps(startLogFile, endtLogFile, abbreviationLogFile, topLapsNumber);
        Integer lineNumber = 1;

        for (Lap lap : laps) {

            Racer racer = lap.getRacer();
            Duration duration = lap.getDuration();
            String line = String.format(REPORT_LINE_FORMAT, lineNumber, racer.getName(), racer.getTeam(),
                    duration.toMinutes(), (duration.toMillis() % 60000) / 1000, duration.toMillis() % 1000);
            boardBuilder.append(line + "\n");

            if (lineNumber == topLapsNumber)
                boardBuilder.append(TOP_RACERS_SEPARATOR + "\n");

            lineNumber++;
        }


        return boardBuilder.toString();

    }

    private static List<Lap> getFastesLaps(String startLogFile, String endtLogFile, String abbreviationLogFile,
            int topLapsNumber)
            throws IOException {

        Stream<String> startLogStream = readLogFile(startLogFile);
        Stream<String> endLogStream = readLogFile(endtLogFile);
        Stream<String> abbreviationLogStream = readLogFile(abbreviationLogFile);

        List<Racer> racers = getRacers(abbreviationLogStream);
        List<Lap> laps = getLaps(racers, startLogStream, endLogStream);

        laps.sort(Comparator.comparing(racer -> racer.getDuration()));

        return laps;

    }

    private static List<Racer> getRacers(Stream<String> abbreviationLogLines) {

        List<Racer> racers = new ArrayList<Racer>();

        abbreviationLogLines.forEach(v -> {
            String[] racerData = v.split(LOG_FIELDS_DELIMITER);
            if (racerData.length == 3) {
                racers.add(new Racer(racerData[1], racerData[2], racerData[0]));
            }
        });

        return racers;

    }

    private static List<Lap> getLaps(List<Racer> racers, Stream<String> startLogLines, Stream<String> endLogLines) {

        Map<String, LocalDateTime> startsByAbbreviation = getDateTimeByAbbreviation(startLogLines);
        Map<String, LocalDateTime> endsByAbbreviation = getDateTimeByAbbreviation(endLogLines);

        List<Lap> laps = new ArrayList<Lap>(racers.size());

        racers.forEach(racer -> {

            LocalDateTime startTime = startsByAbbreviation.get(racer.getAbbreviation());
            LocalDateTime endTime = endsByAbbreviation.get(racer.getAbbreviation());

            Duration duration = Duration.between(startTime, endTime);

            Lap lap = new Lap(startTime, endTime, duration, racer);
            laps.add(lap);

        });

        return laps;
    }

    private static Map<String, LocalDateTime> getDateTimeByAbbreviation(Stream<String> durationParts) {

        Map<String, LocalDateTime> dateTimeByAbbreviation = new HashMap<String, LocalDateTime>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        dateTimeByAbbreviation = durationParts.collect(Collectors.toMap(line -> line.substring(0, 3),
                line -> LocalDateTime.parse(line.substring(3), formatter)));

        return dateTimeByAbbreviation;

    }

    private static Stream<String> readLogFile(String logFileName) throws IOException {

        if (logFileName == null || logFileName.isEmpty()) {
            throw new IllegalArgumentException("Path to the log file should not be empty");
        }

        try {
            String logFilePath = FastestRacersReport.class.getResource("/" + logFileName).getPath();
            Path path = Paths.get(logFilePath);
            Stream<String> streamLogFile = Files.lines(path);
            return streamLogFile;

        } catch (Exception err) {
            throw new FileNotFoundException("Cannot proccess the log file: " + logFileName + err.getMessage());
        }
    }

}
