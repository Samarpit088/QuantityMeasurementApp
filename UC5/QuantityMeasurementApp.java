package UC5;

import java.util.Scanner;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        System.out.println("Feet → Inches");
        System.out.println(QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));
        System.out.println("Yards → Feet");
        System.out.println(QuantityLength.convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));
        System.out.println("Inches → Yards");
        System.out.println(QuantityLength.convert(36.0, LengthUnit.INCHES, LengthUnit.YARDS));
        System.out.println("Centimeters → Inches");
        System.out.println(QuantityLength.convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));
    }
}
enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.393701 / 12.0);
    private final double conversionFactor;
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    public double getConversionFactor() {
        return conversionFactor;
    }
}
class QuantityLength {
    private final double value;
    private final LengthUnit unit;
    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }
    public QuantityLength convertTo(LengthUnit targetUnit) {
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new QuantityLength(convertedValue, targetUnit);
    }
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");
        double baseValue = value * source.getConversionFactor();
        return baseValue / target.getConversionFactor();
    }
    private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < 1e-6;
    }
    @Override
    public String toString() {
        return value + " " + unit;
    }
}
