package model.vo;

import model.entity.ItemEntity;

public class ItemVO {
    private int id_item;
    private String nome;
    private String abreviacao;
    private String url;
    private boolean favorito;

    public ItemVO(ItemEntity entity) {
        this.nome = entity.getNome();
        this.abreviacao = entity.getAbreviacao();
        this.url = entity.getUrl();
        this.favorito = entity.getFavorito();
    }

    public ItemVO() {
    }

    public ItemVO(int id_item, String nome, String abreviacao, String url) {
        this.id_item = id_item;
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.url = url;
        this.favorito = false;
    }

    public int getId_item() {
        return id_item;
    }

    public String getNome() {
        return nome;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public String getUrl() {
        return url;
    }

    public boolean getFavorito() {
        return favorito;
    }

}
