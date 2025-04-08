package controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.UserBO;
import model.entity.ResponseEntity;
import model.vo.UserCreateVO;
import model.vo.UserResponseVO;
import model.vo.UserUpdateVO;

import java.sql.SQLException;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserController {

    @Inject
    UserBO userBO;

    public Response getAllUsers() {
        ResponseEntity re = new ResponseEntity();
        try {
            List<UserResponseVO> users = userBO.getAllUsers();
            return re.OK(users);
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());

            return re.InternalServerError();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        }
    }

    public Response getUserById(Long id) {
        ResponseEntity re = new ResponseEntity();
        try {
            return userBO.getUserById(id)
                    .map(user -> re.OK(user))
                    .orElse(re.NotFound());
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());

            return re.InternalServerError();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        }
    }

    public Response createUser(UserCreateVO user) {
        ResponseEntity re = new ResponseEntity();
        try {
            UserResponseVO created = userBO.createUser(user);
            return re.Created(created);
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());

            return re.InternalServerError();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        }
    }

    public Response updateUser(Long id, UserUpdateVO user) {
        ResponseEntity re = new ResponseEntity();
        try {
            return userBO.updateUser(id, user)
                    .map(updated -> re.OK(updated))
                    .orElse(re.NotFound());
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());

            return re.InternalServerError();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        }
    }

    public Response deleteUser(Long id) {
        ResponseEntity re = new ResponseEntity();
        try {
            if (userBO.deleteUser(id)) {
                return re.NoContent();
            }
            return re.NotFound();
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());

            return re.InternalServerError();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        }
    }
}