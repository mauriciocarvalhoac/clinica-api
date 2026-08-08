CREATE TABLE tb_medico (
 id BIGSERIAL primary key,
 nome varchar(255) not null,
 cpf varchar(11) not null,
 email varchar(100),
 telefone varchar(11) not null,
 celular varchar(11)
);