package UC7;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println("Feet Result: " + QuantityLength.add(q1, q2, LengthUnit.FEET));
        System.out.println("Inches Result: " + QuantityLength.add(q1, q2, LengthUnit.INCH));
        System.out.println("Yards Result: " + QuantityLength.add(q1, q2, LengthUnit.YARDS));
    }
}
enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETER(1.0 / 30.48);
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
    private static final double EPSILON = 1e-6;
    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }
    public QuantityLength convertTo(LengthUnit targetUnit) {
        double baseValue = this.value * this.unit.getConversionFactor();
        double convertedValue = baseValue / targetUnit.getConversionFactor();
        return new QuantityLength(convertedValue, targetUnit);
    }
    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Quantities cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!Double.isFinite(q1.value) || !Double.isFinite(q2.value)) {
            throw new IllegalArgumentException("Invalid numeric values");
        }
        double base1 = q1.value * q1.unit.getConversionFactor();
        double base2 = q2.value * q2.unit.getConversionFactor();
        double sumBase = base1 + base2;
        double result = sumBase / targetUnit.getConversionFactor();
        return new QuantityLength(result, targetUnit);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
        double base1 = this.value * this.unit.getConversionFactor();
        double base2 = other.value * other.unit.getConversionFactor();
        return Math.abs(base1 - base2) < EPSILON;
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
