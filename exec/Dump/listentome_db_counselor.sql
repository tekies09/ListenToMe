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
-- Table structure for table `counselor`
--

DROP TABLE IF EXISTS `counselor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counselor` (
  `degree` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `greeting` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `short_greeting` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `FKg9kreq0cbsjmf2nphe4wyenrv` FOREIGN KEY (`email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counselor`
--

LOCK TABLES `counselor` WRITE;
/*!40000 ALTER TABLE `counselor` DISABLE KEYS */;
INSERT INTO `counselor` VALUES ('00대학교 심리상담학과 박사 졸업','언제든 힘드실 때 따뜻한 공감과 지지를 드리며 온 마음을 다해 함께 하겠습니다.','ayun@naver.com_ayun.jpg','안녕하세요 반갑습니다','ayun@naver.com'),('00대학교 심리학 석사 졸업','이야기를 누군가에게 털어놓는 것은 쉽지 않지만 생각이 정리되기도 할 것입니다.','boram90@naver.com_c3.jpg','같이 힘내보아요.','boram90@naver.com'),('00대학교 심리상담학과 박사 졸업','언제든 힘드실 때 따뜻한 공감과 지지를 드리며 온 마음을 다해 함께 하겠습니다.','elin0225@naver.com_yumin.jpg','언제든 쉬러 와주세요','elin0225@naver.com'),('00대학교 상담심리 전공 석사 졸업','안녕하세요 반갑습니다','enji81@naver.com_c2.jpg','같이 힘내보아요.','enji81@naver.com'),('00대학교 심리학 석사 졸업','안녕하세요 반갑습니다','happy20@naver.com_c2.jpg','같이 힘내보아요.','happy20@naver.com'),('상담심리 석사졸업','익숙한 방식이 아닌 낯선 선택을 하며 어렵고 때로는 두려운 그 과정을 함께 하겠습니다.','jieun01@gmail.com_v3_0969190.jpg','여기까지 오느라 애쓴 당신. 오늘 우리 같이 잠시 멈춰봅시다.','jieun01@gmail.com'),('00대학교 아동가족복지학과 가족학 전공 박사 수료','안녕하세요 반갑습니다','jong82@naver.com_c7.jpg','같이 힘내보아요.','jong82@naver.com'),('00대학교 상담심리 전공 석사 졸업','안녕하세요 반갑습니다','sang92@naver.com_c6.jpg','같이 힘내보아요.','sang92@naver.com'),('oo대학교 사회교육대학원 상담심리 전공 석사졸업','\'슬픔은 나누면 반이 되고, 기쁨은 나누면 배가 된다\' 라는 말이 있습니다. 용기내어주신 걸음에 늘 함께 하겠습니다.','syoung@gmail.com_seoyoung.jpg','용기내어 내딛어주신 걸음 함께 하겠습니다.','syoung@gmail.com'),('한국상담대학원 미술심리치료학','여러분의 답답함을 함께 나누어드리겠습니다.','tekies0909@gmail.com_노문택증명사진.png','반갑습니다. 잘부탁드립니다.','tekies0909@gmail.com'),('00대학교 심리학 석사 졸업','안녕하세요 반갑습니다','therapyall@naver.com_c4.jpg','같이 힘내보아요.','therapyall@naver.com');
/*!40000 ALTER TABLE `counselor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  3:02:52
