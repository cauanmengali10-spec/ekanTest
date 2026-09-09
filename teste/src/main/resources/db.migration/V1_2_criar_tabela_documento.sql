CREATE TABLE test.documento
    id_documento UUID PRIMARY KEY,
    id_beneficiario UUID NOT NULL REFERENCES test.beneficiario(id_beneficiario),
    tipo_documento VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_inclusao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL
);