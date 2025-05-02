package model.entity;

import java.util.List;

public class LinhaEntity {
    private int id_linha;
    private String nome;
    private String sigla;
    private int numero;
    private List<EstacaoEntity> estacoes;

    public LinhaEntity(int id_linha, String nome, String sigla, int numero) {
        this.id_linha = id_linha;
        this.nome = nome;
        this.sigla = sigla;
        this.numero = numero;
    }

    public int getId_linha() {
        return id_linha;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public int getNumero() {
        return numero;
    }

    public List<EstacaoEntity> getEstacoes() {
        return estacoes;
    }
}
