package model.bo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.dao.UserDAO;
import model.vo.UserCreateVO;
import model.entity.UserEntity;
import model.vo.UserResponseVO;
import model.vo.UserUpdateVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserBO {

    @Inject
    UserDAO userDAO;

    public List<UserResponseVO> getAllUsers() throws SQLException {
        return userDAO.findAll()
                .stream()
                .map(UserResponseVO::new)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseVO> getUserById(Long id) throws SQLException {
        return userDAO.findById(id)
                .map(UserResponseVO::new);
    }

    public UserResponseVO createUser(UserCreateVO createVO) throws SQLException {
        // Add business validation here
        if (createVO.getName() == null || createVO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (createVO.getEmail() == null || createVO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!createVO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (createVO.getPassword() == null || createVO.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (createVO.getPassword().length() < 8) {
            throw new IllegalArgumentException("Invalid password");
        }

        UserEntity entity = createVO.toEntity();
        userDAO.create(entity);

        return new UserResponseVO(entity);
    }

    public Optional<UserResponseVO> updateUser(Long id, UserUpdateVO updateVO) throws SQLException {
        // Add business validation here
        if (updateVO.getName() == null || updateVO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (updateVO.getEmail() == null || updateVO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!updateVO.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (updateVO.getPassword() != null && !updateVO.getPassword().trim().isEmpty()) {
            if (updateVO.getPassword().length() < 8) {
                throw new IllegalArgumentException("Invalid password");
            }
        }

        Optional<UserEntity> existingUser = userDAO.findById(id);
        if (existingUser.isEmpty()) {
            return Optional.empty();
        }

        UserEntity entityToUpdate = updateVO.toEntity(id);
        if (updateVO.getPassword() == null || updateVO.getPassword().trim().isEmpty()) {
            entityToUpdate.setPassword(existingUser.get().getPassword());
        }

        if (userDAO.update(entityToUpdate)) {
            return Optional.of(new UserResponseVO(entityToUpdate));
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return userDAO.delete(id);
    }
}