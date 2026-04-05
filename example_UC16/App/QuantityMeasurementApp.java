package org.example_UC16.App;

import org.example_UC16.Controller.QuantityMeasurementController;
import org.example_UC16.Repository.QuantityMeasurementDatabaseRepository;
import org.example_UC16.Service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        QuantityMeasurementDatabaseRepository repo =
                new QuantityMeasurementDatabaseRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        controller.run();
    }
}
