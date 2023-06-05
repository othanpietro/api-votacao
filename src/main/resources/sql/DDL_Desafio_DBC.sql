-- Os comandos devem ser executados na ordem de cima para baixo
-- Criacao da tabela pauta
CREATE TABLE `pauta` (
  `id` int NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Criacao da tabela usuario
CREATE TABLE `usuario` (
  `id` int NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Criacao da tabela secao_votacao
CREATE TABLE `secao_votacao` (
  `id` int NOT NULL,
  `data_criacao` datetime DEFAULT NULL,
  `data_finalizacao` datetime DEFAULT NULL,
  `id_pauta` int DEFAULT NULL,
  `resultado_enviado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk.pauta_idx` (`id_pauta`),
  CONSTRAINT `fk.pauta` FOREIGN KEY (`id_pauta`) REFERENCES `pauta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Criacao da tabela votacao
CREATE TABLE `votacao` (
  `id_usuario` int DEFAULT NULL,
  `id_secao` int DEFAULT NULL,
  `voto` char(1) DEFAULT NULL,
  `id` int NOT NULL,
  KEY `fk.secao_votacao_idx` (`id_secao`),
  KEY `fk.usuario_idx` (`id_usuario`),
  CONSTRAINT `fk.secao_votacao` FOREIGN KEY (`id_secao`) REFERENCES `secao_votacao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk.usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

