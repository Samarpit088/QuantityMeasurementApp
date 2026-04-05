package org.example_UC16.units;

public interface IMeasurable {
    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double base) {
        return base / getConversionFactor();
    }

    String getUnitName();

    default boolean supportsArithmetic() {
        return true;
    }

    default void validateOperationSupport(String op) {
        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }
}
