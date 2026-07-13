-- MySQL dump 10.13  Distrib 9.7.1, for Linux (x86_64)
--
-- Host: localhost    Database: GestaoProfessor
-- ------------------------------------------------------
-- Server version	9.7.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'e463015e-6c1e-11f1-8ea1-e2754903d4c5:1-371';

--
-- Current Database: `GestaoProfessor`
--

/*!40000 DROP DATABASE IF EXISTS `GestaoProfessor`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `GestaoProfessor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `GestaoProfessor`;

--
-- Table structure for table `Campo`
--

DROP TABLE IF EXISTS `Campo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Campo` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Classificacao`
--

DROP TABLE IF EXISTS `Classificacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Classificacao` (
  `cod_Campo` int NOT NULL,
  `cod_Qualificacao` int NOT NULL,
  PRIMARY KEY (`cod_Campo`,`cod_Qualificacao`),
  KEY `fk_cod_Qualificacao` (`cod_Qualificacao`),
  CONSTRAINT `fk_cod_Qualificacao` FOREIGN KEY (`cod_Qualificacao`) REFERENCES `Qualificacao` (`cod_Quali`),
  CONSTRAINT `fk_codigo_campo` FOREIGN KEY (`cod_Campo`) REFERENCES `Campo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Coordenador`
--

DROP TABLE IF EXISTS `Coordenador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Coordenador` (
  `cod_Formador` int NOT NULL,
  PRIMARY KEY (`cod_Formador`),
  CONSTRAINT `fk_cod_Formador` FOREIGN KEY (`cod_Formador`) REFERENCES `Formador` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Diretor_Turma`
--

DROP TABLE IF EXISTS `Diretor_Turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Diretor_Turma` (
  `cod_Formador` int NOT NULL,
  PRIMARY KEY (`cod_Formador`),
  CONSTRAINT `Diretor_Turma_ibfk_1` FOREIGN KEY (`cod_Formador`) REFERENCES `Formador` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Formador`
--

DROP TABLE IF EXISTS `Formador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Formador` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `apelido` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `genero` varchar(40) NOT NULL,
  `estadoCivil` varchar(40) NOT NULL,
  `contacto` int NOT NULL,
  `salario` int NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `contacto_Chave_candidata` (`contacto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Log`
--

DROP TABLE IF EXISTS `Log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Log` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `id_Usuario` int NOT NULL,
  `acao` varchar(100) NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `data` datetime NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_id_Usuario` (`id_Usuario`),
  CONSTRAINT `fk_id_Usuario` FOREIGN KEY (`id_Usuario`) REFERENCES `Usuario` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Nivel`
--

DROP TABLE IF EXISTS `Nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Nivel` (
  `codigo` int NOT NULL,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Perfil`
--

DROP TABLE IF EXISTS `Perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Perfil` (
  `id` int NOT NULL,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

insert into Perfil values (1,"Administrador");
insert into Perfil values (2,"Operador");
insert into Perfil values (3,"Super Operador");
insert into Perfil values (4,"Auditor");

--
-- Table structure for table `Quali_Nivel`
--

DROP TABLE IF EXISTS `Quali_Nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Quali_Nivel` (
  `cod_Quali` int NOT NULL,
  `cod_Nivel` int NOT NULL,
  PRIMARY KEY (`cod_Quali`,`cod_Nivel`),
  KEY `fk_codigo_Nivel` (`cod_Nivel`),
  CONSTRAINT `fk_codigo_Nivel` FOREIGN KEY (`cod_Nivel`) REFERENCES `Nivel` (`codigo`),
  CONSTRAINT `fk_codigo_Quali` FOREIGN KEY (`cod_Quali`) REFERENCES `Qualificacao` (`cod_Quali`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Qualificacao`
--

DROP TABLE IF EXISTS `Qualificacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Qualificacao` (
  `cod_Quali` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(60) NOT NULL,
  `cod_Coordenador` int NOT NULL,
  PRIMARY KEY (`cod_Quali`),
  KEY `fk_cod_Coordenador` (`cod_Coordenador`),
  CONSTRAINT `fk_cod_Coordenador` FOREIGN KEY (`cod_Coordenador`) REFERENCES `Coordenador` (`cod_Formador`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Sala`
--

DROP TABLE IF EXISTS `Sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sala` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `designacao` varchar(20) NOT NULL,
  `tipo_sala` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Turma`
--

DROP TABLE IF EXISTS `Turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Turma` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `ano_lectivo` int NOT NULL,
  `turno` varchar(60) NOT NULL,
  `id_Diretor_Turma` int NOT NULL,
  `id_Qualificacao` int NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_id_Diretor` (`id_Diretor_Turma`),
  KEY `fk_id_Qualificacao` (`id_Qualificacao`),
  CONSTRAINT `fk_id_Diretor` FOREIGN KEY (`id_Diretor_Turma`) REFERENCES `Diretor_Turma` (`cod_Formador`),
  CONSTRAINT `fk_id_Qualificacao` FOREIGN KEY (`id_Qualificacao`) REFERENCES `Qualificacao` (`cod_Quali`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuario` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `idPerfil` int NOT NULL,
  `nome` varchar(100) NOT NULL,
  `username` varchar(60) NOT NULL,
  `apelido` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `primeiroAcesso` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `username` (`username`),
  KEY `idPerfil` (`idPerfil`),
  CONSTRAINT `Usuario_ibfk_1` FOREIGN KEY (`idPerfil`) REFERENCES `Perfil` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
insert into Usuario (idPerfil,nome,username,apelido,password,primeiroAcesso) values(1,"SuperAdmin","Admin","ADMINISTRADOR","Admin123",1);
-- Dump completed on 2026-07-12 11:06:50
