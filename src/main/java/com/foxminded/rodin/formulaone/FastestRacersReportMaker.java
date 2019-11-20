package com.foxminded.rodin.formulaone;

import java.time.Duration;
import java.util.List;

public class FastestRacersReportMaker {

    private static final String TOP_RACERS_SEPARATOR = "-------------------------------------------------------\n";
    private static final String REPORT_LINE_FORMAT = "%2d.%-17s|%-25s|%d:%02d.%03d\n";
    private static final int DEFAULT_TOP_LAPS_NUMBER = 15;

    private static final int MILLIS_PER_MINUTE = 60_000;
    private static final int MILLIS_PER_SECOND = 1_000;

    private LapsDetails lapsDetails;

    public FastestRacersReportMaker() {
        this.lapsDetails = new LapsDetails();
    }

    public FastestRacersReportMaker(String startLogFile, String endtLogFile, String abbreviationLogFile) {
        this.lapsDetails = new LapsDetails(startLogFile, endtLogFile, abbreviationLogFile);
    }

    public String generateFastestRacersReport() {
        return generateFastestRacersReport(DEFAULT_TOP_LAPS_NUMBER);
    }

    public String generateFastestRacersReport(int topLapsNumber) {

        StringBuilder boardBuilder = new StringBuilder();

        List<Lap> laps = lapsDetails.createLaps();

        int topLapsSeparatorPosition = Math.min(topLapsNumber, laps.size());

        for (int i = 0; i < topLapsSeparatorPosition; i++) {
            addLapToReport(boardBuilder, laps.get(i), i + 1);
        }

        addSeparatorToReport(boardBuilder);

        for (int i = topLapsSeparatorPosition; i < laps.size(); i++) {
            addLapToReport(boardBuilder, laps.get(i), i + 1);
        }

        return boardBuilder.toString();

    }

    private void addLapToReport(StringBuilder boardBuilder, Lap lap, int lineNumber) {

        Racer racer = lap.getRacer();
        Duration duration = lap.getDuration();

        String line = String.format(REPORT_LINE_FORMAT, lineNumber, racer.getName(), racer.getTeamName(),
                duration.toMinutes(), calculateDurationSeconds(duration), calculateDurationMillis(duration));
        boardBuilder.append(line);

    }

    private void addSeparatorToReport(StringBuilder boardBuilder) {
        boardBuilder.append(TOP_RACERS_SEPARATOR);
    }

    private static long calculateDurationSeconds(Duration duration) {
        return (duration.toMillis() % MILLIS_PER_MINUTE) / MILLIS_PER_SECOND;
    }

    private static long calculateDurationMillis(Duration duration) {
        return duration.toMillis() % MILLIS_PER_SECOND;
    }

}
