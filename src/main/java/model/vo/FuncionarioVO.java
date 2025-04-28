package model.vo;

import model.entity.FuncionarioEntity;
import model.entity.ItemEntity;

import java.util.ArrayList;

public class FuncionarioVO {
    private String nome;
    private String cpf;
    private String email;
    private String cargo;
    private int permissao;
    private ArrayList<ItemEntity> itens = new ArrayList<ItemEntity>();

    public FuncionarioVO(FuncionarioEntity entity) {
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.cargo = entity.getCargo();
        this.permissao = entity.getPermissao();
    }

    public FuncionarioVO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPermissao() {
        return permissao;
    }

    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public ArrayList<ItemEntity> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemEntity> itens) {
        this.itens = itens;
    }
}
