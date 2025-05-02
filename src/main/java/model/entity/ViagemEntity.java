package model.entity;

import java.sql.Timestamp;

public class ViagemEntity {

    private int id_viagem;
    private EstacaoEntity estacaoEntityOrigem;
    private EstacaoEntity estacaoEntityDestino;
    private Timestamp dataInicio;
    private Timestamp dataFim;
    private LinhaEntity linhaEntity;
    private TremEntity tremEntity;


    public ViagemEntity(int id_viagem, EstacaoEntity estacaoEntityOrigem, EstacaoEntity estacaoEntityDestino, LinhaEntity linhaEntity, TremEntity tremEntity) {
        this.id_viagem = id_viagem;
        this.estacaoEntityOrigem = estacaoEntityOrigem;
        this.estacaoEntityDestino = estacaoEntityDestino;
        this.linhaEntity = linhaEntity;
        this.tremEntity = tremEntity;
    }

    public LinhaEntity getLinha() {
        return linhaEntity;
    }

    public TremEntity getTrem() {
        return tremEntity;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public EstacaoEntity getEstacaoOrigem() {
        return estacaoEntityOrigem;
    }

    public EstacaoEntity getEstacaoDestino() {
        return estacaoEntityDestino;
    }

    public EstacaoEntity getestacaoOrigem() {
        return estacaoEntityOrigem;
    }

    public EstacaoEntity getestacaoDestino() {
        return estacaoEntityDestino;
    }

    public void setEstacaoOrigem(EstacaoEntity estacaoEntityOrigem) {
        this.estacaoEntityOrigem = estacaoEntityOrigem;
    }

    public void setEstacaoDestino(EstacaoEntity estacaoEntityDestino) {
        this.estacaoEntityDestino = estacaoEntityDestino;
    }

    public Timestamp getDataInicio() {
        return dataInicio;
    }

    public Timestamp getDataFim() {
        return dataFim;
    }

    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Timestamp dataFim) {
        this.dataFim = dataFim;
    }
}
