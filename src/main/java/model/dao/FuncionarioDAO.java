package model.dao;

import config.DatabaseConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.entity.FuncionarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FuncionarioDAO {

    @Inject
    DatabaseConfig databaseConfig;

    public List<FuncionarioEntity> findAll() throws SQLException {
        List<FuncionarioEntity> funcionarios = new ArrayList<>();
        String sql = "SELECT id_funcionario, nome, cpf, cargo, email, senha, acesso FROM funcionario";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                funcionarios.add(new FuncionarioEntity(
                        rs.getLong("id_funcionario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cargo"),
                        rs.getInt("acesso")));
            }
        }
        return funcionarios;
    }

    public Optional<FuncionarioEntity> findByEmail(String email) throws SQLException {
        String sql = "SELECT id_funcionario, nome, cpf, cargo, email, senha, acesso FROM funcionario WHERE email = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FuncionarioEntity funcionario = new FuncionarioEntity(
                            rs.getLong("id_funcionario"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("cargo"),
                            rs.getInt("acesso"));
                    return Optional.of(funcionario);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<FuncionarioEntity> findById(Long id) throws SQLException {
        String sql = "SELECT id_funcionario, nome, cpf, cargo, email, senha, acesso FROM funcionario WHERE id_funcionario = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FuncionarioEntity funcionario = new FuncionarioEntity(
                            rs.getLong("id_funcionario"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("cargo"),
                            rs.getInt("acesso"));
                    return Optional.of(funcionario);
                }
            }
        }
        return Optional.empty();
    }

    public FuncionarioEntity create(FuncionarioEntity funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome, cpf, cargo, email, senha, acesso) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, new String[] { "id_funcionario" })) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setInt(6, funcionario.getPermissao());

            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    funcionario.setId_funcionario(rs.getLong(1));
                }
                return funcionario;
            }
        }
    }

    public boolean update(FuncionarioEntity funcionario) throws SQLException {
        String sql = "UPDATE funcionarios SET nome = ?, cpf = ?, cargo = ?, email = ?, senha = ?, acesso = ? WHERE id_funcionario = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setInt(6, funcionario.getPermissao());
            stmt.setLong(7, funcionario.getId_funcionario());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
