CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE test.beneficiario (
    id_beneficiario UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    data_inclusao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL
);