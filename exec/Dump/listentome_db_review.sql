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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_score` int(11) NOT NULL,
  `client_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `counselor_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK59wpyhu0xgen07ngn4aw839u5` (`client_email`),
  KEY `FKrsul60s8yr1cbr39j61qyqwpj` (`counselor_email`),
  CONSTRAINT `FK59wpyhu0xgen07ngn4aw839u5` FOREIGN KEY (`client_email`) REFERENCES `client` (`email`),
  CONSTRAINT `FKrsul60s8yr1cbr39j61qyqwpj` FOREIGN KEY (`counselor_email`) REFERENCES `counselor` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (2,'너무좋았습니당..',4,'test2@test.com','tekies0909@gmail.com'),(3,'도움이 많이 되었습니다.',4,'joon@naver.com','enji81@naver.com'),(4,'도움이 많이 되었습니다.',3,'sook75@naver.com','therapyall@naver.com'),(5,'도움이 많이 되었습니다.',4,'sook75@naver.com','therapyall@naver.com'),(6,'도움이 많이 되었습니다.',3,'sook75@naver.com','boram90@naver.com'),(7,'덕분에 힘 내서 한걸음 나아갈 수 있을 것 같습니다. 감사합니다.',5,'sook75@naver.com','syoung@gmail.com'),(8,'덕분에 힘 내서 한걸음 나아갈 수 있을 것 같습니다. 감사합니다.',3,'sook75@naver.com','happy20@naver.com'),(9,'덕분에 힘 내서 한걸음 나아갈 수 있을 것 같습니다. 감사합니다.',3,'sook75@naver.com','jieun01@gmail.com'),(10,'덕분에 힘 내서 한걸음 나아갈 수 있을 것 같습니다. 감사합니다.',3,'sook75@naver.com','jong82@naver.com'),(12,'덕분에 힘 내서 한걸음 나아갈 수 있을 것 같습니다. 감사합니다.',5,'yeongcheol@naver.com','elin0225@naver.com'),(13,'덕분에 마음 편히 상담할 수 있었습니다.',5,'jin24@gmail.com','boram90@naver.com'),(14,'친절한 말솜씨.. 역시 상담의 대가 김유민.. 그녀는 대단한것같습니다..',5,'test2@test.com','elin0225@naver.com'),(15,'위로와 조언의 말씀 감사합니다 ㅠㅠ ',5,'breathinlee@gmail.com','elin0225@naver.com'),(16,'헉 조금 아쉬워요...',3,'breathinlee@gmail.com','elin0225@naver.com'),(17,'좋은말씀 ㄳ',5,'breathinlee@gmail.com','elin0225@naver.com'),(18,'good',5,'breathinlee@gmail.com','elin0225@naver.com'),(19,'친절한 말솜씨... 역시 상담의 대가 심아윤... 정말 대단한 것 같아요.',5,'ho21@gmail.com','ayun@naver.com'),(20,'편안한 분위기로 이끌어주셔서 감사합니다.',5,'breathinlee@gmail.com','jieun01@gmail.com');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
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
