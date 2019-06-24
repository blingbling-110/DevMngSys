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
  `id` varchar(30) NOT NULL COMMENT '借用单编号',
  `dvid` varchar(20) DEFAULT NULL COMMENT '借用设备编号',
  `brwerid` int(11) DEFAULT NULL COMMENT '借用人编号',
  `date` datetime DEFAULT NULL COMMENT '借用日期',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
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
  `id` varchar(20) NOT NULL COMMENT '设备编号',
  `name` varchar(20) DEFAULT NULL COMMENT '设备名称',
  `status` varchar(10) DEFAULT NULL COMMENT '设备状态',
  `des` varchar(50) DEFAULT NULL COMMENT '设备描述',
  `remartk` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_devinfo`
--

LOCK TABLES `tb_devinfo` WRITE;
/*!40000 ALTER TABLE `tb_devinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_devinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_rtn`
--

DROP TABLE IF EXISTS `tb_rtn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_rtn` (
  `id` varchar(30) NOT NULL COMMENT '归还单编号',
  `dvid` varchar(20) DEFAULT NULL COMMENT '归还设备编号',
  `rtnerid` int(11) DEFAULT NULL COMMENT '归还人编号',
  `date` datetime DEFAULT NULL COMMENT '归还日期',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
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
  `id` int(11) NOT NULL COMMENT '人员编号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `userid` varchar(20) DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `pos` varchar(20) DEFAULT NULL COMMENT '职务',
  `dep` varchar(20) DEFAULT NULL COMMENT '部门',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `tel` varchar(30) DEFAULT NULL COMMENT '电话',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_userinfo`
--

LOCK TABLES `tb_userinfo` WRITE;
/*!40000 ALTER TABLE `tb_userinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-24 15:45:23
