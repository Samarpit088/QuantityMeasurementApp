package UC12;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        Quantity<LengthUnit> l1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6, LengthUnit.INCH);
        System.out.println(l1.subtract(l2));
        System.out.println(l1.subtract(l2, LengthUnit.INCH));
        System.out.println(l2.subtract(l1));
        System.out.println(l1.divide(new Quantity<>(2, LengthUnit.FEET)));
        System.out.println(new Quantity<>(24, LengthUnit.INCH).divide(new Quantity<>(2, LengthUnit.FEET)));
    }
}
interface IMeasurable {
    double getConversionFactor();
    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }
    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }
    String getUnitName();
}
enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(1.0 / 30.48);
    private final double factor;
    LengthUnit(double factor) {
        this.factor = factor;
    }
    @Override
    public double getConversionFactor() {
        return factor;
    }
    @Override
    public String getUnitName() {
        return name();
    }
}
enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);
    private final double factor;
    WeightUnit(double factor) {
        this.factor = factor;
    }
    @Override
    public double getConversionFactor() {
        return factor;
    }
    @Override
    public String getUnitName() {
        return name();
    }
}
enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);
    private final double factor;
    VolumeUnit(double factor) {
        this.factor = factor;
    }
    @Override
    public double getConversionFactor() {
        return factor;
    }
    @Override
    public String getUnitName() {
        return name();
    }
}
class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 1e-6;
    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");
        this.value = value;
        this.unit = unit;
    }
    public Quantity<U> convertTo(U targetUnit) {
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(round(converted), targetUnit);
    }
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null)
            throw new IllegalArgumentException("Null quantity");
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double sum = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(sum);
        return new Quantity<>(round(result), targetUnit);
    }
    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateInputs(other, targetUnit);
        double baseThis = this.toBaseUnit();
        double baseOther = other.toBaseUnit();
        double baseResult = baseThis - baseOther;
        double result = fromBaseUnit(baseResult, targetUnit);
        return new Quantity<>(round(result), targetUnit);
    }
    public double divide(Quantity<U> other) {
        validateInputs(other);
        double baseThis = this.toBaseUnit();
        double baseOther = other.toBaseUnit();
        if (baseOther == 0.0) {
            throw new ArithmeticException("Cannot divide by zero quantity");
        }
        return baseThis / baseOther;
    }
    private double toBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }
    private double fromBaseUnit(double baseValue, U targetUnit) {
        return baseValue / targetUnit.getConversionFactor();
    }
    private void validateInputs(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }
    private void validateInputs(Quantity<U> other, U targetUnit) {
        validateInputs(other);
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!this.unit.getClass().equals(targetUnit.getClass())) {
            throw new IllegalArgumentException("Target unit must match category");
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (!unit.getClass().equals(other.unit.getClass()))
            return false;
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
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}