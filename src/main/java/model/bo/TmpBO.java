package model.bo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.dao.TmpDAO;
import model.entity.EstacaoEntity;
import model.entity.LinhaEntity;
import model.vo.TmpResponseVO;
import model.vo.TmpVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class TmpBO {

    @Inject
    TmpDAO tmpDAO;

    public List<LinhaEntity> getLinhas() throws SQLException {
        return tmpDAO.getLinhas();
    }

    public List<EstacaoEntity> getEstacoesByLinha(int linhaId) throws SQLException {
        return tmpDAO.getEstacoesByLinha(linhaId);
    }

    public TmpResponseVO calcularTempo(TmpVO tmpVO) throws SQLException {
        Map<String, Object> resultados = tmpDAO.calcularTempo(
                tmpVO.getLinhaId(), 
                tmpVO.getEstacaoIds(), 
                tmpVO.getData()
        );
        
        TmpResponseVO response = new TmpResponseVO();
        response.setTempoMedio((String) resultados.get("tempoMedio"));
        response.setTempoTotal((String) resultados.get("tempoTotal"));
        response.setDiferencaMes((String) resultados.get("diferencaMes"));
        response.setDiferencaAno((String) resultados.get("diferencaAno"));
        
        return response;
    }
}
