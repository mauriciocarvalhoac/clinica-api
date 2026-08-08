CREATE TABLE tb_usuario (
 id BIGSERIAL primary key,
 username varchar(100) not null,
 password varchar(20) not null,
);