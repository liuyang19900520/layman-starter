-- MySQL dump 10.13  Distrib 8.0.19, for osx10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: layman_starter
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `starter_test`
--

DROP TABLE IF EXISTS `starter_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `starter_test` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `age` int DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测试用';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `starter_test`
--

LOCK TABLES `starter_test` WRITE;
/*!40000 ALTER TABLE `starter_test` DISABLE KEYS */;
INSERT INTO `starter_test` VALUES (1,'layman',30,'liuyang19900520@hotmail.com');
/*!40000 ALTER TABLE `starter_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_error_log`
--

DROP TABLE IF EXISTS `sys_error_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_error_log` (
  `error_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `error_code` varchar(32) DEFAULT NULL COMMENT '日志类型(字典)',
  `error_msg` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `class_name` varchar(255) DEFAULT NULL COMMENT '类名称',
  `method` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '方法名称',
  `path` varchar(255) DEFAULT NULL COMMENT '路由',
  `detail` varchar(255) DEFAULT NULL COMMENT '详情',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` bigint DEFAULT NULL COMMENT '更新用户',
  `delete_flg` char(1) DEFAULT '0' COMMENT '数据状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`error_log_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='错误日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_error_log`
--

LOCK TABLES `sys_error_log` WRITE;
/*!40000 ALTER TABLE `sys_error_log` DISABLE KEYS */;
INSERT INTO `sys_error_log` VALUES (1,'B0001','系统内部出错','com.liuyang19900520.layman.starter.module.test.controller.StarterTestController','user','/test/starterTest/users/1','/ by zero','2020-10-23 23:36:31',NULL,'2020-10-23 23:36:31',NULL,'0',NULL),(2,'B0001','系统内部出错','com.liuyang19900520.layman.starter.module.test.controller.StarterTestController','user','/test/starterTest/users/1','/ by zero','2020-10-23 23:37:17',NULL,'2020-10-23 23:37:17',NULL,'0',NULL),(3,'B0001','系统内部出错','com.liuyang19900520.layman.starter.module.test.controller.StarterTestController','user','/test/starterTest/users/1','/ by zero','2020-10-23 23:40:01',NULL,'2020-10-23 23:40:01',NULL,'0',NULL),(4,'B0001','系统内部出错','com.liuyang19900520.layman.starter.module.test.controller.StarterTestController','user','/test/starterTest/users/1','/ by zero','2020-10-23 23:43:10',NULL,'2020-10-23 23:43:10',NULL,'0',NULL),(5,'B0001','系统内部出错','com.liuyang19900520.layman.starter.module.test.controller.StarterTestController','user','/test/starterTest/users/1','/ by zero','2020-10-25 12:11:16',NULL,'2020-10-25 12:11:16',NULL,'0',NULL);
/*!40000 ALTER TABLE `sys_error_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-26 11:10:34
