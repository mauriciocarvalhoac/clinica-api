CREATE TABLE tb_paciente (
    id BIGSERIAL primary key,
    nome varchar(255) not null,
    cpf varchar(11) not null,
    email varchar(100),
    telefone varchar(11),
    celular varchar(11) not null,

    cep varchar(10),
    logradouro varchar(100),
    numero varchar(10),
    bairro varchar(100),
    cidade varchar(100),
    estado varchar(100)
);

