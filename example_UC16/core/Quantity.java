package org.example_UC16.core;

import org.example_UC16.units.IMeasurable;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
    private static final double EPS = 1e-6;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U target) {
        double base = unit.convertToBaseUnit(value);
        double converted = target.convertFromBaseUnit(base);
        return new Quantity<>(converted, target);
    }

    public Quantity<U> add(Quantity<U> other) {
        validate(other);
        double result = ArithmeticOperation.ADD.apply(
                unit.convertToBaseUnit(value),
                other.unit.convertToBaseUnit(other.value)
        );
        return new Quantity<>(unit.convertFromBaseUnit(result), unit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        validate(other);
        double result = ArithmeticOperation.SUBTRACT.apply(
                unit.convertToBaseUnit(value),
                other.unit.convertToBaseUnit(other.value)
        );
        return new Quantity<>(unit.convertFromBaseUnit(result), unit);
    }

    public double divide(Quantity<U> other) {
        validate(other);
        return ArithmeticOperation.DIVIDE.apply(
                unit.convertToBaseUnit(value),
                other.unit.convertToBaseUnit(other.value)
        );
    }

    private void validate(Quantity<U> other) {
        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement types");
        }
        unit.validateOperationSupport("operation");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quantity<?> q)) return false;

        if (!unit.getClass().equals(q.unit.getClass())) return false;

        double b1 = unit.convertToBaseUnit(value);
        double b2 = q.unit.convertToBaseUnit(q.value);

        return Math.abs(b1 - b2) < EPS;
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }
}
