package controller;

import java.util.ArrayList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import model.bo.FuncionarioBO;
import model.bo.ItemBO;
import model.entity.ItemEntity;
import model.entity.ResponseEntity;
import model.vo.ItemVO;

@ApplicationScoped
public class ItemController {

    @Inject
    FuncionarioBO funcionarioBO;
    @Inject
    ItemBO itemBO;

    ResponseEntity re = new ResponseEntity();

    public Response getByFuncionarioID(Long funcionarioId) {
        try {
            return itemBO.getByFuncionarioID(funcionarioId)
                    .map(item -> re.OK(item))
                    .orElse(re.OK(new ArrayList<ItemVO>()));
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro consultando itens: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response createItem(ItemEntity item) {
        try {
            itemBO.createItem(item);
            return re.Created(true);
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro criando item: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response deleteItem(Long id) {
        try {
            itemBO.deleteItem(id);
            return re.NoContent();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro deletando item: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response favoriteItem(Long id) {
        try {
            itemBO.favoriteItem(id);
            return re.NoContent();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro favoritando item: " + e.getMessage());

            return re.InternalServerError();
        }
    }
}
