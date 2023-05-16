CREATE TABLE IF NOT EXISTS pauta (
    id SERIAL PRIMARY KEY,
    associado_id BIGINT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS sessao (
    id SERIAL PRIMARY KEY,
    pauta_id BIGINT REFERENCES pauta(id) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    closed BOOLEAN,
    ends_in TIMESTAMP NOT NULL,
    votos JSONB
);

CREATE TABLE IF NOT EXISTS voto (
    sessao_id BIGINT REFERENCES sessao(id) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    valor VARCHAR(3) NOT NULL
);
