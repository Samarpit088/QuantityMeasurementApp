package org.example.Serivce;

import org.example.Exception.QuantityMeasurementException;
import org.example.Model.QuantityDTO;
import org.example.Model.QuantityMeasurementEntity;
import org.example.Repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService{
    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public String compare(QuantityDTO q1, QuantityDTO q2) {
        if (q1.unit.equals(q2.unit) && q1.value == q2.value) {
            repository.save(new QuantityMeasurementEntity("Equal"));
            return "Equal";
        }
        return "Not Equal";
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {
        if (q.unit.equals("CELSIUS") && targetUnit.equals("FAHRENHEIT")) {
            double result = (q.value * 9 / 5) + 32;
            return new QuantityDTO(result, targetUnit);
        }
        throw new QuantityMeasurementException("Conversion not supported");
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        if (q1.unit.equals("CELSIUS")) {
            throw new QuantityMeasurementException("Temperature addition not allowed");
        }
        return new QuantityDTO(q1.value + q2.value, q1.unit);
    }
}
