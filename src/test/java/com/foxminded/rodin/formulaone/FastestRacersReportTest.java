package com.foxminded.rodin.formulaone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FastestRacersReportTest {

    @Test
    public void shouldReturnCorrectResultWhenGetsExampleLogs() {

        FastestRacersReportMaker report = new FastestRacersReportMaker();
        String result = report.getFastestRacersReport(15);

        String expectedResult = " 1.Sebastian Vettel |FERRARI                  |1:04.415\n"
                + " 2.Daniel Ricciardo |RED BULL RACING TAG HEUER|1:12.013\n"
                + " 3.Valtteri Bottas  |MERCEDES                 |1:12.434\n"
                + " 4.Lewis Hamilton   |MERCEDES                 |1:12.460\n"
                + " 5.Stoffel Vandoorne|MCLAREN RENAULT          |1:12.463\n"
                + " 6.Kimi Raikkonen   |FERRARI                  |1:12.639\n"
                + " 7.Fernando Alonso  |MCLAREN RENAULT          |1:12.657\n"
                + " 8.Sergey Sirotkin  |WILLIAMS MERCEDES        |1:12.706\n"
                + " 9.Charles Leclerc  |SAUBER FERRARI           |1:12.829\n"
                + "10.Sergio Perez     |FORCE INDIA MERCEDES     |1:12.848\n"
                + "11.Romain Grosjean  |HAAS FERRARI             |1:12.930\n"
                + "12.Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|1:12.941\n"
                + "13.Carlos Sainz     |RENAULT                  |1:12.950\n"
                + "14.Esteban Ocon     |FORCE INDIA MERCEDES     |1:13.028\n"
                + "15.Nico Hulkenberg  |RENAULT                  |1:13.065\n"
                + "-------------------------------------------------------\n"
                + "16.Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|1:13.179\n"
                + "17.Marcus Ericsson  |SAUBER FERRARI           |1:13.265\n"
                + "18.Lance Stroll     |WILLIAMS MERCEDES        |1:13.323\n"
                + "19.Kevin Magnussen  |HAAS FERRARI             |1:13.393\n";

        assertEquals(expectedResult, result);

    }

    @Test
    public void shouldReturnCorrectResultWhenGetsTestLogs() {

        FastestRacersReportMaker report = new FastestRacersReportMaker("startTest.log", "endTest.log", "abbreviationsTest.txt");
        String result = report.getFastestRacersReport(5);

        String expectedResult = " 1.Esteban Ocon     |FORCE INDIA MERCEDES     |-7:-46.-972\n"
                + " 2.Stan Vettel      |FERRARI                  |1:04.415\n"
                + " 3.Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|1:11.941\n"
                + " 4.Daniel Restinpeace|RED BULL RACING TAG HEUER|1:12.013\n"
                + " 5.Lous Houston     |MERCEDES                 |1:12.460\n"
                + "-------------------------------------------------------\n"
                + " 6.Stoffel Vandoorne|MCLAREN RENAULT          |1:12.463\n"
                + " 7.Fernando Alonso  |MCLAREN RENAULT          |1:12.657\n"
                + " 8.Sergey Sirotkin  |WILLIAMS MERCEDES        |1:12.706\n"
                + " 9.Charles Leclerc  |SAUBER FERRARI           |1:12.829\n"
                + "10.Sergio Petrov    |FORCE INDIA MERCEDES     |1:12.848\n"
                + "11.Carlos Saltykov  |RENAULT                  |1:12.950\n"
                + "12.Nico Hulkenberg  |RENAULT                  |1:13.065\n"
                + "13.Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|1:13.179\n"
                + "14.Marcus Ericsson  |SAUBER FERRARI           |6:13.265\n"
                + "15.Kim Rash         |FERRARI                  |11:12.639\n"
                + "16.Kevin Magnussen  |HAAS FERRARI             |11:13.393\n"
                + "17.Valtteri Bostanov|MERCEDES                 |21:12.434\n"
                + "18.Lance Stroll     |WILLIAMS MERCEDES        |21:13.323\n"
                + "19.Romain Grosjean  |HAAS FERRARI             |51:12.930\n";

        assertEquals(expectedResult, result);

    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenGetsWrongFileNameLog() {

        assertThrows(IllegalArgumentException.class, () -> {
            FastestRacersReportMaker report = new FastestRacersReportMaker("startWrong.log", "endWrong.log",
                    "abbreviationsWrong.txt");
            String result = report.getFastestRacersReport(15);
        });

    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenGetsEmptyFileNameLog() {

        assertThrows(IllegalArgumentException.class, () -> {
            FastestRacersReportMaker report = new FastestRacersReportMaker("", "end.log", "abbreviations.txt");
            String result = report.getFastestRacersReport(15);
        });

    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenGetsNullArguments() {

        assertThrows(IllegalArgumentException.class, () -> {
            FastestRacersReportMaker report = new FastestRacersReportMaker("start.log", null, "abbreviations.txt");
            String result = report.getFastestRacersReport(15);
        });

    }

}
