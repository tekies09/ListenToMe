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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `dtype` varchar(31) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birth` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `point` int(11) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Client','2dend0713@gmail.com','1994-07-13','m','박상현','1234','010-6655-3446',8000),('Counselor','ayun@naver.com','1997/02/20','f','심아윤','ayun05','010-4329-4234',11000),('Counselor','boram90@naver.com','1990/11/21','f','김보람','qwer1234','010-4372-6526',9000),('Client','breathinlee@gmail.com','1994-06-02','f','이수민','aaaa11','010-8661-7350',0),('Client','canston@naver.com','1992-08-04','m','이준희','qwert','010-5466-3333',10000),('Client','client1@ssafy.com','1997-08-25','m','client1','1111','010-2324-4646',9000),('Client','conseyolee@gmail.com','1891-06-26','m','이승윤','listentome','010-1234-5678',8000),('Client','ee@naver.com','2021-12-15','m','이이','1234','010',9000),('Counselor','elin0225@naver.com','1999/02/25','f','김유민','qwer!','010-1462-2147',28000),('Counselor','enji81@naver.com','1980/02/21','f','박은지','123%','010-4385-7116',8000),('Counselor','happy20@naver.com','1955/11/21','f','김영자','123%','011-3552-7842',8000),('Client','ho21@gmail.com','1970/12/20','m','김정호','2121!','010-4231-9283',88000),('Client','jhj1234@naver.com','1996-09-15','m','진형준','11111111','010-1111-1111',9000),('Counselor','jieun01@gmail.com','1987-11-12','f','김지은','qwer!','010-2313-3333',3000),('Client','jin24@gmail.com','1988/02/20','f','김진호','11111','010-4223-1124',94000),('Counselor','jong82@naver.com','1982/11/21','m','박종수','123%','010-4385-7116',8000),('Client','joon@naver.com','1980/04/11','m','김민준','joon%','010-9384-2346',91000),('Client','kkjo@naver.com','1988-04-26','m','김종욱','qwert','010-8433-1222',5000),('Counselor','sang92@naver.com','1970/09/21','m','이상철','fjew%','010-4284-2416',7000),('Client','sook75@naver.com','1970/08/09','f','김영숙','sook','010-1386-3571',96000),('Counselor','syoung@gmail.com','1997-10-11','f','이서영','syoung','010-9874-1233',2000),('Counselor','tekies0909@gmail.com','1987-05-16','m','나수전','moon0909','010-5678-3125',3000),('Client','test@test.com','2022-02-15','f','qwer','qwer`123','010-1234-1234',10000),('Client','test123','','m','test123','11111111','010-1111-1111',9000),('Client','test2@test.com','1996-08-01','f','김현아','test2','010-1234-5896',3000),('Counselor','therapyall@naver.com','1960/11/21','m','박영길','123%','010-1252-3527',9000),('Client','tovi@gmail.com','1985/07/01','f','이하림','qwert','010-4543-9455',10000),('Client','yeeun@naver.com','1999/03/11','f','김예은','io39dk','010-5351-2616',87000),('Client','yeongcheol@naver.com','1960/02/05','m','김영철','rcheol@','010-4351-2526',2000),('Client','yoon@gmail.com','1979-11-08','f','이서윤','qwert','010-4546-6633',93000),('Client','young21@gmail.com','1997/02/20','f','박은영','2121!','010-2341-1431',88000),('Counselor','youngwoo@gmail.com','1979-01-13','m','이영우','young1','010-0546-9996',1000);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  3:02:53
