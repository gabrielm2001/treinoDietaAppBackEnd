CREATE TABLE fichas(
   id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
   nome_ficha VARCHAR(100) NOT NULL,

    aluno_id bigint unsigned,

   PRIMARY KEY(id),
   FOREIGN KEY(aluno_id) REFERENCES alunos(id)

);

