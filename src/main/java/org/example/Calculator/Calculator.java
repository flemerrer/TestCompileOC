package org.example.Calculator;

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
}
