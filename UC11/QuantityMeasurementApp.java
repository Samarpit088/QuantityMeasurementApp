package UC11;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);
        System.out.println(v1.equals(v2));
        System.out.println(v1.equals(v3));
        System.out.println(v1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println(v3.convertTo(VolumeUnit.LITRE));
        System.out.println(v1.add(v2));
        System.out.println(v1.add(v3, VolumeUnit.MILLILITRE));
        Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);
        System.out.println(v1.equals(l1));
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