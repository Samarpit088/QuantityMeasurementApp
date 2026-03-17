package UC3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QuantityMeasurementAPp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first value: ");
            double v1 = scanner.nextDouble();
            System.out.print("Enter first unit (FEET/INCH): ");
            LengthUnit u1 = LengthUnit.valueOf(scanner.next().toUpperCase());
            System.out.print("Enter second value: ");
            double v2 = scanner.nextDouble();
            System.out.print("Enter second unit (FEET/INCH): ");
            LengthUnit u2 = LengthUnit.valueOf(scanner.next().toUpperCase());
            QuantityLength q1 = new QuantityLength(v1, u1);
            QuantityLength q2 = new QuantityLength(v2, u2);
            boolean result = q1.equals(q2);
            System.out.println("Input: Quantity(" + v1 + ", " + u1 + ") and Quantity(" + v2 + ", " + u2 + ")");
            System.out.println("Output: Equal (" + result + ")");
        } catch (InputMismatchException e) {
            System.out.println("Invalid numeric input.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid unit type. Supported units: FEET, INCH");
        }
    }
}
enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0);
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

