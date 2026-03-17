package UC1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter first value in feet: ");
            double value1 = sc.nextDouble();
            System.out.print("Enter second value in feet: ");
            double value2 = sc.nextDouble();
            Feet feet1 = new Feet(value1);
            Feet feet2 = new Feet(value2);
            boolean result = feet1.equals(feet2);
            System.out.println("Input: " + value1 + " ft and " + value2 + " ft");
            if (result) {
                System.out.println("Output: Equal (true)");
            } else {
                System.out.println("Output: Not Equal (false)");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numeric values only.");
        }
    }
    public static class Feet{
        private final double value;
        public Feet(double value){
            this.value=value;
        }
        @Override
        public boolean equals(Object obj){
            if(this==obj) return true;
            if(obj==null) return false;
            if(getClass()!=obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value,other.value)==0;
        }
    }
}

