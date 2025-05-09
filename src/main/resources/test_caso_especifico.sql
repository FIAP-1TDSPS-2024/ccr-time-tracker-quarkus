-- Test data specifically for linhaId=1, estacaoIds=[1,2], data="2025-05-01"
-- This will ensure that when you run the exact query with these parameters, you get results

-- Make sure the stations exist
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Osasco', 'OS', 'Rua A, 100');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Presidente Altino', 'PA', 'Rua B, 200');

-- Make sure the linha_estacao relationship exists
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 1);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 2);

-- Add more test data for linha 1 (Diamante) between estacoes 1 and 2 on 2025-05-01
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-05-01 08:00:00', TIMESTAMP '2025-05-01 08:06:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 1, TIMESTAMP '2025-05-01 08:30:00', TIMESTAMP '2025-05-01 08:36:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-05-01 09:00:00', TIMESTAMP '2025-05-01 09:05:30', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 1, TIMESTAMP '2025-05-01 09:30:00', TIMESTAMP '2025-05-01 09:35:45', 1);

-- Add previous month test data
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-04-01 08:00:00', TIMESTAMP '2025-04-01 08:07:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 1, TIMESTAMP '2025-04-01 08:30:00', TIMESTAMP '2025-04-01 08:38:00', 1);

-- Add previous year test data
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2024-05-01 08:00:00', TIMESTAMP '2024-05-01 08:09:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 1, TIMESTAMP '2024-05-01 08:30:00', TIMESTAMP '2024-05-01 08:40:00', 1);
