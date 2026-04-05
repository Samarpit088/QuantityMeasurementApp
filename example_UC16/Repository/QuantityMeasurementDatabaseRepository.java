package org.example_UC16.Repository;

import org.example_UC16.model.QuantityMeasurementEntity;
import org.example_UC16.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {
    public QuantityMeasurementDatabaseRepository() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS quantity_measurements (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "operation VARCHAR(50), " +
                    "result VARCHAR(100), " +
                    "error BOOLEAN, " +
                    "message VARCHAR(255)" +
                    ")";

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO quantity_measurements (operation, result, error, message) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.operation);
            ps.setDouble(2, entity.result);
            ps.setBoolean(3, entity.error);
            ps.setString(4, entity.errorMessage);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

