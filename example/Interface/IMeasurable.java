package org.example.Interface;

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
    boolean supportsArithmetic();
}
