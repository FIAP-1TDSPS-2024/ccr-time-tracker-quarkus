package model.entity;

import java.util.ArrayList;

public class FuncionarioEntity {

    private Long id_funcionario;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String cargo;
    private int permissao;
    private ArrayList<ItemEntity> itens = new ArrayList<ItemEntity>();

    public FuncionarioEntity(Long id_funcionario, String nome, String cpf, String email, String senha, String cargo,
            int permissao) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.permissao = permissao;
    }

    public String getCargo() {
        return cargo;
    }

    protected void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setId_funcionario(Long id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Long getId_funcionario() {
        return id_funcionario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
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

    public int getPermissao() {
        return permissao;
    }

    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }

    public ArrayList<ItemEntity> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemEntity> itens) {
        this.itens = itens;
    }

    public String toString() {
        return "FuncionarioEntity [id_funcionario=" + id_funcionario + ", nome=" + nome + ", cpf=" + cpf + ", email="
                + email + ", senha=" + senha + ", cargo=" + cargo + ", permissao=" + permissao + "]";
    }
}
