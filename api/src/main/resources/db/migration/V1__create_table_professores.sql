create table professores(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    idade varChar(20) not null,
    altura varChar(20) not null,
    email varchar(100) not null unique,

    primary key(id)

);