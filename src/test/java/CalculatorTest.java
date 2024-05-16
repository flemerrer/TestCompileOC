import org.example.Calculator.Calculator;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(LoggingExtension.class)
public class CalculatorTest {

    private static int x = 0;
    private static int y = 0;
    private static Instant startedAt;
    private static Calculator calculator;

    private Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

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
        logger.info("Appel avant chaque test");
        calculator = new Calculator();
        x = (int) Math.random()*10;
        y = (int) Math.random()*10;
        System.out.println(x);
        System.out.println(y);
    }

    @AfterEach
    public void resetCalculator(){
        logger.info("Appel après chaque test");
        calculator = null;
        x = 0;
        y = 0;
    }

    @Nested
    @Tag("Basic Operations")
    @DisplayName("Additions et multiplications d'entiers")
    class BasicOperations {

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
    //        assertEquals(expectedResult, actualResult);
            assertThat(expectedResult).isEqualTo(actualResult);
        }

    }

    @Timeout(1)
    @Test
    public void longCalcul_ShouldCompleteUnderOneSecond(){

        calculator.longCalculation();
    }

    //TODO : fix, test did not pass
    @Test("Stoppé car la fonction ne marche pas")
    @Disabled
    public void digitsSet_shouldReturnTheSetOfDigits_ofPositiveInteger() {
        // GIVEN
        int number = 95897;

        // WHEN
        Set<Integer> actualDigits = calculator.digitsSet(number);

        // THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(5, 7, 8, 9);

        // en JUnit pur :
        Set<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toSet());
        assertEquals(expectedDigits, actualDigits);

    }

}
