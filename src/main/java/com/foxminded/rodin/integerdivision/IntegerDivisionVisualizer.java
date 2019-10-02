package com.foxminded.rodin.integerdivision;

public class IntegerDivisionVisualizer {

    private static final String COLUMN_PRINTING_TYPE_SEPARATOR = "WithDivisorSeparator";
    private static final String COLUMN_PRINTING_TYPE_RESULT = "WithResult";
    private static final String COLUMN_PRINTING_TYPE_PLAIN = "Plain";

    private static final String DIVISION_BRACKET_VERTICAL_BAR = "|";
    private static final String DIVISION_BRACKET_VINCULUM = "-";
    private static final String SUBSTRACTION_SYMBOL = "_";

    /**
     * Performs integer division computation.
     * 
     * @param dividend the input dividend for division computation.
     * @param divisor  the input divisor for division computation.
     * @return an string presentation of computation.
     */
    public static String performDivision(int dividend, int divisor) throws IllegalArgumentException {

        if (dividend <= 0) {
            throw new IllegalArgumentException("Dividend should be a positive number");
        }

        if (divisor <= 0) {
            throw new IllegalArgumentException("Divisor should be a positive number");
        }

        if (dividend <= divisor) {
            throw new IllegalArgumentException("Dividend should be greater than divisor");
        }

        StringBuilder resultBuilder = new StringBuilder();

        int currentDividend = dividend;
        int currentDivisor = divisor;
        int quotient;
        int remainder;
        int shift = 0;

        String currentPrintingMethod = COLUMN_PRINTING_TYPE_SEPARATOR;

        printDividendAndDivisor(dividend, divisor, resultBuilder);

        while (currentDividend >= currentDivisor) {

            int[] currentResultsOfDivision = intermediateDivision(currentDividend, currentDivisor, shift,
                    currentPrintingMethod, resultBuilder);

            currentDividend = currentResultsOfDivision[0];
            quotient = currentResultsOfDivision[1];
            remainder = currentResultsOfDivision[2];
            shift = currentResultsOfDivision[3];

            if (currentPrintingMethod.equals(COLUMN_PRINTING_TYPE_SEPARATOR)) {
                printColumnWithDivisorSeparator(dividend, divisor, quotient, remainder, resultBuilder);
                currentPrintingMethod = COLUMN_PRINTING_TYPE_RESULT;
            } else if (currentPrintingMethod.equals(COLUMN_PRINTING_TYPE_RESULT)) {
                printResult(dividend, divisor, remainder, shift, resultBuilder);
                printColumn(quotient, remainder, shift, resultBuilder);
                currentPrintingMethod = COLUMN_PRINTING_TYPE_PLAIN;
            } else {
                printColumn(quotient, remainder, shift, resultBuilder);
            }

            shift += (countDigits(quotient) - countDigits(quotient - remainder));

        }

        printLastColumn(currentDividend, shift, resultBuilder);

        return resultBuilder.toString();
    }

    private static int countDigits(int numberForCounting) {
        if (numberForCounting != 0) {
            return (int) Math.log10(Math.abs(numberForCounting)) + 1;
        } else {
            return 0;
        }
    }

    private static void printDividendAndDivisor(int dividend, int divisor, StringBuilder resultBuilder) {
        resultBuilder.append(SUBSTRACTION_SYMBOL + dividend + DIVISION_BRACKET_VERTICAL_BAR + divisor + "\n");

    }

    private static int[] intermediateDivision(int dividend, int divisor, int shift, String printingType,
            StringBuilder resultBuilder) {

        int remainder = 0;
        int digitsNumber = countDigits(dividend) - countDigits(divisor);
        int quotient = computeQuotient(dividend, (digitsNumber));

        if (quotient >= divisor) {
            remainder = quotient / divisor * divisor;
            dividend = dividend - remainder * pow10(digitsNumber);
        } else {
            quotient = computeQuotient(dividend, (digitsNumber - 1));
            remainder = quotient / divisor * divisor;
            dividend = dividend - (remainder * pow10(countDigits(dividend) - (countDigits(quotient))));
        }

        int[] resultsOfDivision = { dividend, quotient, remainder, shift };
        return resultsOfDivision;
    }

    private static int pow10(int exponent) {
        return (int) Math.pow(10, exponent);
    }

    private static int computeQuotient(int integerForTrim, int numberDigitsForTrim) {
        return integerForTrim / pow10(numberDigitsForTrim);
    }

    private static void printColumn(int quotient, int remainder, int shift, StringBuilder resultBuilder) {
        resultBuilder.append("\n");
        printSymbol(shift, " ", resultBuilder);
        resultBuilder.append(SUBSTRACTION_SYMBOL + quotient + "\n");
        printSymbol(shift, " ", resultBuilder);
        resultBuilder.append(" " + remainder + "\n");
        printSymbol(shift + 1, " ", resultBuilder);
        printSymbol(countDigits(quotient), DIVISION_BRACKET_VINCULUM, resultBuilder);
    }

    private static void printColumnWithDivisorSeparator(int dividend, int divisor, int quotient, int remainder,
            StringBuilder resultBuilder) {

        int dividendDigits = countDigits(dividend);
        int quotientDigits = countDigits(quotient);
        int resultDigits = countDigits(dividend / divisor);
        int divisorDigits = countDigits(divisor);

        resultBuilder.append(" " + remainder);
        printSymbol(dividendDigits - quotientDigits, " ", resultBuilder);
        resultBuilder.append(DIVISION_BRACKET_VERTICAL_BAR);
        printSymbol(Math.max(divisorDigits, resultDigits), DIVISION_BRACKET_VINCULUM, resultBuilder);
        resultBuilder.append("\n");

    }

    private static void printResult(int dividend, int divisor, int remainder, int shift, StringBuilder resultBuilder) {

        int dividendDigits = countDigits(dividend);
        int divisorDigits = countDigits(divisor);
        int result = dividend / divisor;

        resultBuilder.append(" ");
        printSymbol(divisorDigits, DIVISION_BRACKET_VINCULUM, resultBuilder);
        printSymbol(dividendDigits - divisorDigits, " ", resultBuilder);
        resultBuilder.append(DIVISION_BRACKET_VERTICAL_BAR + result);

    }

    private static void printLastColumn(int remainder, int shift, StringBuilder resultBuilder) {

        resultBuilder.append("\n");
        printSymbol(shift, " ", resultBuilder);
        resultBuilder.append(" " + remainder + "\n");
    }

    private static void printSymbol(int numberOfTimes, String symbol, StringBuilder resultBuilder) {

        for (int i = 1; i <= numberOfTimes; i++) {
            resultBuilder.append(symbol);
        }

    }

}
