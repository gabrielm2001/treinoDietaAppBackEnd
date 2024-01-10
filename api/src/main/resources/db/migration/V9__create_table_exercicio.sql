CREATE TABLE exercicios(

    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,


    series VARCHAR(30) NOT NULL,
    repeticoes VARCHAR(30) NOT NULL,
    tecnica VARCHAR(300) NOT NULL,
    detalhes VARCHAR(300) NOT NULL,
    nome_exercicio VARCHAR(100) NOT NULL,

    ativo BOOLEAN NOT NULL,
    treino_id bigint unsigned NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(treino_id) REFERENCES treinos(id)


);