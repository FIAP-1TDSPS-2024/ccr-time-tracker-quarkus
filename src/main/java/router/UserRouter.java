package router;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import controller.UserController;
import model.vo.UserCreateVO;
import model.vo.UserUpdateVO;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRouter {

    @Inject
    UserController userController;

    @GET
    public Response getAll() {
        return userController.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return userController.getUserById(id);
    }

    @POST
    public Response create(UserCreateVO user) {
        return userController.createUser(user);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserUpdateVO user) {
        return userController.updateUser(id, user);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return userController.deleteUser(id);
    }
}