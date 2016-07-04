-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema CONTROLE_ARMARIOS_DEV
--

CREATE DATABASE IF NOT EXISTS CONTROLE_ARMARIOS_DEV;
USE CONTROLE_ARMARIOS_DEV;

--
-- Definition of table `ALUGUEL_ARMARIO`
--

DROP TABLE IF EXISTS `ALUGUEL_ARMARIO`;
CREATE TABLE `ALUGUEL_ARMARIO` (
  `ID_ALUGUEL_ARMARIO` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_ARMARIO` bigint(20) NOT NULL,
  `ID_ALUNO` bigint(20) NOT NULL,
  `ID_USUARIO_RESPONSAVEL` bigint(20) NOT NULL,
  `DATA_ALUGUEL` date DEFAULT NULL,
  `DATA_DEVOLUCAO` date DEFAULT NULL,
  `FLG_DEVOLVIDO` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID_ALUGUEL_ARMARIO`),
  KEY `ID_ARMARIO` (`ID_ARMARIO`),
  KEY `ID_ALUNO` (`ID_ALUNO`),
  KEY `ID_USUARIO_RESPONSAVEL` (`ID_USUARIO_RESPONSAVEL`),
  CONSTRAINT `ALUGUEL_ARMARIO_ibfk_1` FOREIGN KEY (`ID_ARMARIO`) REFERENCES `ARMARIO` (`ID_ARMARIO`),
  CONSTRAINT `ALUGUEL_ARMARIO_ibfk_2` FOREIGN KEY (`ID_ALUNO`) REFERENCES `ALUNO` (`ID_ALUNO`),
  CONSTRAINT `ALUGUEL_ARMARIO_ibfk_3` FOREIGN KEY (`ID_USUARIO_RESPONSAVEL`) REFERENCES `USUARIO` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ALUGUEL_ARMARIO`
--

/*!40000 ALTER TABLE `ALUGUEL_ARMARIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ALUGUEL_ARMARIO` ENABLE KEYS */;


--
-- Definition of trigger `ALOCAR_ARMARIO_TRG`
--

DROP TRIGGER /*!50030 IF EXISTS */ `ALOCAR_ARMARIO_TRG`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `ALOCAR_ARMARIO_TRG` AFTER INSERT ON `ALUGUEL_ARMARIO` FOR EACH ROW BEGIN

   UPDATE ARMARIO
   SET FLG_OCUPADO = 1
   WHERE ID_ARMARIO = NEW.ID_ARMARIO;

END $$

DELIMITER ;

--
-- Definition of trigger `DESALOCAR_ARMARIO_TRG`
--

DROP TRIGGER /*!50030 IF EXISTS */ `DESALOCAR_ARMARIO_TRG`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `DESALOCAR_ARMARIO_TRG` AFTER UPDATE ON `ALUGUEL_ARMARIO` FOR EACH ROW BEGIN

IF(NEW.FLG_DEVOLVIDO = 1) THEN
   UPDATE ARMARIO
   SET FLG_OCUPADO = 0
   WHERE ID_ARMARIO = NEW.ID_ARMARIO;
END IF;
END $$

DELIMITER ;

--
-- Definition of table `ALUNO`
--

DROP TABLE IF EXISTS `ALUNO`;
CREATE TABLE `ALUNO` (
  `ID_ALUNO` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) DEFAULT NULL,
  `SEXO` char(1) DEFAULT NULL,
  `TELEFONE` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `FLG_ATIVO` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ID_ALUNO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ALUNO`
--

/*!40000 ALTER TABLE `ALUNO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ALUNO` ENABLE KEYS */;


--
-- Definition of table `ARMARIO`
--

DROP TABLE IF EXISTS `ARMARIO`;
CREATE TABLE `ARMARIO` (
  `ID_ARMARIO` bigint(20) NOT NULL AUTO_INCREMENT,
  `CHAVE` bigint(20) DEFAULT NULL,
  `DESCRICAO` varchar(25) DEFAULT NULL,
  `FLG_OCUPADO` bit(1) NOT NULL DEFAULT b'0',
  `FLG_ATIVO` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ID_ARMARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ARMARIO`
--

/*!40000 ALTER TABLE `ARMARIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ARMARIO` ENABLE KEYS */;


--
-- Definition of table `DB_TESTE`
--

DROP TABLE IF EXISTS `DB_TESTE`;
CREATE TABLE `DB_TESTE` (
  `ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DB_TESTE`
--

/*!40000 ALTER TABLE `DB_TESTE` DISABLE KEYS */;
INSERT INTO `DB_TESTE` (`ID`) VALUES 
 (1);
/*!40000 ALTER TABLE `DB_TESTE` ENABLE KEYS */;


--
-- Definition of table `LKP_TIPO_USUARIO`
--

DROP TABLE IF EXISTS `LKP_TIPO_USUARIO`;
CREATE TABLE `LKP_TIPO_USUARIO` (
  `ID_LKP_TIPO_USUARIO` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` varchar(50) DEFAULT NULL,
  `FLG_ATIVO` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ID_LKP_TIPO_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LKP_TIPO_USUARIO`
--

/*!40000 ALTER TABLE `LKP_TIPO_USUARIO` DISABLE KEYS */;
INSERT INTO `LKP_TIPO_USUARIO` (`ID_LKP_TIPO_USUARIO`,`DESCRICAO`,`FLG_ATIVO`) VALUES 
 (1,'ADMINISTRADOR',0x01),
 (2,'BIBLIOTECA',0x01),
 (3,'PROFESSOR',0x01),
 (4,'OUTROS',0x01);
/*!40000 ALTER TABLE `LKP_TIPO_USUARIO` ENABLE KEYS */;


--
-- Definition of table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
CREATE TABLE `USUARIO` (
  `ID_USUARIO` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_LKP_TIPO_USUARIO` bigint(20) NOT NULL,
  `NOME` varchar(100) DEFAULT NULL,
  `FLG_ATIVO` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ID_USUARIO`),
  KEY `ID_LKP_TIPO_USUARIO` (`ID_LKP_TIPO_USUARIO`),
  CONSTRAINT `USUARIO_ibfk_1` FOREIGN KEY (`ID_LKP_TIPO_USUARIO`) REFERENCES `LKP_TIPO_USUARIO` (`ID_LKP_TIPO_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USUARIO`
--

/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
