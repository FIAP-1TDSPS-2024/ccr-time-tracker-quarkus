package model.entity;

public class ItemEntity {
    private int id_item;
    private String nome;
    private String abreviacao;
    private String url;
    private boolean favorito;
    private int id_funcionario;

    public ItemEntity(int id_item, String nome, String abreviacao, String url, int id_funcionario, boolean favorito) {
        this.id_item = id_item;
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.url = url;
        this.favorito = favorito;
        this.id_funcionario = id_funcionario;
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

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

}
