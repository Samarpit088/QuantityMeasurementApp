package org.example_UC16.model;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {
    public String operation;
    public double result;
    public boolean error;
    public String errorMessage;

    public QuantityMeasurementEntity(String operation, double result) {
        this.operation = operation;
        this.result = result;
        this.error = false;
    }

    public QuantityMeasurementEntity(String operation, String errorMessage) {
        this.operation = operation;
        this.error = true;
        this.errorMessage = errorMessage;
    }
}
