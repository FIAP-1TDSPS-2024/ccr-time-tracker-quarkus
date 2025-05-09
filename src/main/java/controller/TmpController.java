package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.TmpBO;
import model.entity.EstacaoEntity;
import model.entity.LinhaEntity;
import model.entity.ResponseEntity;
import model.vo.TmpResponseVO;
import model.vo.TmpVO;

import java.sql.SQLException;
import java.util.List;

@Path("/tmp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TmpController {

    @Inject
    TmpBO tmpBO;    
    
    @GET
    @Path("/linhas")
    public Response getLinhas() {
        try {
            List<LinhaEntity> linhas = tmpBO.getLinhas();
            return Response.ok(new ResponseEntity("Linhas recuperadas com sucesso", "0000", "success", linhas)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseEntity("Erro ao recuperar linhas: " + e.getMessage(), "0001", "error", null))
                    .build();
        }
    }    
    
    @GET
    @Path("/estacoes/{linhaId}")
    public Response getEstacoesByLinha(@PathParam("linhaId") int linhaId) {
        try {
            List<EstacaoEntity> estacoes = tmpBO.getEstacoesByLinha(linhaId);
            return Response.ok(new ResponseEntity("Estações recuperadas com sucesso", "0000", "success", estacoes)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseEntity("Erro ao recuperar estações: " + e.getMessage(), "0001", "error", null))
                    .build();
        }
    }    
    
    @POST
    @Path("/calcular")
    public Response calcularTempo(TmpVO tmpVO) {
        try {
            TmpResponseVO responseVO = tmpBO.calcularTempo(tmpVO);
            return Response.ok(new ResponseEntity("Cálculo realizado com sucesso", "0000", "success", responseVO)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseEntity("Erro ao calcular tempo: " + e.getMessage(), "0001", "error", null))
                    .build();
        }
    }
}
