package UC9;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.println(w1.equals(w2));
        System.out.println(w1.convertTo(WeightUnit.GRAM));
        QuantityWeight w3 = new QuantityWeight(2.0, WeightUnit.POUND);
        System.out.println(w1.add(w3));
        System.out.println(QuantityWeight.add(w1, w3, WeightUnit.KILOGRAM));
    }
}
enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);
    private final double conversionFactor;
    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    public double getConversionFactor() {
        return conversionFactor;
    }
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}
class QuantityWeight {
    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON = 1e-6;
    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }
    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        return new QuantityWeight(converted, targetUnit);
    }
    public QuantityWeight add(QuantityWeight other) {
        if (other == null) {
            throw new IllegalArgumentException("Null quantity");
        }
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double sumBase = base1 + base2;
        double result = unit.convertFromBaseUnit(sumBase);
        return new QuantityWeight(result, unit);
    }
    public static QuantityWeight add(QuantityWeight q1, QuantityWeight q2, WeightUnit targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        double base1 = q1.unit.convertToBaseUnit(q1.value);
        double base2 = q2.unit.convertToBaseUnit(q2.value);
        double sumBase = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new QuantityWeight(result, targetUnit);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityWeight other = (QuantityWeight) obj;
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return Math.abs(base1 - base2) < EPSILON;
    }
    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Double.hashCode(Math.round(base / EPSILON));
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);
    private final double conversionFactor;
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}
class QuantityLength {
    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 1e-6;
    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }
    public QuantityLength convertTo(LengthUnit targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        return new QuantityLength(converted, targetUnit);
    }
    public QuantityLength add(QuantityLength other) {
        if (other == null) {
            throw new IllegalArgumentException("Null quantity");
        }
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double sumBase = base1 + base2;
        double result = unit.convertFromBaseUnit(sumBase);
        return new QuantityLength(result, unit);
    }
    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        double base1 = q1.unit.convertToBaseUnit(q1.value);
        double base2 = q2.unit.convertToBaseUnit(q2.value);
        double sumBase = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new QuantityLength(result, targetUnit);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return Math.abs(base1 - base2) < EPSILON;
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
