package router;

import controller.FuncionarioController;
import controller.UserController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entity.FuncionarioEntity;
import model.vo.FuncionarioVO;
import model.vo.UserCreateVO;
import model.vo.UserUpdateVO;

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioRouter {

    @Inject
    FuncionarioController funcionarioController;

    @GET
    public Response getAll() {
        return funcionarioController.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return funcionarioController.getUserById(id);
    }

    @POST
    public Response create(FuncionarioEntity funcionario) {
        return funcionarioController.createUser(funcionario);
    }

    //@PUT
    //@Path("/{id}")
    //public Response update(@PathParam("id") Long id, UserUpdateVO user) {
    //    return funcionarioController.updateUser(id, user);
    //}

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return funcionarioController.deleteFuncionario(id);
    }
}
