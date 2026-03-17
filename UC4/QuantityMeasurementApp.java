package UC4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter first value:");
            double value1 = scanner.nextDouble();
            System.out.println("Enter first unit (FEET, INCH, YARD, CENTIMETER):");
            String unit1Input = scanner.next().toUpperCase();
            LengthUnit unit1 = LengthUnit.valueOf(unit1Input);
            System.out.println("Enter second value:");
            double value2 = scanner.nextDouble();
            System.out.println("Enter second unit (FEET, INCH, YARD, CENTIMETER):");
            String unit2Input = scanner.next().toUpperCase();
            LengthUnit unit2 = LengthUnit.valueOf(unit2Input);
            QuantityLength q1 = new QuantityLength(value1, unit1);
            QuantityLength q2 = new QuantityLength(value2, unit2);
            boolean result = q1.equals(q2);
            System.out.println("Input: Quantity(" + value1 + ", " + unit1 + ") and Quantity(" + value2 + ", " + unit2 + ")");
            System.out.println("Output: Equal (" + result + ")");
        } catch (InputMismatchException e) {
            System.out.println("Invalid numeric input. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid unit entered. Supported units: FEET, INCH, YARD, CENTIMETER");
        }
    }
}
enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(0.393701 / 12.0);
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
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }
    private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }
}
