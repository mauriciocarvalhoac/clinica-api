CREATE TABLE tb_paciente (
    id BIGSERIAL primary key,
    nome varchar(255) not null,
    rg varchar(15),
    cpf varchar(11) not null,
    genero varchar(1),
    data_nascimento date,

    email varchar(100),
    celular varchar(11) not null,
    telefone varchar(11),

    cep varchar(10),
    logradouro varchar(100),
    numero varchar(10),
    bairro varchar(100),
    cidade varchar(100),
    estado varchar(100)
);