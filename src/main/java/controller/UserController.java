package controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.UserBO;
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
        try {
            List<UserResponseVO> users = userBO.getAllUsers();
            return Response.ok(users).build();
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    public Response getUserById(Long id) {
        try {
            return userBO.getUserById(id)
                    .map(user -> Response.ok(user).build())
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    public Response createUser(UserCreateVO user) {
        try {
            UserResponseVO created = userBO.createUser(user);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    public Response updateUser(Long id, UserUpdateVO user) {
        try {
            return userBO.updateUser(id, user)
                    .map(updated -> Response.ok(updated).build())
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    public Response deleteUser(Long id) {
        try {
            if (userBO.deleteUser(id)) {
                return Response.noContent().build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}