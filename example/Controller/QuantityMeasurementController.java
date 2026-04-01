package org.example.Controller;

import org.example.Model.QuantityDTO;
import org.example.Serivce.IQuantityMeasurementService;

public class QuantityMeasurementController {
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performOperations() {

        QuantityDTO t1 = new QuantityDTO(0.0, "CELSIUS");
        QuantityDTO t2 = new QuantityDTO(32.0, "FAHRENHEIT");

        System.out.println("Compare: " + service.compare(t1, t2));

        System.out.println("Convert: " + service.convert(t1, "FAHRENHEIT").value);

        try {
            service.add(t1, t2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
