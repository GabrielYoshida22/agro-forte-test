CREATE TABLE person_physical
(
    id              UUID PRIMARY KEY                 DEFAULT gen_random_uuid() NOT NULL,
    nome            VARCHAR(255)                     NOT NULL,
    email           VARCHAR(255)                     NOT NULL,
    celular         VARCHAR(50)                      NOT NULL,
    nacionalidade   VARCHAR(100)                     NOT NULL,
    cpf             VARCHAR(14)                      NOT NULL UNIQUE,
    rg              VARCHAR(20),
    marital_status  VARCHAR(20),
    genero          VARCHAR(20),
    data_nascimento DATE,
    nome_mae        VARCHAR(255),
    criado_em       TIMESTAMP WITHOUT TIME ZONE     DEFAULT NOW()
);
