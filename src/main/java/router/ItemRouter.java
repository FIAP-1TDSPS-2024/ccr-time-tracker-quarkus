package router;

import controller.ItemController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entity.ItemEntity;

@Path("/itens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemRouter {

    @Inject
    ItemController itemController;

    @GET
    @Path("/funcionario/{funcionario_id}")
    public Response getByFuncionarioID(@PathParam("funcionario_id") Long funcionarioId) {
        return itemController.getByFuncionarioID(funcionarioId);
    }

    @POST
    public Response createItem(ItemEntity item) {
        return itemController.createItem(item);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") Long id) {
        return itemController.deleteItem(id);
    }

    @PUT
    @Path("/{id}/favoritar")
    public Response favoritarItem(@PathParam("id") Long id) {
        return itemController.favoriteItem(id);
    }
}
