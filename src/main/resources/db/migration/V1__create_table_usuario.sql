CREATE TABLE tb_usuario (
 id BIGSERIAL primary key,
 username varchar(100) not null,
 password varchar(250) not null,
);