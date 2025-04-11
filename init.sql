-- Criar tipo ENUM para turno
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'turno_enum') THEN
        CREATE TYPE turno_enum AS ENUM ('MANHA', 'TARDE', 'NOITE');
    END IF;
END$$;

-- Criar tabela cursos
CREATE TABLE cursos (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    sigla VARCHAR(2),
    turno turno_enum
);

-- Criar tabela users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    ativo BOOLEAN DEFAULT TRUE,
    curso_id INTEGER REFERENCES cursos(id)
);

-- Inserir usuário com senha BCrypt
INSERT INTO users (name, email, password)
VALUES ('Igor', 'igorigor@alu.ufc.br', '$2a$12$NMgLwu8bqUNT5sZjQPYfXehBWCKRJp4soaRtcZ6cFmnYNZD/o1fiu');