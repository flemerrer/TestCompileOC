package org.example.Calculator;

import java.util.Collections;
import java.util.Set;

public class Calculator {

    public int add(int x, int y) {
        return x+y;
    }

    public int multiply(int x, int y) {
        return x*y;
    }

    public void longCalculation() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> digitsSet(int number) {
        return Collections.singleton(number);
    }
}
