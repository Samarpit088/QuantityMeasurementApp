package UC2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QuantityMeasurementApp {
    static class Feet {
        private final double value;
        public Feet(double value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }
    static class Inches {
        private final double value;
        public Inches(double value) {
            this.value = value;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }
    public static boolean checkFeetEquality(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        return f1.equals(f2);
    }
    public static boolean checkInchesEquality(double v1, double v2) {
        Inches i1 = new Inches(v1);
        Inches i2 = new Inches(v2);
        return i1.equals(i2);
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first value in inches: ");
            double inch1 = scanner.nextDouble();
            System.out.print("Enter second value in inches: ");
            double inch2 = scanner.nextDouble();
            boolean inchResult = checkInchesEquality(inch1, inch2);
            System.out.println("Input: " + inch1 + " inch and " + inch2 + " inch");
            System.out.println("Output: Equal (" + inchResult + ")");
            System.out.print("\nEnter first value in feet: ");
            double feet1 = scanner.nextDouble();
            System.out.print("Enter second value in feet: ");
            double feet2 = scanner.nextDouble();
            boolean feetResult = checkFeetEquality(feet1, feet2);
            System.out.println("Input: " + feet1 + " ft and " + feet2 + " ft");
            System.out.println("Output: Equal (" + feetResult + ")");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numeric values only.");
        }
    }
}

