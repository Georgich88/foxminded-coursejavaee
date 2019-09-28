package com.foxminded.rodin.integerdivision;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IntegerDivisionVisualizerTest {

    @Test
    public void shouldReturnCorrectResultWhenDivisionOfEvenNumberByOddNumber() {

        IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();

        assertEquals(integerDivisionVisualizer.performDivision(78945, 4), 
                "_78945|4\n" + 
                " 4    |-----\n" + 
                "_38   |19736\n" + 
                " 36\n" + 
                " --\n" + 
                " _29\n" + 
                "  28\n" + 
                "  --\n" + 
                "  _14\n" + 
                "   12\n" + 
                "   --\n" + 
                "   _25\n" + 
                "    24\n" + 
                "    --\n" + 
                "     1\n");

    }
    
    
    @Test
    public void shouldReturnCorrectResultWhenDivisionOfOddNumberByOddNumber() {

        IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();

        assertEquals(integerDivisionVisualizer.performDivision(78945, 45), 
                "_78945|45\n" + 
                " 45   |----\n" + 
                "_339  |1754\n" + 
                " 315\n" + 
                " ---\n" + 
                " _244\n" + 
                "  225\n" + 
                "  ---\n" + 
                "  _195\n" + 
                "   180\n" + 
                "   ---\n" + 
                "    15\n");

    }
    
    @Test
    public void shouldReturnCorrectResultWhenDivisionOfEvenNumberByEvenNumber() {

        IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();

        assertEquals(integerDivisionVisualizer.performDivision(500, 4), 
                "_500|4\n" + 
                " 4  |---\n" + 
                "_10 |125\n" + 
                " 8\n" + 
                " --\n" + 
                " _20\n" + 
                "  20\n" + 
                "  --\n" + 
                "    0\n");

    }        
 
    
    @Test
    public void shouldThrowIllegalArgumentExceptionIfDividendLessThanDivisor() {

        assertThrows(IllegalArgumentException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();
            String result = integerDivisionVisualizer.performDivision(1, 600);
        });

    }    
    
    @Test
    public void shouldThrowIllegalArgumentExceptionIfDividendIsNegative() {

        assertThrows(IllegalArgumentException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();
            String result = integerDivisionVisualizer.performDivision(-1, 600);
        });

    }  
    
    @Test
    public void shouldThrowIllegalArgumentExceptionIfDivisorIsNegative() {

        assertThrows(IllegalArgumentException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();
            String result = integerDivisionVisualizer.performDivision(-1, 600);
        });

    }      
 
    @Test
    public void shouldThrowIllegalArgumentExceptionIfDividendIsZero() {

        assertThrows(IllegalArgumentException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();
            String result = integerDivisionVisualizer.performDivision(0, 10);
        });

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfDivisorIsZero() {

        assertThrows(IllegalArgumentException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();
            String result = integerDivisionVisualizer.performDivision(0, 10);
        });

    } 
    
    @Test
    public void shouldThrowIllegalArgumentExceptionIfDivisorIsNumberOne() {

        assertThrows(IllegalArgumentException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer();
            String result = integerDivisionVisualizer.performDivision(1, 2);
        });

    }       
    
}
