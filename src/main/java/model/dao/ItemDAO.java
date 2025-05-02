package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import config.DatabaseConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.entity.ItemEntity;

@ApplicationScoped
public class ItemDAO {

    @Inject
    DatabaseConfig databaseConfig;

    public Optional<ArrayList<ItemEntity>> findByFuncionarioID(Long funcionarioID) throws SQLException {
        String sql = "SELECT id_item, nome, abreviacao, url, id_funcionario, favorito FROM item WHERE id_funcionario = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, funcionarioID);

            try (ResultSet rs = stmt.executeQuery()) {
                ArrayList<ItemEntity> items = new ArrayList<>();
                while (rs.next()) {
                    ItemEntity item = new ItemEntity(
                            rs.getInt("id_item"),
                            rs.getString("nome"),
                            rs.getString("abreviacao"),
                            rs.getString("url"),
                            rs.getInt("id_funcionario"),
                            rs.getBoolean("favorito"));

                    items.add(item);
                }
                if (items.isEmpty()) {
                    return Optional.empty();
                }
                return Optional.of(items);
            }
        }
    }

    public void create(ItemEntity item) throws SQLException {
        String sql = "INSERT INTO item (nome, abreviacao, url, id_funcionario, favorito) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getAbreviacao());
            stmt.setString(3, item.getUrl());
            stmt.setInt(4, item.getId_funcionario());
            stmt.setBoolean(5, item.getFavorito());

            stmt.executeUpdate();
        }
    }

    public void delete(Long id_item) throws SQLException {
        String sql = "DELETE FROM item WHERE id_item = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id_item);
            stmt.executeUpdate();
        }
    }

    public void setFavorito(Long id_item, boolean favorito) throws SQLException {
        String sql = "UPDATE item SET favorito = ? WHERE id_item = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, favorito);
            stmt.setLong(2, id_item);
            stmt.executeUpdate();
        }
    }

    public Optional<ItemEntity> findById(Long id_item) throws SQLException {
        String sql = "SELECT id_item, nome, abreviacao, url, id_funcionario, favorito FROM item WHERE id_item = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id_item);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ItemEntity item = new ItemEntity(
                            rs.getInt("id_item"),
                            rs.getString("nome"),
                            rs.getString("abreviacao"),
                            rs.getString("url"),
                            rs.getInt("id_funcionario"),
                            rs.getBoolean("favorito"));
                    return Optional.of(item);
                }
            }
        }
        return Optional.empty();
    }
}
