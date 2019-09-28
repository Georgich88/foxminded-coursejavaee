package com.foxminded.rodin.integerdivision;

public class IntegerDivisionVisualizer {

    private int dividend;
    private int divisor;
    private int result;

    private static final String COLUMN_PRINTING_TYPE_SEPARATOR = "WithDivisorSeparator";
    private static final String COLUMN_PRINTING_TYPE_RESULT = "WithResult";
    private static final String COLUMN_PRINTING_TYPE_PLAIN = "Plain";

    private static final String VERTICAL_BAR_SEPARATOR = "|";
    private static final String MINUS_SYMBOL = "_";
    private static final String SPACE_SEPARATOR = " ";
    private static final String NEWLINE_SEPARATOR = "\n";
    private static final String HORIZONTAL_LINE_SEPARATOR = "-";

    private StringBuilder resultBuilder;

    public IntegerDivisionVisualizer() {
    }

    /**
     * Performs integer division computation.
     * 
     * @param inputDividend the input dividend for division computation.
     * @param inputDivisor  the input divisor for division computation.
     * @return an string presentation of computation.
     */
    public String performDivision(int inputDividend, int inputDivisor) throws IllegalArgumentException {

        if (inputDividend <= 0) {
            throw new IllegalArgumentException("Dividend should be a positive number");
        }

        if (inputDivisor <= 0) {
            throw new IllegalArgumentException("Divisor should be a positive number");
        }

        if (inputDividend <= inputDivisor) {
            throw new IllegalArgumentException("Dividend should be greater than divisor");
        }

        dividend = inputDividend;
        divisor = inputDivisor;

        resultBuilder = new StringBuilder();

        result = dividend / divisor;

        int currentDividend = dividend;
        int currentDivisor = divisor;
        int shift = 0;
        String currentPrintingMethod = COLUMN_PRINTING_TYPE_SEPARATOR;

        printDividendAndDivisor(dividend, divisor);

        while (currentDividend >= currentDivisor) {

            int[] currentresultsOfDivision = intermediateDivision(currentDividend, currentDivisor, shift,
                    currentPrintingMethod);

            currentDividend = currentresultsOfDivision[0];
            shift = currentresultsOfDivision[1];

            if (currentPrintingMethod.equals(COLUMN_PRINTING_TYPE_SEPARATOR)) {
                currentPrintingMethod = COLUMN_PRINTING_TYPE_RESULT;

            } else if (currentPrintingMethod.equals(COLUMN_PRINTING_TYPE_RESULT)) {
                currentPrintingMethod = COLUMN_PRINTING_TYPE_PLAIN;
            }
        }

        printLastColumn(currentDividend, shift);

        return resultBuilder.toString();
    }

    private int countDigits(int numberForCounting) {
        if (numberForCounting != 0) {
            return (int) Math.log10(Math.abs(numberForCounting)) + 1;
        } else {
            return 0;
        }
    }

    private void printDividendAndDivisor(int dividend, int divisor) {
        resultBuilder.append(MINUS_SYMBOL + dividend + VERTICAL_BAR_SEPARATOR + divisor + NEWLINE_SEPARATOR);

    }

    private int[] intermediateDivision(int dividend, int divisor, int shift, String printingType) {

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

        if (printingType.equals(COLUMN_PRINTING_TYPE_SEPARATOR)) {
            printColumnWithDivisorSeparator(quotient, remainder);
        } else if (printingType.equals(COLUMN_PRINTING_TYPE_RESULT)) {
            printColumnWithResult(quotient, remainder, shift);
        } else {
            printColumn(quotient, remainder, shift);
        }

        shift += (countDigits(quotient) - countDigits(quotient - remainder));

        int[] resultsOfDivision = { dividend, shift };
        return resultsOfDivision;
    }

    private int pow10(int exponent) {
        return (int) Math.pow(10, exponent);
    }

    private int computeQuotient(int integerForTrim, int numberDigitsForTrim) {
        return integerForTrim / pow10(numberDigitsForTrim);
    }

    private void printColumn(int quotient, int remainder, int shift) {
        resultBuilder.append(NEWLINE_SEPARATOR);
        printSymbol(shift, SPACE_SEPARATOR);
        resultBuilder.append(MINUS_SYMBOL + quotient + NEWLINE_SEPARATOR);
        printSymbol(shift, SPACE_SEPARATOR);
        resultBuilder.append(SPACE_SEPARATOR + remainder + NEWLINE_SEPARATOR);
        printSymbol(shift + 1, SPACE_SEPARATOR);
        printSymbol(countDigits(quotient), HORIZONTAL_LINE_SEPARATOR);
    }

    private void printColumnWithDivisorSeparator(int quotient, int remainder) {

        int dividendDigits = countDigits(dividend);
        int quotientDigits = countDigits(quotient);

        resultBuilder.append(SPACE_SEPARATOR + remainder);
        printSymbol(dividendDigits - quotientDigits, SPACE_SEPARATOR);
        resultBuilder.append(VERTICAL_BAR_SEPARATOR);
        printSymbol(Math.max(countDigits(divisor), countDigits(result)), HORIZONTAL_LINE_SEPARATOR);
        resultBuilder.append(NEWLINE_SEPARATOR);

    }

    private void printColumnWithResult(int quotient, int remainder, int shift) {

        int dividendDigits = countDigits(dividend);
        int quotientDigits = countDigits(quotient);

        printSymbol(shift, SPACE_SEPARATOR);
        resultBuilder.append(MINUS_SYMBOL + quotient);
        printSymbol(dividendDigits - quotientDigits, SPACE_SEPARATOR);
        resultBuilder.append(VERTICAL_BAR_SEPARATOR + result + NEWLINE_SEPARATOR);
        printSymbol(shift, SPACE_SEPARATOR);
        resultBuilder.append(SPACE_SEPARATOR + remainder + NEWLINE_SEPARATOR + SPACE_SEPARATOR);
        printSymbol(countDigits(quotient), HORIZONTAL_LINE_SEPARATOR);

    }

    private void printLastColumn(int remainder, int shift) {

        resultBuilder.append(NEWLINE_SEPARATOR);
        printSymbol(shift, SPACE_SEPARATOR);
        resultBuilder.append(SPACE_SEPARATOR + remainder + NEWLINE_SEPARATOR);
    }

    private void printSymbol(int numberOfTimes, String symbol) {

        for (int i = 1; i <= numberOfTimes; i++) {
            resultBuilder.append(symbol);
        }

    }

}
