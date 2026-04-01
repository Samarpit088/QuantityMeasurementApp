package org.example.Model;

public class QuantityMeasurementEntity {
    public QuantityDTO input1;
    public QuantityDTO input2;
    public String operation;
    public String result;
    public boolean isError;

    public QuantityMeasurementEntity(String result) {
        this.result = result;
        this.isError = false;
    }

    public QuantityMeasurementEntity(String error, boolean isError) {
        this.result = error;
        this.isError = true;
    }
}
