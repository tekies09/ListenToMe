-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: i6a605.p.ssafy.io    Database: listentome_db
-- ------------------------------------------------------
-- Server version	5.7.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `counselor_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmsskqb6xii7vdr8cn8hcuukt5` (`client_email`),
  KEY `FKk4snev9m5ev5bnis1dn39do8t` (`counselor_email`),
  CONSTRAINT `FKk4snev9m5ev5bnis1dn39do8t` FOREIGN KEY (`counselor_email`) REFERENCES `counselor` (`email`),
  CONSTRAINT `FKmsskqb6xii7vdr8cn8hcuukt5` FOREIGN KEY (`client_email`) REFERENCES `client` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
INSERT INTO `bookmark` VALUES (1,'test@test.com','elin0225@naver.com'),(2,'test@test.com','tekies0909@gmail.com'),(3,'test@test.com','tekies0909@gmail.com'),(4,'test2@test.com','tekies0909@gmail.com'),(5,'test2@test.com','tekies0909@gmail.com'),(6,'test2@test.com','tekies0909@gmail.com'),(7,'jhj1234@naver.com','enji81@naver.com'),(8,'jhj1234@naver.com','enji81@naver.com'),(9,'jhj1234@naver.com','enji81@naver.com'),(10,'jhj1234@naver.com','enji81@naver.com'),(11,'jhj1234@naver.com','jong82@naver.com'),(12,'jhj1234@naver.com','jong82@naver.com'),(13,'jhj1234@naver.com','jong82@naver.com'),(14,'jhj1234@naver.com','jong82@naver.com'),(15,'jhj1234@naver.com','jong82@naver.com'),(16,'jhj1234@naver.com','jong82@naver.com'),(17,'jhj1234@naver.com','jong82@naver.com'),(18,'jhj1234@naver.com','jong82@naver.com'),(19,'jhj1234@naver.com','jong82@naver.com'),(20,'jhj1234@naver.com','happy20@naver.com'),(21,'jhj1234@naver.com','sang92@naver.com'),(22,'jhj1234@naver.com','sang92@naver.com'),(23,'jhj1234@naver.com','sang92@naver.com'),(24,'jhj1234@naver.com','sang92@naver.com'),(25,'jhj1234@naver.com','sang92@naver.com'),(26,'jhj1234@naver.com','sang92@naver.com'),(27,'jhj1234@naver.com','sang92@naver.com'),(28,'jhj1234@naver.com','sang92@naver.com'),(29,'jhj1234@naver.com','sang92@naver.com'),(30,'jhj1234@naver.com','sang92@naver.com'),(31,'jhj1234@naver.com','sang92@naver.com'),(32,'jhj1234@naver.com','sang92@naver.com'),(33,'jhj1234@naver.com','sang92@naver.com'),(34,'ee@naver.com','elin0225@naver.com'),(35,'ee@naver.com','elin0225@naver.com'),(36,'jin24@gmail.com','syoung@gmail.com'),(37,'test2@test.com','syoung@gmail.com'),(38,'test2@test.com','boram90@naver.com'),(39,'test2@test.com','boram90@naver.com'),(40,'joon@naver.com','elin0225@naver.com'),(41,'joon@naver.com','syoung@gmail.com'),(42,'breathinlee@gmail.com','elin0225@naver.com'),(43,'test123','happy20@naver.com'),(44,'test123','happy20@naver.com'),(45,'test123','happy20@naver.com'),(46,'test123','happy20@naver.com'),(47,'test123','happy20@naver.com'),(48,'test123','happy20@naver.com'),(49,'test123','happy20@naver.com'),(50,'test123','happy20@naver.com'),(51,'test123','happy20@naver.com'),(52,'test123','happy20@naver.com'),(53,'test123','enji81@naver.com'),(54,'test123','enji81@naver.com'),(55,'test123','enji81@naver.com'),(56,'test123','enji81@naver.com'),(57,'test123','enji81@naver.com'),(58,'test123','enji81@naver.com'),(59,'test123','enji81@naver.com'),(60,'test123','enji81@naver.com'),(61,'test123','enji81@naver.com'),(62,'test123','happy20@naver.com'),(63,'test123','happy20@naver.com'),(64,'test123','therapyall@naver.com'),(65,'test@test.com','happy20@naver.com'),(66,'conseyolee@gmail.com','jong82@naver.com'),(67,'conseyolee@gmail.com','jong82@naver.com'),(68,'conseyolee@gmail.com','elin0225@naver.com'),(69,'kkjo@naver.com','elin0225@naver.com'),(70,'kkjo@naver.com','elin0225@naver.com'),(71,'sook75@naver.com','syoung@gmail.com'),(72,'sook75@naver.com','elin0225@naver.com'),(73,'yoon@gmail.com','tekies0909@gmail.com'),(74,'test123','boram90@naver.com');
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  3:02:54
