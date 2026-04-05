package org.example_UC16.Controller;


import org.example_UC16.model.QuantityDTO;
import org.example_UC16.Service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void run() {

        QuantityDTO q1 = new QuantityDTO(10, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCH");

        System.out.println("Equal: " + service.compare(q1, q2));

        QuantityDTO sum = service.add(q1, q2);
        System.out.println("Sum: " + sum.value + " " + sum.unit);

        QuantityDTO converted = service.convert(q1, "INCH");
        System.out.println("Converted: " + converted.value + " " + converted.unit);
    }
}
