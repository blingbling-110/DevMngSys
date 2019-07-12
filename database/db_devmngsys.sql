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
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_devinfo`
--

LOCK TABLES `tb_devinfo` WRITE;
/*!40000 ALTER TABLE `tb_devinfo` DISABLE KEYS */;
INSERT INTO `tb_devinfo` VALUES ('DZ-BD-012','CANAPE','喻延福','功能开发及调试设备','用于测试设备总览内部窗体'),('DZ-CA-001','CANAPE','杨化方','功能开发及调试设备','用于测试刷新按钮'),('DZ-CA-002','CANAPE','苏健','功能开发及调试设备','用于测试增加设备按钮');
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
  `isadmin` tinyint(1) DEFAULT NULL COMMENT '是否拥有管理权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_userinfo`
--

LOCK TABLES `tb_userinfo` WRITE;
/*!40000 ALTER TABLE `tb_userinfo` DISABLE KEYS */;
INSERT INTO `tb_userinfo` VALUES (10915,'覃子俊','admin','dias,11','软件工程师','CP（控制器平台）','qinzijun@dias.com.cn','+86(21)60305233','系统管理员',1),(54312,'普通用户','user','dias,11','','','no','no','',0),(54321,'管理员','manager','dias,11','','','no','no','',1);
/*!40000 ALTER TABLE `tb_userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_brwinfo`
--

DROP TABLE IF EXISTS `v_brwinfo`;
/*!50001 DROP VIEW IF EXISTS `v_brwinfo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_brwinfo` AS SELECT 
 1 AS `id`,
 1 AS `devname`,
 1 AS `dvid`,
 1 AS `username`,
 1 AS `email`,
 1 AS `tel`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_rtninfo`
--

DROP TABLE IF EXISTS `v_rtninfo`;
/*!50001 DROP VIEW IF EXISTS `v_rtninfo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `v_rtninfo` AS SELECT 
 1 AS `id`,
 1 AS `devname`,
 1 AS `dvid`,
 1 AS `username`,
 1 AS `email`,
 1 AS `tel`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_brwinfo`
--

/*!50001 DROP VIEW IF EXISTS `v_brwinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_brwinfo` AS select `tb_brw`.`id` AS `id`,`tb_devinfo`.`name` AS `devname`,`tb_brw`.`dvid` AS `dvid`,`tb_userinfo`.`name` AS `username`,`tb_userinfo`.`email` AS `email`,`tb_userinfo`.`tel` AS `tel` from ((`tb_brw` join `tb_devinfo` on((`tb_brw`.`dvid` = `tb_devinfo`.`id`))) join `tb_userinfo` on((`tb_brw`.`brwerid` = `tb_userinfo`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_rtninfo`
--

/*!50001 DROP VIEW IF EXISTS `v_rtninfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_rtninfo` AS select `tb_rtn`.`id` AS `id`,`tb_devinfo`.`name` AS `devname`,`tb_rtn`.`dvid` AS `dvid`,`tb_userinfo`.`name` AS `username`,`tb_userinfo`.`email` AS `email`,`tb_userinfo`.`tel` AS `tel` from ((`tb_rtn` join `tb_devinfo` on((`tb_rtn`.`dvid` = `tb_devinfo`.`id`))) join `tb_userinfo` on((`tb_rtn`.`rtnerid` = `tb_userinfo`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-12 11:09:30
