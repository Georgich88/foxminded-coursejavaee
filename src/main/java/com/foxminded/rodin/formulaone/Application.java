package com.foxminded.rodin.formulaone;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {

        try {
            String report = FastestRacersReport.getFastestRacersReport("start.log", "end.log", "abbreviations.txt", 15);
            System.out.println(report);
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

}
