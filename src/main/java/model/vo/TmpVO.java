package model.vo;

import java.time.LocalDate;
import java.util.List;

public class TmpVO {
    private int linhaId;
    private List<Integer> estacaoIds;
    private LocalDate data;

    public TmpVO() {
    }

    public TmpVO(int linhaId, List<Integer> estacaoIds, LocalDate data) {
        this.linhaId = linhaId;
        this.estacaoIds = estacaoIds;
        this.data = data;
    }

    public int getLinhaId() {
        return linhaId;
    }

    public void setLinhaId(int linhaId) {
        this.linhaId = linhaId;
    }

    public List<Integer> getEstacaoIds() {
        return estacaoIds;
    }

    public void setEstacaoIds(List<Integer> estacaoIds) {
        this.estacaoIds = estacaoIds;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
