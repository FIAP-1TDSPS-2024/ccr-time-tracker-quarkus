package config;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class DatabaseConfig {

    @Inject
    AgroalDataSource dataSource;

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}