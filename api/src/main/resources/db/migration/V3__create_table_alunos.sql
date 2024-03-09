CREATE TABLE alunos(
  id bigint unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  idade VARCHAR(20) NOT NULL,
  altura VARCHAR(20) NOT NULL,
  email varchar(100) NOT NULL UNIQUE,
  peso VARCHAR(20) NOT NULL,
  projeto varchar(20) NOT NULL,
  objetivo varchar(20) NOT NULL,
  agua varchar(20),
  tbm varchar(20),
  professor_id bigint unsigned NOT NULL,

  PRIMARY KEY(id),
  FOREIGN KEY(professor_id) REFERENCES professores(id)
);
