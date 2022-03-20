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
-- Table structure for table `counselor_category`
--

DROP TABLE IF EXISTS `counselor_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counselor_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `counselor_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbp45via8pww6u0kglxcyk7eox` (`category_id`),
  KEY `FKi95ohqovvmmqcm4ka7aoe9qmc` (`counselor_email`),
  CONSTRAINT `FKbp45via8pww6u0kglxcyk7eox` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKi95ohqovvmmqcm4ka7aoe9qmc` FOREIGN KEY (`counselor_email`) REFERENCES `counselor` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counselor_category`
--

LOCK TABLES `counselor_category` WRITE;
/*!40000 ALTER TABLE `counselor_category` DISABLE KEYS */;
INSERT INTO `counselor_category` VALUES (1,4,'enji81@naver.com'),(2,5,'tekies0909@gmail.com'),(3,6,'syoung@gmail.com'),(5,1,'boram90@naver.com'),(6,3,'happy20@naver.com'),(7,5,'jieun01@gmail.com'),(8,1,'jong82@naver.com'),(9,8,'therapyall@naver.com'),(10,9,'sang92@naver.com'),(11,1,'elin0225@naver.com'),(12,6,'ayun@naver.com');
/*!40000 ALTER TABLE `counselor_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  3:02:55
