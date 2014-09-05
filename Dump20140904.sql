CREATE DATABASE  IF NOT EXISTS `sociallibrary_development` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sociallibrary_development`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: localhost    Database: sociallibrary_development
-- ------------------------------------------------------
-- Server version	5.6.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookcategories`
--

DROP TABLE IF EXISTS `bookcategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookcategories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcategories`
--

LOCK TABLES `bookcategories` WRITE;
/*!40000 ALTER TABLE `bookcategories` DISABLE KEYS */;
INSERT INTO `bookcategories` VALUES (1,'fiction'),(2,''),(3,'Mystery'),(4,'Traitor'),(5,'History'),(6,'Entertainment'),(7,'Thriller'),(8,'Romantic'),(9,'AutoBiography');
/*!40000 ALTER TABLE `bookcategories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookrequest`
--

DROP TABLE IF EXISTS `bookrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookrequest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_book_id` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookrequest`
--

LOCK TABLES `bookrequest` WRITE;
/*!40000 ALTER TABLE `bookrequest` DISABLE KEYS */;
INSERT INTO `bookrequest` VALUES (6,4,1);
/*!40000 ALTER TABLE `bookrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(100) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `ISBN` varchar(100) DEFAULT NULL,
  `bookimage` blob,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (33,'Two States',8,4,'14700349461',NULL,30),(34,'OutLander',8,4,'14700134947',NULL,30),(35,'HarryPotter',1,3,'1470034952',NULL,10),(36,'Troy',5,4,'1470034959',NULL,30),(37,'Twilight',8,3,'1470034973',NULL,20),(38,'House Divided',3,4,'7110034980',NULL,30),(39,'Unseen',3,3,'2210034990',NULL,NULL),(40,'Alchemist',1,3,'1420034678',NULL,NULL),(41,'Dan Brown',8,3,'314700134947',NULL,NULL),(42,'The Proposal',8,3,'144700134947',NULL,20),(43,'Black Rain',5,3,'4710034959',NULL,30),(44,'Paris Wife',9,3,'501959122',NULL,15),(45,'Marks Story',9,2,'4355345342',NULL,15),(46,'EQ',6,4,'471959122',NULL,NULL),(47,'SkinWalkers',4,2,'9591221111',NULL,15),(48,'Road',2,3,'601959122',NULL,NULL),(49,'Book1',1,3,'1234566787',NULL,NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'SCU'),(2,'SJSU'),(3,'USC'),(4,'CSU'),(5,'SDSU');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memberbooks`
--

DROP TABLE IF EXISTS `memberbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memberbooks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `borrower_id` int(11) DEFAULT NULL,
  `memberrating` int(11) DEFAULT NULL,
  `last_updated_at` date DEFAULT NULL,
  `availability` tinyint(1) DEFAULT NULL,
  `purchasable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memberbooks`
--

LOCK TABLES `memberbooks` WRITE;
/*!40000 ALTER TABLE `memberbooks` DISABLE KEYS */;
INSERT INTO `memberbooks` VALUES (3,36,1,1,4,'2014-09-04',0,1),(4,37,2,2,3,'2014-09-04',0,1),(5,38,2,2,4,'2014-09-04',0,1),(6,39,2,2,3,'2014-09-04',0,1),(7,40,3,3,3,'2014-09-04',0,1),(8,41,3,3,3,'2014-09-04',0,1),(9,42,3,3,3,'2014-09-04',0,1),(10,43,4,4,3,'2014-09-04',0,1),(11,44,4,4,3,'2014-09-04',0,1),(12,45,4,4,2,'2014-09-04',0,1),(13,46,4,4,4,'2014-09-04',0,1),(14,47,5,5,2,'2014-09-04',0,1),(15,48,5,5,3,'2014-09-04',0,1),(16,33,5,5,4,'2014-09-04',0,1),(17,49,2,2,3,'2014-09-04',NULL,NULL);
/*!40000 ALTER TABLE `memberbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membergroups`
--

DROP TABLE IF EXISTS `membergroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membergroups` (
  `group_id` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membergroups`
--

LOCK TABLES `membergroups` WRITE;
/*!40000 ALTER TABLE `membergroups` DISABLE KEYS */;
INSERT INTO `membergroups` VALUES (1,1,NULL),(1,2,NULL),(2,2,NULL),(2,3,NULL),(3,3,NULL),(3,4,NULL),(3,5,NULL);
/*!40000 ALTER TABLE `membergroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` char(100) DEFAULT NULL,
  `lastname` char(100) DEFAULT NULL,
  `username` char(100) DEFAULT NULL,
  `password` char(100) DEFAULT NULL,
  `address` char(100) DEFAULT NULL,
  `email` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Siva','Lalita','SLalita','password','1050 Benton St','lalita.vissamsetty@gmail.com'),(2,'Harsha','Teja','Harshatjk','password','2197 NewHall Street','harshatjk@gmail.com'),(3,'Stephen','Robert','SRobert','password','2334 lexington St','srobert@gmail.com'),(4,'Virat','Kohli','VKohli','password',' 3214 delmonte St','vkohli@gmail.com'),(5,'Lalita','Visty','User','pssword','330 washington St','l@gmail.com');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `member_id` int(11) DEFAULT NULL,
  `notification` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (2,'Twilighthas been requested by Siva'),(2,'Twilighthas been requested by Siva'),(2,'Book1has been added by Harsha'),(2,'Book1has been added by Harsha');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nullreference`
--

DROP TABLE IF EXISTS `nullreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nullreference` (
  `nullvalu` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nullreference`
--

LOCK TABLES `nullreference` WRITE;
/*!40000 ALTER TABLE `nullreference` DISABLE KEYS */;
INSERT INTO `nullreference` VALUES ('You need to select a search type');
/*!40000 ALTER TABLE `nullreference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Lalita'),('Lalita'),('SivaLalita'),('SivaLalita'),('SivaLalita'),('SivaLalita'),('SivaLalita'),('Lalli');
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

-- Dump completed on 2014-09-04 18:05:38
