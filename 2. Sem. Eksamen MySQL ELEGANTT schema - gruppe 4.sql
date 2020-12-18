CREATE DATABASE  IF NOT EXISTS `project_manager_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `project_manager_app`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: project_manager_app
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `project_ownership`
--

DROP TABLE IF EXISTS `project_ownership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_ownership` (
  `prim_key` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `projectID` int NOT NULL,
  PRIMARY KEY (`prim_key`),
  UNIQUE KEY `prim_key_UNIQUE` (`prim_key`),
  KEY `fk_projectID_idx` (`projectID`),
  CONSTRAINT `fk_projectID` FOREIGN KEY (`projectID`) REFERENCES `projects` (`projectID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_ownership`
--

LOCK TABLES `project_ownership` WRITE;
/*!40000 ALTER TABLE `project_ownership` DISABLE KEYS */;
INSERT INTO `project_ownership` VALUES (22,62,50),(23,62,51),(24,63,52),(25,64,53),(26,65,54),(28,62,52),(29,63,50),(30,64,54),(34,62,59);
/*!40000 ALTER TABLE `project_ownership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `projectID` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(100) NOT NULL,
  `project_created_date` date NOT NULL,
  `project_end_date` date NOT NULL,
  `created_by` int NOT NULL,
  PRIMARY KEY (`projectID`),
  UNIQUE KEY `projectID_UNIQUE` (`projectID`),
  KEY `fk_createdby_idx` (`created_by`),
  CONSTRAINT `fk_createdby` FOREIGN KEY (`created_by`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (50,'project1','2020-12-01','2020-12-31',62),(51,'project2','2020-12-01','2020-12-31',62),(52,'project3','2020-12-01','2020-12-31',63),(53,'project4','2020-12-01','2020-12-31',64),(54,'project5','2020-12-01','2020-12-31',65),(59,'```!*Z*>*>***>*@£$€$€£$€','2020-12-18','2020-12-22',62);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=CURRENT_USER*/ /*!50003 TRIGGER `projects_AFTER_INSERT` AFTER INSERT ON `projects` FOR EACH ROW BEGIN
insert into project_ownership(userID, projectID) values (new.created_by, new.projectID);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `taskID` int NOT NULL AUTO_INCREMENT,
  `task_name` varchar(200) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `task_responsible` int DEFAULT NULL,
  `completion_status` tinyint(1) NOT NULL DEFAULT '0',
  `task_duration_in_hours` int DEFAULT NULL,
  `projectID` int NOT NULL,
  PRIMARY KEY (`taskID`),
  UNIQUE KEY `taskID_UNIQUE` (`taskID`),
  KEY `fk_tasks_idx` (`projectID`),
  CONSTRAINT `fk_task_to_project` FOREIGN KEY (`projectID`) REFERENCES `projects` (`projectID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (48,'task1','2020-12-01','2020-12-05',62,0,12,50),(49,'task2','2020-12-01','2020-12-08',62,0,16,50),(50,'task3','2020-12-05','2020-12-08',63,0,5,50),(51,'task4','2020-12-12','2020-12-25',63,0,21,50),(52,'task1','2020-12-01','2020-12-31',62,0,55,51),(57,'task2','2020-12-10','2020-12-26',62,0,33,51),(58,'task1','2020-12-02','2020-12-14',63,0,21,52),(59,'task2','2020-12-05','2020-12-06',62,0,3,52),(60,'task3','2020-12-05','2020-12-20',62,0,17,52),(61,'task1','2020-12-12','2020-12-14',64,0,2,53),(62,'task1','2020-12-07','2020-12-30',65,0,28,54),(63,'task2','2020-12-09','2020-12-22',64,0,12,54);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (62,'user1','pass1'),(63,'user2','pass2'),(64,'user3','pass3'),(65,'user4','pass4');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'project_manager_app'
--

--
-- Dumping routines for database 'project_manager_app'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-18 15:25:53
