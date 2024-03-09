CREATE TABLE treinos(
   id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
   tempo_treino VARCHAR(100) NOT NULL,
   nome_treino VARCHAR(100) NOT NULL,
   ficha_id bigint unsigned NOT NULL,
   ativo BOOLEAN NOT NULL,

   PRIMARY KEY(id),
   FOREIGN KEY(ficha_id) REFERENCES fichas(id)

);

