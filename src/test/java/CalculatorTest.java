import org.example.Calculator.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    int x = (int) Math.random();
    int y = (int) Math.random();

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
