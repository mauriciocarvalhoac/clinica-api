create table tb_medico (
 id bigint not null auto_increment,
 nome varchar(255),
 cpf varchar(11),
 email varchar(100),
 telefone varchar(11),
 celular varchar(11),
 primary key (id)
);