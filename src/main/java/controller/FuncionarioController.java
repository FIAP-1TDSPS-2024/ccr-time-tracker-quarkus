package controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.FuncionarioBO;

import model.entity.FuncionarioEntity;
import model.entity.ResponseEntity;
import model.vo.FuncionarioVO;
import java.util.List;

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FuncionarioController {

    @Inject
    FuncionarioBO funcionarioBO;
    ResponseEntity re = new ResponseEntity();

    public Response getAllUsers() {
        try {
            List<FuncionarioVO> funcionarios = funcionarioBO.getAllUsers();
            return re.OK(funcionarios);
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro consultando funcionários: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response getUserById(Long id) {
        try {
            return funcionarioBO.getUserById(id)
                    .map(user -> re.OK(user))
                    .orElse(re.NotFound());
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro consultando funcionários: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response createUser(FuncionarioEntity funcionario) {
        try {
            FuncionarioVO created = funcionarioBO.createUser(funcionario);
            return re.Created(created);
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro criando funcionário: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response deleteFuncionario(Long id) {
        try {
            if (funcionarioBO.deleteUser(id)) {
                return re.NoContent();
            }
            return re.NotFound();
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro deletando funcionário: " + e.getMessage());

            return re.InternalServerError();
        }
    }

    public Response login(String email, String senha) {
        try {
            FuncionarioVO funcionario = funcionarioBO.login(email, senha);
            if (funcionario != null) {
                return re.OK(funcionario);
            } else {
                return re.Unauthorized();
            }
        } catch (IllegalArgumentException e) {
            return re.BadRequest(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());

            return re.InternalServerError();
        }
    }

}
