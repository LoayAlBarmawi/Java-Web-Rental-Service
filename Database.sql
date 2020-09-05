CREATE DATABASE  IF NOT EXISTS `rentals` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rentals`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: rentals
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `type` int NOT NULL,
  `id` int NOT NULL,
  `vin` varchar(45) NOT NULL,
  `platenum` int NOT NULL,
  `seats` int NOT NULL,
  `maker` varchar(45) NOT NULL,
  `model` varchar(45) DEFAULT NULL,
  `year` int NOT NULL,
  `odometer` int NOT NULL,
  `rented` int NOT NULL,
  `available` int NOT NULL,
  `branch` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (2,1,'45664',3244,4,'aa','ff',2990,34564,1,1,2),(1,12,'1111',1111,4,'rr','ee',1999,23234,1,1,1),(2,44,'22222',33333,5,'toyota','prius',2010,0,1,1,1),(1,5111,'2323d43',45324,5,'totoya','corola',2015,15123,0,1,0);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `cat` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `kmprice` double DEFAULT NULL,
  `dayprice` int DEFAULT NULL,
  PRIMARY KEY (`cat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'sedan',0.1,15),(2,'compact',0.05,10),(3,'van',0.35,25);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `oid` int NOT NULL,
  `uid` int NOT NULL,
  `cid` int NOT NULL,
  `length` int NOT NULL,
  `odometer` int DEFAULT NULL,
  `distance` int DEFAULT '0',
  `cost` double DEFAULT NULL,
  `confirmed` int DEFAULT '0',
  `lefttopay` double DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,15,1,33,44444,55,645,1,0),(3,18,1,20,6000,100,535,1,NULL),(5,2,1,2,333,4,5,1,NULL),(45,15,44,-1,10332,100,-5,1,NULL),(54,33,22,554,23453,77,345,1,6),(77,14,44,55,15555,33,551.65,1,0),(122,22,44,122,24212,332,1236.6,0,0),(2233,22,1,33,0,44,840.4,1,610.4),(3505,35,44,5,0,300,65,0,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `address` text NOT NULL,
  `email` varchar(45) NOT NULL,
  `active` int DEFAULT '1',
  `type` int NOT NULL,
  `license` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `branch` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'root','l','b','079711111','fhgdf hdghd dgfhdfh ','email@email.com',1,0,'33321','rootpass',0),(2,'user1','r','d','777777','dasfa asdfasg asdfag','email@mail.com',1,2,'546262','userpass',NULL),(3,'admin1','name1','name2','07974321','somewhere','email@g.com',1,1,'55r4','rootpass',1),(5,'user5','user','five','0795555','amman','userfive@gmail.com',1,2,'55555','userpass',NULL),(9,'user9','user','nine','079999','amman','user@mail.com',1,2,'747873','userpass',NULL),(10,'user10','user','ten','657458','amman','email',1,2,'657457','userpass',NULL),(12,'user12','user','twelve','0799999','amman','email@mail.com',1,2,'32423532','userpass',NULL),(15,'user15','user','fifteen','55555','amman','mail',1,2,'45545','userpass',NULL),(16,'user16','user','sixteen','56777','amman','mail@email.com',1,2,'345645','userpass',NULL),(17,'user17','user','seventeen','12233244','amman','mail@email.com',1,2,'435346736','userpass',NULL),(22,'user22','user','22','2222','amman','email',1,2,'2222','userpass',NULL),(31,'reem','reem','burmawi','3332','amman','mail@mail',1,0,'234235','rootpass',NULL),(32,'user1100','user','1100','324325','amman','mail@mail',1,2,'2345','userpass',NULL),(33,'menah','menah','burmawi','079676','amman','mail@mail',1,2,'234563','userpass',NULL),(34,'user1222','loai','b','4352','amman','mail@mail',1,2,'3324','userpass',NULL),(35,'user99','user','99','435643','amman','mail',1,2,'343535','userpass',NULL),(36,'user1000','hfhg','asdfas','32435433','amman','mail@mail',1,2,'345645','userpass',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-05 23:11:37
