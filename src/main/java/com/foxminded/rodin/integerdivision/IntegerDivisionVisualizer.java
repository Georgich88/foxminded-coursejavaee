package com.foxminded.rodin.integerdivision;

public class IntegerDivisionVisualizer {

    private int dividend;
    private int divisor;
    private int result;

    private static final String COLUMN_PRINTING_TYPE_SEPARATOR = "WithDivisorSeparator";
    private static final String COLUMN_PRINTING_TYPE_RESULT = "WithResult";

    private StringBuilder resultBuilder;

    public IntegerDivisionVisualizer(int dividend, int divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    /**
     * Performs integer division computation.
     * 
     * @return an string presentation of computation.
     */
    public String performDivision() throws ArithmeticException {

        if (dividend == 0) {
            throw new ArithmeticException("Dividend should not be zero");
        }

        if (divisor == 0) {
            throw new ArithmeticException("Divisor should not be zero");
        }

        if (dividend < 0) {
            throw new ArithmeticException("Dividend should be a positive number");
        }

        if (divisor < 0) {
            throw new ArithmeticException("Divisor should be a positive number");
        }

        if (dividend <= divisor) {
            throw new ArithmeticException("Dividend should be greater than divisor");
        }

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
                currentPrintingMethod = "";
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
        resultBuilder.append("_" + dividend + "|" + divisor + "\n");

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
        resultBuilder.append("\n");
        printCharacter(shift, ' ');
        resultBuilder.append("_" + quotient + "\n");
        printCharacter(shift, ' ');
        resultBuilder.append(" " + remainder + "\n");
        printCharacter(shift + 1, ' ');
        printCharacter(countDigits(quotient), '-');
    }

    private void printColumnWithDivisorSeparator(int quotient, int remainder) {

        int dividendDigits = countDigits(dividend);
        int quotientDigits = countDigits(quotient);

        resultBuilder.append(" " + remainder);
        printCharacter(dividendDigits - quotientDigits, ' ');
        resultBuilder.append("|");
        printCharacter(Math.max(countDigits(divisor), countDigits(result)), '-');
        resultBuilder.append("\n");

    }

    private void printColumnWithResult(int quotient, int remainder, int shift) {

        int dividendDigits = countDigits(dividend);
        int quotientDigits = countDigits(quotient);

        printCharacter(shift, ' ');
        resultBuilder.append("_" + quotient);
        printCharacter(dividendDigits - quotientDigits, ' ');
        resultBuilder.append("|" + result + "\n");
        printCharacter(shift, ' ');
        resultBuilder.append(" " + remainder + "\n ");
        printCharacter(countDigits(quotient), '-');

    }

    private void printLastColumn(int remainder, int shift) {

        resultBuilder.append("\n");
        printCharacter(shift, ' ');
        resultBuilder.append(" " + remainder + "\n");
    }

    private void printCharacter(int numberOfTimes, char character) {

        for (int i = 1; i <= numberOfTimes; i++) {
            resultBuilder.append(character);
        }

    }

}
