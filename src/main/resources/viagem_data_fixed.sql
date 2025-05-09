-- SQL script to populate the viagem table with data for the calcularTempo method
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Osasco', 'OS', 'Rua A, 100');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Presidente Altino', 'PA', 'Rua B, 200');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Ceasa', 'CE', 'Rua C, 300');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Vila Lobos-Jaguaré', 'VLJ', 'Rua D, 400');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Cidade Universitária', 'CU', 'Rua E, 500');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Pinheiros', 'PI', 'Rua F, 600');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Hebraica-Rebouças', 'HR', 'Rua G, 700');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Cidade Jardim', 'CJ', 'Rua H, 800');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Vila Olímpia', 'VO', 'Rua I, 900');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Berrini', 'BE', 'Rua J, 1000');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Morumbi-Claro', 'MC', 'Rua K, 1100');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Granja Julieta', 'GJ', 'Rua L, 1200');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('João Dias', 'JD', 'Rua M, 1300');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Santo Amaro', 'SA', 'Rua N, 1400');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Socorro', 'SO', 'Rua O, 1500');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Jurubatuba-Senac', 'JS', 'Rua P, 1600');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Autódromo', 'AU', 'Rua Q, 1700');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Primavera-Interlagos', 'PII', 'Rua R, 1800');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Grajaú', 'GR', 'Rua S, 1900');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Bruno Covas-Mendes-Vila Natal', 'BCM', 'Rua T, 2000');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Júlio Prestes', 'JP', 'Rua U, 2100');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Palmeiras-Barra Funda', 'PBF', 'Rua V, 2200');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Lapa', 'LA', 'Rua W, 2300');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Domingos de Moraes', 'DM', 'Rua X, 2400');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Imperatriz Leopoldina', 'IL', 'Rua Y, 2500');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Comandante Sampaio', 'CS', 'Rua Z, 2600');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('Quitaúna', 'QU', 'Rua AA, 2700');
INSERT INTO estacao (nome, sigla, endereco) VALUES ('General Miguel Costa', 'GMC', 'Rua AB, 2800');

INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 1);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 2);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 3);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 4);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 5);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 6);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 7);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 8);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 9);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 10);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 11);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 12);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 13);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 14);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 15);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 16);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 17);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 18);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 19);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (2, 20);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 21);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 22);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 23);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 24);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 25);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 2);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 1);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 26);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 27);
INSERT INTO linha_estacao (id_linha, id_estacao) VALUES (1, 28);

-- Current Month (May 2025) - Linha 8 (Diamante)
-- Viagens sequenciais entre estações da linha 8
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (21, 22, TIMESTAMP '2025-05-01 07:00:00', TIMESTAMP '2025-05-01 07:12:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (22, 23, TIMESTAMP '2025-05-01 07:15:00', TIMESTAMP '2025-05-01 07:23:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (23, 24, TIMESTAMP '2025-05-01 07:25:00', TIMESTAMP '2025-05-01 07:33:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (24, 25, TIMESTAMP '2025-05-01 07:35:00', TIMESTAMP '2025-05-01 07:42:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (25, 2, TIMESTAMP '2025-05-01 07:44:00', TIMESTAMP '2025-05-01 07:52:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 1, TIMESTAMP '2025-05-01 07:54:00', TIMESTAMP '2025-05-01 08:00:00', 1);

-- Additional trips for the same day with similar timing
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (21, 22, TIMESTAMP '2025-05-01 08:00:00', TIMESTAMP '2025-05-01 08:11:00', 1); 

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (22, 23, TIMESTAMP '2025-05-01 08:15:00', TIMESTAMP '2025-05-01 08:22:00', 1); 

-- Current Month (May 2025) - Linha 9 (Esmeralda)
-- Viagens sequenciais entre estações da linha 9
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-05-01 07:00:00', TIMESTAMP '2025-05-01 07:05:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 3, TIMESTAMP '2025-05-01 07:07:00', TIMESTAMP '2025-05-01 07:12:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (3, 4, TIMESTAMP '2025-05-01 07:14:00', TIMESTAMP '2025-05-01 07:19:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (4, 5, TIMESTAMP '2025-05-01 07:21:00', TIMESTAMP '2025-05-01 07:26:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (5, 6, TIMESTAMP '2025-05-01 07:28:00', TIMESTAMP '2025-05-01 07:34:00', 2);

-- More May 2025 data for different days
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-05-02 07:00:00', TIMESTAMP '2025-05-02 07:06:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 3, TIMESTAMP '2025-05-02 07:08:00', TIMESTAMP '2025-05-02 07:14:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-05-03 07:00:00', TIMESTAMP '2025-05-03 07:05:30', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 3, TIMESTAMP '2025-05-03 07:07:30', TIMESTAMP '2025-05-03 07:13:00', 2);

-- Add data specifically for May 8, 2025 (current date)
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-05-08 07:00:00', TIMESTAMP '2025-05-08 07:04:30', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 3, TIMESTAMP '2025-05-08 07:06:30', TIMESTAMP '2025-05-08 07:11:00', 2);

-- Last Month (April 2025) - Similar patterns but with slightly longer times
-- Linha 8 - Diamante
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (21, 22, TIMESTAMP '2025-04-01 07:00:00', TIMESTAMP '2025-04-01 07:13:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (22, 23, TIMESTAMP '2025-04-01 07:15:00', TIMESTAMP '2025-04-01 07:24:30', 1);

-- Linha 9 - Esmeralda
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-04-01 07:00:00', TIMESTAMP '2025-04-01 07:06:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 3, TIMESTAMP '2025-04-01 07:08:00', TIMESTAMP '2025-04-01 07:14:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2025-04-08 07:00:00', TIMESTAMP '2025-04-08 07:06:30', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 3, TIMESTAMP '2025-04-08 07:08:30', TIMESTAMP '2025-04-08 07:14:30', 2);

-- Last Year (May 2024) - Similar patterns but with much longer times to show annual improvements
-- Linha 8 - Diamante
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (21, 22, TIMESTAMP '2024-05-01 07:00:00', TIMESTAMP '2024-05-01 07:15:00', 1);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (22, 23, TIMESTAMP '2024-05-01 07:17:00', TIMESTAMP '2024-05-01 07:27:00', 1);

-- Linha 9 - Esmeralda
INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2024-05-01 07:00:00', TIMESTAMP '2024-05-01 07:08:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 3, TIMESTAMP '2024-05-01 07:10:00', TIMESTAMP '2024-05-01 07:17:00', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (1, 2, TIMESTAMP '2024-05-08 07:00:00', TIMESTAMP '2024-05-08 07:07:30', 2);

INSERT INTO viagem (id_estacao_partida, id_estacao_destino, data_partida, data_chegada, id_linha)
VALUES (2, 3, TIMESTAMP '2024-05-08 07:09:30', TIMESTAMP '2024-05-08 07:16:30', 2);

-- Add trains for these trips
-- For May 2025 trips
INSERT INTO trem (id_viagem, status, numeracao) VALUES (6, 'ativo', 'T8001');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (7, 'ativo', 'T8002');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (8, 'ativo', 'T8003');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (9, 'ativo', 'T8004');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (10, 'ativo', 'T8005');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (11, 'ativo', 'T8006');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (12, 'ativo', 'T8007');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (13, 'ativo', 'T8008');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (14, 'ativo', 'T9001');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (15, 'ativo', 'T9002');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (16, 'ativo', 'T9003');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (17, 'ativo', 'T9004');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (18, 'ativo', 'T9005');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (19, 'ativo', 'T9006');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (20, 'ativo', 'T9007');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (21, 'ativo', 'T9008');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (22, 'ativo', 'T9009');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (23, 'ativo', 'T9010');

-- For April 2025 trips
INSERT INTO trem (id_viagem, status, numeracao) VALUES (24, 'ativo', 'T8009');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (25, 'ativo', 'T8010');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (26, 'ativo', 'T9011');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (27, 'ativo', 'T9012');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (28, 'ativo', 'T9013');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (29, 'ativo', 'T9014');

-- For May 2024 trips
INSERT INTO trem (id_viagem, status, numeracao) VALUES (30, 'inativo', 'T8011');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (31, 'inativo', 'T8012');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (32, 'inativo', 'T9015');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (33, 'inativo', 'T9016');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (34, 'inativo', 'T9017');
INSERT INTO trem (id_viagem, status, numeracao) VALUES (35, 'inativo', 'T9018');
