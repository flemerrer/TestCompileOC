import org.example.Calculator.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private static int x;
    private static int y;
    private static Instant startedAt;
    private static Calculator calculator;

    @BeforeAll
    public static void initStartingTime(){

        startedAt = Instant.now();
        System.out.println("Tests started at " + startedAt);
    }

    @AfterAll
    public static void showTestDuration(){

        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Tests ended at " + endedAt + "/n Tests duration : {0} ms", duration));
    }

    @BeforeEach
    public void initCalculator(){

        calculator = new Calculator();
        x = (int) Math.random();
        y = (int) Math.random();
    }

    @AfterEach
    public void resetCalculator(){

        calculator = null;
        x = 0;
        y = 0;
    }

    @Test
    public void Add_TwoPositiveNumbers(){

        int testedSum = calculator.add(x, y);
        assertEquals( x+y, testedSum);
    }

    @Test
    public void multiply_TwoPositiveNumbers() {

        int testedMultiple = calculator.multiply(x, y);
        assertEquals(x*y, testedMultiple);
    }

    @ParameterizedTest(name = "{0}*0 should be equal to 0")
    @ValueSource(ints = {0, 1, 2, 42, 1011, 5089})
    public void multiply_OneNumberByZero_ShouldReturnZero(int arg){

        int testedMultipleOfZero = calculator.multiply(arg, 0);
        assertEquals(0, testedMultipleOfZero);
    }

    @ParameterizedTest(name = "{0} + {1} should be equal to {2}")
    @CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
    public void add_MultipleNumbers_ShouldReturnTheirSum(int arg1, int arg2, int expectedResult){

        int actualResult = calculator.add(arg1, arg2);
        assertEquals(expectedResult, actualResult);
    }

    @Timeout(1)
    @Test
    public void longCalcul_ShouldCompleteUnderOneSecond(){

        calculator.longCalculation();
    }

}
