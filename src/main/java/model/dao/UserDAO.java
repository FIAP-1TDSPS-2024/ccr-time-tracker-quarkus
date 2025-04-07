package model.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import config.DatabaseConfig;
import model.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDAO {

    @Inject
    DatabaseConfig databaseConfig;

    public List<UserEntity> findAll() throws SQLException {
        List<UserEntity> users = new ArrayList<>();
        String sql = "SELECT id, name, email, password FROM users";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new UserEntity(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")));
            }
        }
        return users;
    }

    public Optional<UserEntity> findById(Long id) throws SQLException {
        String sql = "SELECT id, name, email, password FROM users WHERE id = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new UserEntity(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")));
                }
            }
        }
        return Optional.empty();
    }

    public UserEntity create(UserEntity user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong("id"));
                }
            }
        }
        return user;
    }

    public boolean update(UserEntity user) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setLong(4, user.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}