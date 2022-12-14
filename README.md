# OliveiraTrade
Projeto feito para o processo de seleção de trainees da EveryMind.
Consiste em um pequeno sistema de login feito em Java, Bootstrap 5 e MYSQL.

#### SCRIPTS PARA A CRIAÇÃO DO BANCO DE DADOS ####

DELIMITER $
CREATE DEFINER=`root`@`localhost` TRIGGER `Tgr_login_Insert` AFTER INSERT ON `clientes` FOR EACH ROW BEGIN
	INSERT INTO login VALUES(new.cpf,new.idCliente, new.email, new.senha);
END
DELIMITER $

CREATE TABLE `clientes` (
  `idCliente` double NOT NULL,
  `email` varchar(200) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(12) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `endereco` varchar(2000) NOT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `login` (
  `idLogin` varchar(12) NOT NULL,
  `idCliente` double NOT NULL,
  `usuario` varchar(200) NOT NULL,
  `senha` varchar(20) NOT NULL,
  PRIMARY KEY (`idLogin`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

##########
