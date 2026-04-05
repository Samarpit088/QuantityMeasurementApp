package org.example_UC16.units;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable{
    CELSIUS(c -> c, c -> c),
    FAHRENHEIT(f -> (f - 32) * 5 / 9, c -> (c * 9 / 5) + 32);

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    TemperatureUnit(Function<Double, Double> toBase, Function<Double, Double> fromBase) {
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    public double convertFromBaseUnit(double base) {
        return fromBase.apply(base);
    }

    public boolean supportsArithmetic() { return false; }

    public String getUnitName() { return name(); }

    public double getConversionFactor() { return 1; }
}
