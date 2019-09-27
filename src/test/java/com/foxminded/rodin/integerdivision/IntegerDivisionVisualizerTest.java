package com.foxminded.rodin.integerdivision;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IntegerDivisionVisualizerTest {

    @Test
    public void shouldReturnCorrectResultCase1() {

        IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(78945, 4);

        assertEquals(integerDivisionVisualizer.performDivision(), 
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
    public void shouldReturnCorrectResultCase2() {

        IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(78945, 45);

        assertEquals(integerDivisionVisualizer.performDivision(), 
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
    public void shouldReturnCorrectResultCase3() {

        IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(500, 4);

        assertEquals(integerDivisionVisualizer.performDivision(), 
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
    public void shouldThrowArithmeticExceptionIfDividendLessThanDivisor() {

        assertThrows(ArithmeticException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(1, 600);
            String result = integerDivisionVisualizer.performDivision();
        });

    }    
    
    @Test
    public void shouldThrowArithmeticExceptionIfDividendIsNegative() {

        assertThrows(ArithmeticException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(-1, 600);
            String result = integerDivisionVisualizer.performDivision();
        });

    }  
    
    @Test
    public void shouldThrowArithmeticExceptionIfDivisorIsNegative() {

        assertThrows(ArithmeticException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(-1, 600);
            String result = integerDivisionVisualizer.performDivision();
        });

    }      
 
    @Test
    public void shouldThrowArithmeticExceptionIfDividendIsZero() {

        assertThrows(ArithmeticException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(0, 10);
            String result = integerDivisionVisualizer.performDivision();
        });

    }

    @Test
    public void shouldThrowArithmeticExceptionIfDivisorIsZero() {

        assertThrows(ArithmeticException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(0, 10);
            String result = integerDivisionVisualizer.performDivision();
        });

    } 
    
    @Test
    public void shouldThrowArithmeticExceptionIfDivisorIsNumberOne() {

        assertThrows(ArithmeticException.class, () -> {
            IntegerDivisionVisualizer integerDivisionVisualizer = new IntegerDivisionVisualizer(1, 2);
            String result = integerDivisionVisualizer.performDivision();
        });

    }       
    
}
