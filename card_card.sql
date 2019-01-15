-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: card
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `flag` varchar(45) DEFAULT '0',
  `addby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (31,'徐森威','男','这是部门','121312113','12121','411038790@qq.com','sasasa','0','普通'),(32,'梁毅','男','浙江省杭州市江干区','21213121','1112121','313131@qq.com','擦掉的擦擦擦','0','普通'),(38,'aa','男','浙江省杭州市江干区','21212','2112','212cda@qq.com','cdacad','0','普通'),(39,'安琪拉','男','浙江省杭州市江干区','1875888153','17836812','31615613@qq.com','浙江省杭州市江干区','0','普通'),(40,'吕布','男','浙江省杭州市江干区','1875888155','33336812','cdajnskn@qq.com','浙江省杭州市江干区','1','普通'),(41,'百里守约','女','浙江省杭州市江干区','1875888156','47836812','cdaca@qq.com','浙江省杭州市江干区','0','普通'),(42,'百里玄策','男','浙江省杭州市江干区','1875888157','55556812','1783183@qq.com','浙江省杭州市江干区','0','普通'),(44,'庄周','男','浙江省杭州市江干区','1875888159','34343812','cdnjk@qq.com','浙江省杭州市江干区','0','普通'),(45,'芈月','女','浙江省杭州市江干区','18758881564','99936812','3178@qq.com','浙江省杭州市江干区','0','徐森威'),(46,'测试1','男','浙江省杭州市江干区','1212131','2121312','1313@qq.com','cdacacacad','0','徐森威'),(49,'测试用户12','男','浙江省杭州市江干','213121312','131213121','131212@qq.com','粗大的擦擦打岔','0','徐森威'),(50,'测试用户14','男','浙江省杭州市江干区','2131212','21112','xacxadn@qq.xo','小爱潇洒潇洒','0','徐森威'),(51,'牛魔','男','测试部门','11111111111','11111111111','3121d@qq.com','cddcsda','0','徐森威'),(65,'典韦','男','测试部门啊啊啊啊','271827182781','21728127','2167261@qq.com','cdjk','0','徐森威');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-31  8:28:32
