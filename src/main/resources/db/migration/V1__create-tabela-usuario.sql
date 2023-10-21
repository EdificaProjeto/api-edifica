CREATE TABLE usuario (

    id TEXT PRIMARY KEY NOT NULL,
    nome TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    papel TEXT NOT NULL

);

CREATE TABLE gestao (

    id SERIAL PRIMARY KEY NOT NULL,
    nome TEXT NOT NULL,
    status TEXT NOT NULL,
    tipo TEXT NOT NULL,
    usuario_id TEXT NOT NULL,

    CONSTRAINT fk_usuario_id
    FOREIGN KEY (usuario_id) references usuario(id)
    ON DELETE CASCADE

);

CREATE TABLE material (

    id SERIAL PRIMARY KEY NOT NULL,
    nome TEXT NOT NULL,
    quantidade INT NOT NULL,
    valor NUMERIC NOT NULL,
    gestao_id INTEGER NOT NULL,

    CONSTRAINT fk_gestao_id
    FOREIGN KEY (gestao_id) references gestao(id)

);