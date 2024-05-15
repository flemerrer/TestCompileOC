import org.example.Calculator.Calculator;
import org.junit.jupiter.api.*;

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
    void testAddTwoPositiveNumbers(){

        int testedSum = calculator.add(x, y);

        assertEquals( x+y, testedSum);
    }

    @Test
    void multiplyTwoPositiveNumbers() {

        int testedMultiple = calculator.multiply(x, y);

        assertEquals(x*y, testedMultiple);
    }
}
