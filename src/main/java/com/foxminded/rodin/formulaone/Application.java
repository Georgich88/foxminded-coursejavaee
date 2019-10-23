package com.foxminded.rodin.formulaone;

import java.io.IOException;

public class Application {

    private static final String LOG_START_DEFAULT_NAME = "start.log";
    private static final String LOG_END_DEFAULT_NAME = "end.log";
    private static final String LOG_ABBREVIATION_DEFAULT_NAME = "abbreviations.txt";
    private static final int DEFAULT_TOP_LAPS_NUMBER = 15;

    public static void main(String[] args) {

        try {
            String report = FastestRacersReport.getFastestRacersReport(LOG_START_DEFAULT_NAME, LOG_END_DEFAULT_NAME,
                    LOG_ABBREVIATION_DEFAULT_NAME, DEFAULT_TOP_LAPS_NUMBER);
            System.out.println(report);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }

    }

}
