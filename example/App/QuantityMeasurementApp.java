package org.example.App;

import org.example.Controller.QuantityMeasurementController;
import org.example.Repository.QuantityMeasurementCacheRepository;
import org.example.Serivce.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        var repo = QuantityMeasurementCacheRepository.getInstance();
        var service = new QuantityMeasurementServiceImpl(repo);
        var controller = new QuantityMeasurementController(service);

        controller.performOperations();
    }
}
