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

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserController {

    @Inject
    UserBO userBO;
    ResponseEntity re = new ResponseEntity();

    public Response getAllUsers() {
        try {
            List<UserResponseVO> users = userBO.getAllUsers();
            return re.OK(users);
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response getUserById(Long id) {
        try {
            return userBO.getUserById(id)
                    .map(user -> re.OK(user))
                    .orElse(re.NotFound());
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response createUser(UserCreateVO user) {
        try {
            UserResponseVO created = userBO.createUser(user);
            return re.Created(created);
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response updateUser(Long id, UserUpdateVO user) {
        try {
            return userBO.updateUser(id, user)
                    .map(updated -> re.OK(updated))
                    .orElse(re.NotFound());
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response deleteUser(Long id) {
        try {
            if (userBO.deleteUser(id)) {
                return re.NoContent();
            }
            return re.NotFound();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());

            return re.InternalServerError();
        }
    }
}