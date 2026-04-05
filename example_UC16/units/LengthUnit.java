package org.example_UC16.units;

public enum LengthUnit implements IMeasurable{
    FEET(1.0),
    INCH(1.0 / 12),
    YARD(3.0),
    CM(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }

    public String getUnitName() { return name(); }
}
