create table usuario(
    id bigint not null auto_increment,
    nome varchar(50) not null ,
    email varchar(50) not null ,
    primary key(id)
);

insert into usuario(id, nome, email) VALUES ( 1, 'Gilberto', 'gcbrandao@gmail.com' );
