package org.example_UC16.core;

import java.util.function.DoubleBinaryOperator;

public enum ArithmeticOperation {
    ADD((a, b) -> a + b),
    SUBTRACT((a, b) -> a - b),
    DIVIDE((a, b) -> {
        if (Math.abs(b) < 1e-9) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    });

    private final DoubleBinaryOperator op;

    ArithmeticOperation(DoubleBinaryOperator op) {
        this.op = op;
    }

    public double apply(double a, double b) {
        return op.applyAsDouble(a, b);
    }

}
