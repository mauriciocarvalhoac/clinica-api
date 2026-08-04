create table tb_paciente (
 id bigint not null auto_increment,
 nome varchar(255),
 cpf varchar(255),
 email varchar(255),
 telefone varchar(255),
 celular varchar(255),
 primary key (id)
);