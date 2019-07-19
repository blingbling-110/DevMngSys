-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: db_devmngsys
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_brw`
--

DROP TABLE IF EXISTS `tb_brw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_brw` (
  `id` varchar(30) NOT NULL,
  `devid` varchar(20) DEFAULT NULL,
  `brwerid` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_brw`
--

LOCK TABLES `tb_brw` WRITE;
/*!40000 ALTER TABLE `tb_brw` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_brw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_devinfo`
--

DROP TABLE IF EXISTS `tb_devinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_devinfo` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `des` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `req` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_devinfo`
--

LOCK TABLES `tb_devinfo` WRITE;
/*!40000 ALTER TABLE `tb_devinfo` DISABLE KEYS */;
INSERT INTO `tb_devinfo` VALUES ('Dev001','设备001','库存中','','',''),('Dev002','设备002','库存中','','',''),('Dev003','设备003','库存中','','','');
/*!40000 ALTER TABLE `tb_devinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_rtn`
--

DROP TABLE IF EXISTS `tb_rtn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_rtn` (
  `id` varchar(30) NOT NULL,
  `devid` varchar(20) DEFAULT NULL,
  `rtnerid` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_rtn`
--

LOCK TABLES `tb_rtn` WRITE;
/*!40000 ALTER TABLE `tb_rtn` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_rtn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_userinfo`
--

DROP TABLE IF EXISTS `tb_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_userinfo` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `pos` varchar(20) DEFAULT NULL,
  `dep` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `isadmin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_userinfo`
--

LOCK TABLES `tb_userinfo` WRITE;
/*!40000 ALTER TABLE `tb_userinfo` DISABLE KEYS */;
INSERT INTO `tb_userinfo` VALUES (-2,'用户','user','123456','','','no','no','',0),(-1,'管理员','admin','123456','','','no','no','',1),(0,'超级管理员','root','123456','','','no','no','',1);
/*!40000 ALTER TABLE `tb_userinfo` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2019-07-13 18:43:37
