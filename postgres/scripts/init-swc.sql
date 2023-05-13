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
    ends_in TIMESTAMP
);

CREATE TYPE tipo_voto AS ENUM ('SIM', 'NAO');
CREATE TABLE IF NOT EXISTS voto (
    sessao_id BIGINT REFERENCES sessao(id) NOT NULL,
    associado_id BIGINT NOT NULL,
    valor tipo_voto NOT NULL
);
