package com.foxminded.rodin.formulaone;

import java.io.IOException;

public class Application {

    private static final int DEFAULT_TOP_LAPS_NUMBER = 15;

    public static void main(String[] args) {

        try {
            LapsDetails lapsDetails = new LapsDetails();
            String report = FastestRacersReport.getFastestRacersReport(lapsDetails, DEFAULT_TOP_LAPS_NUMBER);
            System.out.println(report);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }

    }

}
