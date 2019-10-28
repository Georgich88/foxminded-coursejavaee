package com.foxminded.rodin.formulaone;

import java.time.Duration;
import java.util.List;

public class FastestRacersReport {

    private static final String TOP_RACERS_SEPARATOR = "-------------------------------------------------------";
    private static final String REPORT_LINE_FORMAT = "%2d.%-17s|%-25s|%d:%02d.%03d";
    private static final int DEFAULT_TOP_LAPS_NUMBER = 15;
    private LapsDetails lapsDetails;

    public FastestRacersReport() {
        this.lapsDetails = new LapsDetails();
    }

    public FastestRacersReport(String startLogFile, String endtLogFile, String abbreviationLogFile) {
        this.lapsDetails = new LapsDetails(startLogFile, endtLogFile, abbreviationLogFile);
    }

    public String getFastestRacersReport() {
        return getFastestRacersReport(DEFAULT_TOP_LAPS_NUMBER);
    }

    public String getFastestRacersReport(int topLapsNumber) {

        StringBuilder boardBuilder = new StringBuilder();

        List<Lap> laps = lapsDetails.getLaps();
        Integer lineNumber = 1;

        for (Lap lap : laps) {

            Racer racer = lap.getRacer();
            Duration duration = lap.getDuration();
            String line = String.format(REPORT_LINE_FORMAT, lineNumber, racer.getName(), racer.getTeamName(),
                    duration.toMinutes(), (duration.toMillis() % 60000) / 1000, duration.toMillis() % 1000);
            boardBuilder.append(line + "\n");

            if (lineNumber == topLapsNumber)
                boardBuilder.append(TOP_RACERS_SEPARATOR + "\n");

            lineNumber++;
        }


        return boardBuilder.toString();

    }

}
