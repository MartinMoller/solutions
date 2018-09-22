-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: recipesDB
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.16.04.1

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
-- Table structure for table `Ingredient`
--

DROP TABLE IF EXISTS `Ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ingredient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ingredient`
--

LOCK TABLES `Ingredient` WRITE;
/*!40000 ALTER TABLE `Ingredient` DISABLE KEYS */;
INSERT INTO `Ingredient` VALUES (1,'mel'),(2,'mælk'),(3,'æg'),(4,'salt');
/*!40000 ALTER TABLE `Ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recipe`
--

DROP TABLE IF EXISTS `Recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `todo` varchar(200) DEFAULT NULL,
  `cookingtime` int(11) DEFAULT NULL,
  `image` varchar(45) DEFAULT NULL,
  `User_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`User_id`),
  KEY `fk_Recipe_User1_idx` (`User_id`),
  CONSTRAINT `fk_Recipe_User1` FOREIGN KEY (`User_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recipe`
--

LOCK TABLES `Recipe` WRITE;
/*!40000 ALTER TABLE `Recipe` DISABLE KEYS */;
INSERT INTO `Recipe` VALUES (1,'pandekager','lækre sprøde pandekager','bland alle ingredienser sammen i en skål og rør melet ud. Varm en pande med smør og steg pandekagerne ved mellemhøj varme',20,'pandekager.jpg',1),(2,'snobrød','letbrændt snobrød over bål','bland alle ingredienserne sammen og ælt dejen grundigt i en time',90,'snobread.jpg',1),(3,'Naan brød','Indisk brød bagt på pande','Bland ingredienserne sammen og ælt dejen grundigt. Rul små kugler af dejen. Rul kuglerne til flade brød og steg dem på panden',20,'naan.jpg',1);
/*!40000 ALTER TABLE `Recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recipe_has_Ingredient`
--

DROP TABLE IF EXISTS `Recipe_has_Ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recipe_has_Ingredient` (
  `Recipe_id` int(11) NOT NULL,
  `Ingredient_id` int(11) NOT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `measure` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Recipe_id`,`Ingredient_id`),
  KEY `fk_Recipe_has_Ingredient_Ingredient1_idx` (`Ingredient_id`),
  KEY `fk_Recipe_has_Ingredient_Recipe_idx` (`Recipe_id`),
  CONSTRAINT `fk_Recipe_has_Ingredient_Ingredient1` FOREIGN KEY (`Ingredient_id`) REFERENCES `Ingredient` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recipe_has_Ingredient_Recipe` FOREIGN KEY (`Recipe_id`) REFERENCES `Recipe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recipe_has_Ingredient`
--

LOCK TABLES `Recipe_has_Ingredient` WRITE;
/*!40000 ALTER TABLE `Recipe_has_Ingredient` DISABLE KEYS */;
INSERT INTO `Recipe_has_Ingredient` VALUES (1,1,'500','gram'),(1,2,'500','ml'),(1,3,'2','stk.'),(1,4,'1','knivsspids'),(2,1,'500','gram'),(2,2,'200','ml'),(2,4,'1','tskf'),(3,1,'500','ml'),(3,2,'200','ml'),(3,4,'1','tskf');
/*!40000 ALTER TABLE `Recipe_has_Ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'demouser','pass123'),(2,'adminuser','pass123');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-22 12:16:19
