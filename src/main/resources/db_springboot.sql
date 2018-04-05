-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_springboot
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id`        INT(11)     NOT NULL AUTO_INCREMENT,
  `user_id`   INT(11)     NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_authority_unique` (`user_id`, `authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities`
  DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1, 1, 'ROLE_USER'), (3, 2, 'ROLE_ADMIN'), (2, 2, 'ROLE_USER');
/*!40000 ALTER TABLE `authorities`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `apellido`  VARCHAR(255) NOT NULL,
  `create_at` DATE         NOT NULL,
  `email`     VARCHAR(255) NOT NULL,
  `foto`      VARCHAR(255)          DEFAULT NULL,
  `nombre`    VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1c96wv36rk2hwui7qhjks3mvg` (`email`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 56
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes`
  DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1, 'Alvarez', '2017-08-28', 'mail1@preving.com', '', 'Andres'),
  (2, 'Perez', '2017-08-30', 'mail2@preving.com', '', 'Pepito'),
  (3, 'Sanchez', '2018-08-28', 'mail3@preving.com', '', 'Juan'),
  (4, 'Guzman', '2017-08-28', 'mail4@preving.com', '', 'Pedro'),
  (5, 'Rodriguez', '2017-08-30', 'mail5@preving.com', '', 'Antonio'),
  (6, 'Sanchez', '2018-08-28', 'mail6@preving.com', '', 'Adolfo'),
  (7, 'Guzman', '2017-08-28', 'mail7@preving.com', '', 'Jacinto'),
  (8, 'Molina', '2017-08-30', 'mail8@preving.com', '', 'Jaime'),
  (9, 'Suarez', '2018-08-28', 'mail9@preving.com', '', 'Jairo'),
  (10, 'Guzman', '2017-08-28', 'mail10@preving.com', '', 'Josefa'),
  (11, 'Hernandez', '2017-08-30', 'mail11@preving.com', '', 'Ana'),
  (12, 'Sanchez', '2018-08-28', 'mail12@preving.com', '', 'Amanda'),
  (13, 'Alor', '2017-08-28', 'mail13@preving.com', '', 'Auxiliadora'),
  (14, 'Perez', '2017-08-30', 'mail14@preving.com', '', 'Alicia'),
  (15, 'Luna', '2018-08-28', 'mail15@preving.com', '', 'María'),
  (16, 'Guzman', '2017-08-28', 'mail16@preving.com', '', 'Manuela'),
  (17, 'Vila', '2017-08-30', 'mail17@preving.com', '', 'Estrella'),
  (18, 'Rodriguez', '2018-08-28', 'mail18@preving.com', '', 'Sara'),
  (19, 'Martin', '2017-08-28', 'mail19@preving.com', '', 'Rocio'),
  (20, 'Perez', '2017-08-30', 'mail20@preving.com', '', 'Yolanda'),
  (21, 'Ceballos', '2018-08-28', 'mail21@preving.com', '', 'Cristina'),
  (22, 'Carreras', '2017-08-28', 'mail22@preving.com', '', 'Luis'),
  (23, 'Saavedra', '2017-08-30', 'mail23@preving.com', '', 'Antonia'),
  (24, 'Caldera', '2018-08-28', 'mail24@preving.com', '', 'Alba'),
  (25, 'Fernandez', '2017-08-28', 'mail25@preving.com', '', 'Mercedes'),
  (26, 'Perez', '2017-08-30', 'mail26@preving.com', '', 'Estefania'),
  (27, 'Sanchez', '2018-08-28', 'mail27@preving.com', '', 'Eva'),
  (28, 'Guzman', '2017-08-28', 'mail28@preving.com', '', 'Baltasar'),
  (29, 'Rodriguez', '2017-08-30', 'mail29@preving.com', '', 'Jesus'),
  (30, 'Sanchez', '2018-08-28', 'mail30@preving.com', '', 'Adriana'),
  (31, 'Guzman', '2017-08-01', 'profesor@bolsadeideas.com', '', 'Andres'),
  (32, 'Doe', '2017-08-02', 'john.doe@gmail.com', '', 'John'),
  (33, 'Torvalds', '2017-08-03', 'linus.torvalds@gmail.com', '', 'Linus'),
  (34, 'Doe', '2017-08-04', 'jane.doe@gmail.com', '', 'Jane'),
  (35, 'Lerdorf', '2017-08-05', 'rasmus.lerdorf@gmail.com', '', 'Rasmus'),
  (36, 'Gamma', '2017-08-06', 'erich.gamma@gmail.com', '', 'Erich'),
  (37, 'Helm', '2017-08-07', 'richard.helm@gmail.com', '', 'Richard'),
  (38, 'Johnson', '2017-08-08', 'ralph.johnson@gmail.com', '', 'Ralph'),
  (39, 'Vlissides', '2017-08-09', 'john.vlissides@gmail.com', '', 'John'),
  (40, 'Gosling', '2017-08-10', 'james.gosling@gmail.com', '', 'James'),
  (41, 'Lee', '2017-08-11', 'bruce.lee@gmail.com', '', 'Bruce'),
  (42, 'Doe', '2017-08-12', 'johnny.doe@gmail.com', '', 'Johnny'),
  (43, 'Roe', '2017-08-13', 'john.roe@gmail.com', '', 'John'),
  (44, 'Roe', '2017-08-14', 'jane.roe@gmail.com', '', 'Jane'),
  (45, 'Doe', '2017-08-15', 'richard.doe@gmail.com', '', 'Richard'),
  (46, 'Doe', '2017-08-16', 'janie.doe@gmail.com', '', 'Janie'),
  (47, 'Webb', '2017-08-17', 'phillip.webb@gmail.com', '', 'Phillip'),
  (48, 'Nicoll', '2017-08-18', 'stephane.nicoll@gmail.com', '', 'Stephane'),
  (49, 'Brannen', '2017-08-19', 'sam.brannen@gmail.com', '', 'Sam'),
  (50, 'Hoeller', '2017-08-20', 'juergen.Hoeller@gmail.com', '', 'Juergen'),
  (51, 'Roe', '2017-08-21', 'janie.roe@gmail.com', '', 'Janie'),
  (52, 'Smith', '2017-08-22', 'john.smith@gmail.com', '', 'John'),
  (53, 'Bloggs', '2017-08-23', 'joe.bloggs@gmail.com', '', 'Joe'),
  (54, 'Stiles', '2017-08-24', 'john.stiles@gmail.com', '', 'John'),
  (55, 'Roe', '2017-08-25', 'stiles.roe@gmail.com', '', 'Richard');
/*!40000 ALTER TABLE `clientes`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturas` (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_at`   DATE                DEFAULT NULL,
  `descripcion` VARCHAR(255)        DEFAULT NULL,
  `observacion` VARCHAR(255)        DEFAULT NULL,
  `cliente_id`  BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1qiuk10rfkovhlfpsk7oic0v8` (`cliente_id`),
  CONSTRAINT `FK1qiuk10rfkovhlfpsk7oic0v8` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas`
  DISABLE KEYS */;
INSERT INTO `facturas` VALUES (1, '2018-04-05', 'Factura equipos de oficina', NULL, 1),
  (2, '2018-04-05', 'Factura Bicicleta', 'Alguna nota importante!', 1);
/*!40000 ALTER TABLE `facturas`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas_items`
--

DROP TABLE IF EXISTS `facturas_items`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturas_items` (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cantidad`    INT(11)             DEFAULT NULL,
  `producto_id` BIGINT(20)          DEFAULT NULL,
  `factura_id`  BIGINT(20)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdumnm9x14hjfp9fufn7q8389r` (`producto_id`),
  KEY `FKnv8ijya20df661b0p6drqcx7u` (`factura_id`),
  CONSTRAINT `FKdumnm9x14hjfp9fufn7q8389r` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`),
  CONSTRAINT `FKnv8ijya20df661b0p6drqcx7u` FOREIGN KEY (`factura_id`) REFERENCES `facturas` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas_items`
--

LOCK TABLES `facturas_items` WRITE;
/*!40000 ALTER TABLE `facturas_items`
  DISABLE KEYS */;
INSERT INTO `facturas_items` VALUES (1, 1, 1, 1), (2, 2, 4, 1), (3, 1, 5, 1), (4, 1, 7, 1), (5, 3, 6, 2);
/*!40000 ALTER TABLE `facturas_items`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `create_at` DATE                DEFAULT NULL,
  `nombre`    VARCHAR(255)        DEFAULT NULL,
  `precio`    DOUBLE              DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos`
  DISABLE KEYS */;
INSERT INTO `productos`
VALUES (1, '2018-04-05', 'Panasonic Pantalla LCD', 259990), (2, '2018-04-05', 'Sony Camara digital DSC-W320B', 123490),
  (3, '2018-04-05', 'Apple iPod shuffle', 1499990), (4, '2018-04-05', 'Sony Notebook Z110', 37990),
  (5, '2018-04-05', 'Hewlett Packard Multifuncional F2280', 69990),
  (6, '2018-04-05', 'Bianchi Bicicleta Aro 26', 69990), (7, '2018-04-05', 'Mica Comoda 5 Cajones', 299990);
/*!40000 ALTER TABLE `productos`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id`       INT(11)     NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enable`   TINYINT(1)  NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users`
  DISABLE KEYS */;
INSERT INTO `users` VALUES (1, 'andres', '$2a$10$Tb6rAxz5HrlkrcO1y9kNCulAeoT3NmUgXNGvZ4l1XiGFIjM/G80BW', 1),
  (2, 'admin', '$2a$10$D9kaJYEal1hHPc6JG9Hk9eP.JOEtotmSHBxaJizt9IfATn9R0qMyu', 1);
/*!40000 ALTER TABLE `users`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2018-04-05 12:06:18
