package model.vo;

public class TmpResponseVO {
    private String tempoMedio;
    private String tempoTotal;
    private String diferencaMes;
    private String diferencaAno;

    public TmpResponseVO() {
    }

    public TmpResponseVO(String tempoMedio, String tempoTotal, String diferencaMes, String diferencaAno) {
        this.tempoMedio = tempoMedio;
        this.tempoTotal = tempoTotal;
        this.diferencaMes = diferencaMes;
        this.diferencaAno = diferencaAno;
    }

    public String getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(String tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public String getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(String tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public String getDiferencaMes() {
        return diferencaMes;
    }

    public void setDiferencaMes(String diferencaMes) {
        this.diferencaMes = diferencaMes;
    }

    public String getDiferencaAno() {
        return diferencaAno;
    }

    public void setDiferencaAno(String diferencaAno) {
        this.diferencaAno = diferencaAno;
    }
}
