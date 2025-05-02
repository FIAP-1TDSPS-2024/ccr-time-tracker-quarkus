package model.bo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.dao.FuncionarioDAO;
import model.entity.FuncionarioEntity;
import model.vo.FuncionarioVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class FuncionarioBO {

    @Inject
    FuncionarioDAO funcionarioDAO;

    public List<FuncionarioVO> getAllUsers() throws SQLException {
        return funcionarioDAO.findAll()
                .stream()
                .map(FuncionarioVO::new)
                .collect(Collectors.toList());
    }

    public Optional<FuncionarioVO> getUserById(Long id) throws SQLException {
        return funcionarioDAO.findById(id)
                .map(FuncionarioVO::new);
    }

    public FuncionarioVO createUser(FuncionarioEntity funcionario) throws SQLException {
        // Add business validation here
        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF cannot be empty");
        }
        if (!funcionario.getCpf().matches("^\\d{11}$")) {
            throw new IllegalArgumentException("Invalid CPF format");
        }
        if (funcionario.getEmail() == null || funcionario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!funcionario.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (funcionario.getCargo() == null || funcionario.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
        if (funcionario.getPermissao() == 0) {
            throw new IllegalArgumentException("Permission cannot be empty");
        }
        if (funcionario.getSenha() == null || funcionario.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (funcionario.getSenha().length() < 8) {
            throw new IllegalArgumentException("Invalid password");
        }

        funcionarioDAO.create(funcionario);

        return new FuncionarioVO(funcionario);
    }

    public boolean deleteUser(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return funcionarioDAO.delete(id);
    }

    public FuncionarioVO login(String email, String senha) throws SQLException {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        Optional<FuncionarioEntity> funcionario = funcionarioDAO.findByEmail(email);

        System.out.println("Funcionario: " + funcionario.toString());
        if (!funcionario.isEmpty() && funcionario.get().getSenha().equals(senha)) {
            return funcionario.map(FuncionarioVO::new).orElse(null);
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }
}
