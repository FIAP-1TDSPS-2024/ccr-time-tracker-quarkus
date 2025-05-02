CREATE TABLE funcionario(
    id_funcionario number(10) GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_id_funcionario PRIMARY KEY,
    nome varchar(50) CONSTRAINT funcionario_nome_nn NOT NULL,
    cpf varchar(11) CONSTRAINT funcionario_cpf_nn NOT NULL,
    cargo varchar(50) CONSTRAINT funcionario_cargo_nn NOT NULL,
    email varchar(50) CONSTRAINT funcionario_email_nn NOT NULL,
    senha varchar(50) CONSTRAINT funcionario_senha_nn NOT NULL,
    acesso number(1) CONSTRAINT funcionario_acesso_nn NOT NULL,
    id_funcionario_admin number(10) CONSTRAINT fk_id_funcionario_admin REFERENCES funcionario(id_funcionario)
);

CREATE TABLE item(
    id_item number(10) GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_id_item PRIMARY KEY,
    nome varchar(50) CONSTRAINT item_nome_nn NOT NULL,
    abreviacao varchar(10) CONSTRAINT item_abreviacao_nn NOT NULL,
    url varchar(100) CONSTRAINT item_url_nn NOT NULL,
    favorito number(1) CONSTRAINT item_favorito_nn NOT NULL,
    id_funcionario number(10) CONSTRAINT fk_id_funcionario REFERENCES funcionario(id_funcionario)
);
