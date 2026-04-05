package org.example_UC16.Service;

import org.example_UC16.Repository.IQuantityMeasurementRepository;
import org.example_UC16.core.Quantity;
import org.example_UC16.model.QuantityDTO;
import org.example_UC16.model.QuantityMeasurementEntity;
import org.example_UC16.units.LengthUnit;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private final IQuantityMeasurementRepository repo;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repo = repo;
    }

    private LengthUnit getLengthUnit(String unit) {
        return LengthUnit.valueOf(unit);
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        try {
            Quantity<LengthUnit> a = new Quantity<>(q1.value, getLengthUnit(q1.unit));
            Quantity<LengthUnit> b = new Quantity<>(q2.value, getLengthUnit(q2.unit));

            boolean res = a.equals(b);
            repo.save(new QuantityMeasurementEntity("COMPARE", res ? 1 : 0));
            return res;

        } catch (Exception e) {
            repo.save(new QuantityMeasurementEntity("COMPARE", e.getMessage()));
            throw e;
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String target) {
        Quantity<LengthUnit> a = new Quantity<>(q.value, getLengthUnit(q.unit));
        Quantity<LengthUnit> res = a.convertTo(getLengthUnit(target));

        repo.save(new QuantityMeasurementEntity("CONVERT", res.getValue()));

        return new QuantityDTO(res.getValue(), target);
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        Quantity<LengthUnit> a = new Quantity<>(q1.value, getLengthUnit(q1.unit));
        Quantity<LengthUnit> b = new Quantity<>(q2.value, getLengthUnit(q2.unit));

        Quantity<LengthUnit> res = a.add(b);

        repo.save(new QuantityMeasurementEntity("ADD", res.getValue()));

        return new QuantityDTO(res.getValue(), res.getUnit().name());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        Quantity<LengthUnit> a = new Quantity<>(q1.value, getLengthUnit(q1.unit));
        Quantity<LengthUnit> b = new Quantity<>(q2.value, getLengthUnit(q2.unit));

        double res = a.divide(b);

        repo.save(new QuantityMeasurementEntity("DIVIDE", res));

        return res;
    }
}
