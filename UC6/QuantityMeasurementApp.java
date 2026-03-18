package UC6;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println(q1.add(q2));
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
    public QuantityLength add(QuantityLength other) {
        if (other == null) {
            throw new IllegalArgumentException("Second quantity cannot be null");
        }
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        double base1 = this.value * this.unit.getConversionFactor();
        double base2 = other.value * other.unit.getConversionFactor();
        double sumBase = base1 + base2;
        double result = sumBase / this.unit.getConversionFactor();
        return new QuantityLength(result, this.unit);
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
