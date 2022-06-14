-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: rugby
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.24-MariaDB

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
-- Table structure for table `coach`
--

DROP TABLE IF EXISTS `coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_team` int(11) DEFAULT NULL,
  `fullname` varchar(32) DEFAULT NULL,
  `weight` int(11) DEFAULT 0,
  `experience` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id_team` (`id_team`),
  CONSTRAINT `coach_ibfk_1` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=811 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach`
--

LOCK TABLES `coach` WRITE;
/*!40000 ALTER TABLE `coach` DISABLE KEYS */;
INSERT INTO `coach` VALUES (793,265,'Trainer 1',78,18),(794,265,'Trainer 2',63,8),(795,265,'Trainer 3',98,38),(796,266,'Trainer 4',74,90),(797,266,'Trainer 5',107,54),(798,266,'Trainer 6',65,96),(799,267,'Trainer 7',77,3),(800,267,'Trainer 8',78,69),(801,267,'Trainer 9',72,67),(802,268,'Trainer 10',98,75),(803,268,'Trainer 11',86,36),(804,268,'Trainer 12',78,44),(805,269,'Trainer 13',74,53),(806,269,'Trainer 14',85,93),(807,269,'Trainer 15',90,55),(808,270,'Trainer 16',89,35),(809,270,'Trainer 17',73,44),(810,270,'Trainer 18',104,92);
/*!40000 ALTER TABLE `coach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enforce`
--

DROP TABLE IF EXISTS `enforce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enforce` (
  `id_game` int(11) NOT NULL,
  `id_referee` int(11) NOT NULL,
  `acta` varchar(64) DEFAULT 'NONE',
  PRIMARY KEY (`id_game`,`id_referee`),
  KEY `id_referee` (`id_referee`),
  CONSTRAINT `enforce_ibfk_1` FOREIGN KEY (`id_game`) REFERENCES `game` (`id`),
  CONSTRAINT `enforce_ibfk_2` FOREIGN KEY (`id_referee`) REFERENCES `referee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enforce`
--

LOCK TABLES `enforce` WRITE;
/*!40000 ALTER TABLE `enforce` DISABLE KEYS */;
INSERT INTO `enforce` VALUES (1291,265,'The winner is FRANCE'),(1291,266,NULL),(1291,267,NULL),(1292,268,'The winner is ENGLAND'),(1292,269,NULL),(1292,270,NULL),(1293,265,'The winner is ENGLAND'),(1293,266,NULL),(1293,267,NULL),(1294,268,'The winner is ENGLAND'),(1294,269,NULL),(1294,270,NULL),(1295,265,'The winner is ENGLAND'),(1295,266,NULL),(1295,267,NULL),(1296,268,'The winner is FRANCE'),(1296,269,NULL),(1296,270,NULL),(1297,265,'The winner is FRANCE'),(1297,266,NULL),(1297,267,NULL),(1298,268,'The winner is FRANCE'),(1298,269,NULL),(1298,270,NULL),(1299,265,'The winner is FRANCE'),(1299,266,NULL),(1299,267,NULL),(1300,268,'The winner is IRELAND'),(1300,269,NULL),(1300,270,NULL),(1301,265,'The winner is IRELAND'),(1301,266,NULL),(1301,267,NULL),(1302,268,'The winner is WALES'),(1302,269,NULL),(1302,270,NULL),(1303,265,'The winner is SCOTLAND'),(1303,266,NULL),(1303,267,NULL),(1304,268,'The winner is ITALY'),(1304,269,NULL),(1304,270,NULL),(1305,265,'The winner is SCOTLAND'),(1305,266,NULL),(1305,267,NULL);
/*!40000 ALTER TABLE `enforce` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_stadium` int(11) DEFAULT NULL,
  `host_score` int(11) DEFAULT 0,
  `visitor_score` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id_stadium` (`id_stadium`),
  CONSTRAINT `game_ibfk_1` FOREIGN KEY (`id_stadium`) REFERENCES `stadium` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1306 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1291,265,39,55),(1292,266,16,12),(1293,267,16,5),(1294,268,27,20),(1295,269,24,4),(1296,270,20,19),(1297,265,43,25),(1298,266,52,21),(1299,267,40,37),(1300,268,44,2),(1301,269,44,32),(1302,270,22,53),(1303,265,4,44),(1304,266,36,7),(1305,267,51,44);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participate`
--

DROP TABLE IF EXISTS `participate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participate` (
  `id_game` int(11) NOT NULL,
  `id_team` int(11) NOT NULL,
  `is_host` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_game`,`id_team`),
  KEY `id_team` (`id_team`),
  CONSTRAINT `participate_ibfk_1` FOREIGN KEY (`id_game`) REFERENCES `game` (`id`),
  CONSTRAINT `participate_ibfk_2` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participate`
--

LOCK TABLES `participate` WRITE;
/*!40000 ALTER TABLE `participate` DISABLE KEYS */;
INSERT INTO `participate` VALUES (1291,265,1),(1291,266,0),(1292,265,1),(1292,267,0),(1293,265,1),(1293,268,0),(1294,265,1),(1294,269,0),(1295,265,1),(1295,270,0),(1296,266,1),(1296,267,0),(1297,266,1),(1297,268,0),(1298,266,1),(1298,269,0),(1299,266,1),(1299,270,0),(1300,267,1),(1300,268,0),(1301,267,1),(1301,269,0),(1302,267,1),(1302,270,0),(1303,268,1),(1303,269,0),(1304,268,1),(1304,270,0),(1305,269,1),(1305,270,0);
/*!40000 ALTER TABLE `participate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_team` int(11) DEFAULT NULL,
  `fullname` varchar(32) DEFAULT NULL,
  `weight` int(11) DEFAULT 0,
  `strength` int(11) DEFAULT 0,
  `speed` int(11) DEFAULT 0,
  `resistence` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id_team` (`id_team`),
  CONSTRAINT `player_ibfk_1` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (7921,265,'Player 1',84,60,60,20),(7922,265,'Player 2',105,83,7,5),(7923,265,'Player 3',72,12,50,61),(7924,265,'Player 4',79,32,88,51),(7925,265,'Player 5',78,49,30,95),(7926,265,'Player 6',78,8,82,46),(7927,265,'Player 7',65,33,59,2),(7928,265,'Player 8',117,61,51,75),(7929,265,'Player 9',86,33,42,41),(7930,265,'Player 10',74,39,4,86),(7931,265,'Player 11',81,73,57,46),(7932,265,'Player 12',99,24,28,27),(7933,265,'Player 13',73,66,35,23),(7934,265,'Player 14',62,22,15,95),(7935,265,'Player 15',92,82,73,34),(7936,265,'Player 16',111,0,4,6),(7937,265,'Player 17',64,83,86,82),(7938,265,'Player 18',80,82,57,8),(7939,265,'Player 19',83,14,29,32),(7940,265,'Player 20',70,32,93,79),(7941,265,'Player 21',81,15,46,41),(7942,265,'Player 22',64,91,67,4),(7943,265,'Player 23',76,30,31,68),(7944,265,'Player 24',73,45,91,5),(7945,265,'Player 25',76,91,0,97),(7946,265,'Player 26',80,43,94,79),(7947,265,'Player 27',99,23,14,92),(7948,265,'Player 28',115,93,93,70),(7949,265,'Player 29',119,96,5,56),(7950,265,'Player 30',61,94,21,63),(7951,266,'Player 31',69,11,88,88),(7952,266,'Player 32',86,95,22,1),(7953,266,'Player 33',108,12,62,36),(7954,266,'Player 34',76,92,23,38),(7955,266,'Player 35',108,46,42,99),(7956,266,'Player 36',80,87,28,1),(7957,266,'Player 37',97,14,61,26),(7958,266,'Player 38',93,81,14,5),(7959,266,'Player 39',112,99,46,14),(7960,266,'Player 40',98,77,1,56),(7961,266,'Player 41',77,49,55,9),(7962,266,'Player 42',97,5,42,40),(7963,266,'Player 43',113,56,13,53),(7964,266,'Player 44',116,11,52,62),(7965,266,'Player 45',77,66,16,34),(7966,266,'Player 46',66,60,67,8),(7967,266,'Player 47',71,94,59,32),(7968,266,'Player 48',86,37,7,28),(7969,266,'Player 49',88,43,20,69),(7970,266,'Player 50',62,69,83,68),(7971,266,'Player 51',103,60,18,15),(7972,266,'Player 52',69,87,69,0),(7973,266,'Player 53',114,47,3,85),(7974,266,'Player 54',110,0,33,24),(7975,266,'Player 55',83,37,62,46),(7976,266,'Player 56',115,55,98,52),(7977,266,'Player 57',110,0,51,28),(7978,266,'Player 58',86,0,66,79),(7979,266,'Player 59',91,18,74,77),(7980,266,'Player 60',95,90,50,13),(7981,267,'Player 61',117,81,74,28),(7982,267,'Player 62',108,76,75,29),(7983,267,'Player 63',64,51,22,62),(7984,267,'Player 64',120,87,44,47),(7985,267,'Player 65',84,27,95,87),(7986,267,'Player 66',87,94,93,62),(7987,267,'Player 67',105,3,91,57),(7988,267,'Player 68',77,0,79,74),(7989,267,'Player 69',86,78,30,8),(7990,267,'Player 70',91,22,63,98),(7991,267,'Player 71',65,97,16,87),(7992,267,'Player 72',110,35,85,50),(7993,267,'Player 73',103,9,90,2),(7994,267,'Player 74',112,95,59,31),(7995,267,'Player 75',76,66,68,12),(7996,267,'Player 76',91,83,21,74),(7997,267,'Player 77',64,18,63,38),(7998,267,'Player 78',94,19,99,11),(7999,267,'Player 79',113,71,40,64),(8000,267,'Player 80',102,30,8,71),(8001,267,'Player 81',73,99,26,68),(8002,267,'Player 82',93,97,88,35),(8003,267,'Player 83',98,47,0,84),(8004,267,'Player 84',93,96,14,91),(8005,267,'Player 85',101,59,31,97),(8006,267,'Player 86',97,4,69,74),(8007,267,'Player 87',110,11,29,26),(8008,267,'Player 88',118,59,60,82),(8009,267,'Player 89',108,39,28,30),(8010,267,'Player 90',92,67,97,2),(8011,268,'Player 91',109,94,27,65),(8012,268,'Player 92',114,12,92,89),(8013,268,'Player 93',115,64,54,56),(8014,268,'Player 94',97,8,19,76),(8015,268,'Player 95',70,38,16,71),(8016,268,'Player 96',81,98,70,19),(8017,268,'Player 97',95,5,65,18),(8018,268,'Player 98',78,89,84,1),(8019,268,'Player 99',120,17,95,88),(8020,268,'Player 100',100,79,23,36),(8021,268,'Player 101',67,64,34,21),(8022,268,'Player 102',75,14,52,62),(8023,268,'Player 103',109,20,7,74),(8024,268,'Player 104',91,84,15,19),(8025,268,'Player 105',119,76,85,12),(8026,268,'Player 106',86,40,38,30),(8027,268,'Player 107',101,63,47,50),(8028,268,'Player 108',73,94,85,50),(8029,268,'Player 109',96,36,4,13),(8030,268,'Player 110',102,42,54,37),(8031,268,'Player 111',104,60,86,11),(8032,268,'Player 112',105,3,96,73),(8033,268,'Player 113',60,30,75,23),(8034,268,'Player 114',84,86,81,32),(8035,268,'Player 115',68,18,39,46),(8036,268,'Player 116',64,13,54,70),(8037,268,'Player 117',118,11,1,63),(8038,268,'Player 118',89,60,46,92),(8039,268,'Player 119',82,6,43,16),(8040,268,'Player 120',65,10,43,15),(8041,269,'Player 121',110,62,45,6),(8042,269,'Player 122',99,7,26,32),(8043,269,'Player 123',114,31,84,44),(8044,269,'Player 124',62,73,25,49),(8045,269,'Player 125',114,10,60,36),(8046,269,'Player 126',111,20,57,54),(8047,269,'Player 127',82,95,28,63),(8048,269,'Player 128',89,65,52,26),(8049,269,'Player 129',96,29,37,98),(8050,269,'Player 130',60,41,69,4),(8051,269,'Player 131',108,65,12,74),(8052,269,'Player 132',99,51,55,79),(8053,269,'Player 133',61,94,39,79),(8054,269,'Player 134',64,16,25,65),(8055,269,'Player 135',100,13,27,66),(8056,269,'Player 136',87,6,36,67),(8057,269,'Player 137',86,20,60,77),(8058,269,'Player 138',70,60,96,58),(8059,269,'Player 139',115,75,65,42),(8060,269,'Player 140',108,43,35,32),(8061,269,'Player 141',65,53,63,97),(8062,269,'Player 142',75,73,23,82),(8063,269,'Player 143',100,83,66,11),(8064,269,'Player 144',102,36,9,38),(8065,269,'Player 145',111,51,49,47),(8066,269,'Player 146',98,68,27,56),(8067,269,'Player 147',62,8,12,80),(8068,269,'Player 148',96,64,80,31),(8069,269,'Player 149',85,9,98,49),(8070,269,'Player 150',74,53,6,5),(8071,270,'Player 151',101,80,40,15),(8072,270,'Player 152',80,41,92,63),(8073,270,'Player 153',71,83,50,53),(8074,270,'Player 154',100,20,73,48),(8075,270,'Player 155',76,26,88,6),(8076,270,'Player 156',95,80,48,49),(8077,270,'Player 157',96,50,49,72),(8078,270,'Player 158',113,92,89,28),(8079,270,'Player 159',107,70,66,97),(8080,270,'Player 160',115,33,44,92),(8081,270,'Player 161',88,70,31,12),(8082,270,'Player 162',108,74,29,40),(8083,270,'Player 163',112,63,62,20),(8084,270,'Player 164',66,64,87,26),(8085,270,'Player 165',85,85,79,29),(8086,270,'Player 166',89,62,48,14),(8087,270,'Player 167',105,71,62,91),(8088,270,'Player 168',72,12,17,39),(8089,270,'Player 169',90,23,12,77),(8090,270,'Player 170',77,61,82,81),(8091,270,'Player 171',85,93,25,20),(8092,270,'Player 172',115,70,2,13),(8093,270,'Player 173',118,29,56,86),(8094,270,'Player 174',88,55,41,0),(8095,270,'Player 175',77,41,49,71),(8096,270,'Player 176',104,63,42,39),(8097,270,'Player 177',65,10,30,25),(8098,270,'Player 178',117,85,49,76),(8099,270,'Player 179',68,39,8,45),(8100,270,'Player 180',72,41,47,49);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referee`
--

DROP TABLE IF EXISTS `referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(32) DEFAULT NULL,
  `weight` int(11) DEFAULT 0,
  `accuracy` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referee`
--

LOCK TABLES `referee` WRITE;
/*!40000 ALTER TABLE `referee` DISABLE KEYS */;
INSERT INTO `referee` VALUES (265,'Referee 1',68,10),(266,'Referee 2',101,55),(267,'Referee 3',112,84),(268,'Referee 4',89,43),(269,'Referee 5',61,71),(270,'Referee 6',96,53);
/*!40000 ALTER TABLE `referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stadium`
--

DROP TABLE IF EXISTS `stadium`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stadium` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(64) DEFAULT NULL,
  `capacity` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stadium`
--

LOCK TABLES `stadium` WRITE;
/*!40000 ALTER TABLE `stadium` DISABLE KEYS */;
INSERT INTO `stadium` VALUES (265,'ENGLAND',3286),(266,'FRANCE',3659),(267,'IRELAND',5400),(268,'ITALY',4971),(269,'SCOTLAND',3272),(270,'WALES',4736);
/*!40000 ALTER TABLE `stadium` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nation` varchar(32) DEFAULT NULL,
  `played` int(11) DEFAULT 0,
  `losses` int(11) DEFAULT 0,
  `victories` int(11) DEFAULT 0,
  `draws` int(11) DEFAULT 0,
  `points` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (265,'ENGLAND',5,1,4,0,12),(266,'FRANCE',5,0,5,0,15),(267,'IRELAND',5,3,2,0,6),(268,'ITALY',5,4,1,0,3),(269,'SCOTLAND',5,3,2,0,6),(270,'WALES',5,4,1,0,3);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-14 16:04:53
