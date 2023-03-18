CREATE TABLE usuario (
id serial primary key,
nome varchar(200) NOT NULL,
email varchar(200) NOT NULL UNIQUE,
foto varchar(200) NOT NULL
);

CREATE TABLE tarefa (
id serial primary key,
titulo varchar(50) NOT NULL,
descricao varchar(200) NOT NULL,
criacao date NOT NULL,
status varchar(50) NOT NULL,
usuario int NOT NULL REFERENCES usuario(id)
);