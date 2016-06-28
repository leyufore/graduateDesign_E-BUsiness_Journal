-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: multiplePlatform
-- ------------------------------------------------------
-- Server version	5.7.12

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `img_title` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `item_type` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `trip_max_day` bigint(20) DEFAULT NULL,
  `accom_nigth` bigint(20) DEFAULT NULL,
  `prov` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `from_location` varchar(255) DEFAULT NULL,
  `to_location` varchar(255) DEFAULT NULL,
  `fee_included_desc` varchar(255) DEFAULT NULL,
  `fee_excluded_desc` varchar(255) DEFAULT NULL,
  `order_info` varchar(255) DEFAULT NULL,
  `back_traffic_type` bigint(20) DEFAULT NULL,
  `back_traffic_no` varchar(255) DEFAULT NULL,
  `go_traffic_type` bigint(20) DEFAULT NULL,
  `go_traffic_no` varchar(255) DEFAULT NULL,
  `item_sku_package_name` varchar(255) DEFAULT NULL,
  `item_sku_price_type` bigint(20) DEFAULT NULL,
  `item_sku_stock` bigint(20) DEFAULT NULL,
  `item_sku_price` bigint(20) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `market_rice` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (27,2200781488795,'http://localhost:8080/GreenAppleGraduateDesign/uploadImg/20160606182824.jpeg','测试的标题','测试台湾三天两夜游玩沙箱测试',1,'测试的商品描述',3,2,'广东','佛山','武汉','上海','测试的费用包含','测试的费用不好','需知安全金蛇',1,'CA123',1,'CA213','测试套餐',1,20,9,0.01,10,1),(29,2200782394906,'http://localhost:8080/GreenAppleGraduateDesign/uploadImg/20160606182824.jpeg','测试的标题','测试九寨沟天堂游玩沙箱测试',1,'测试的商品描述',3,2,'广东','佛山','武汉','上海','测试的费用包含','测试的费用不好','需知安全金蛇',1,'CA123',1,'CA213','测试套餐',1,20,9,0.01,10,0),(31,2200781488796,'http://localhost:8080/GreenAppleGraduateDesign/uploadImg/20160606184514.jpeg','测试的标题','测试天天向上沙箱测试',1,'测试的商品描述',3,2,'广东','佛山','武汉','上海','测试的费用包含','测试的费用不好','需知安全金蛇',1,'CA123',1,'CA213','测试套餐',1,20,9,0.01,10,0),(32,2200783500121,'http://localhost:8080/GreenAppleGraduateDesign/uploadImg/20160606184918.jpeg','测试的标题','测试我要去旅游沙箱测试',1,'测试的商品描述',3,2,'广东','佛山','武汉','上海','测试的费用包含','测试的费用不好','需知安全金蛇',1,'CA123',1,'CA213','测试套餐',1,20,9,0.01,10,0),(33,2200783500130,'http://localhost:8080/GreenAppleGraduateDesign/uploadImg/20160606190712.jpeg','测试的标题','测试管你去哪儿旅哟沙箱测试',1,'测试的商品描述',3,2,'广东','佛山','武汉','上海','测试的费用包含','测试的费用不好','需知安全金蛇',1,'CA123',1,'CA213','测试套餐',1,20,9,0.01,10,1),(34,2200781749400,'http://localhost:8080/GreenAppleGraduateDesign/uploadImg/20160607110103.jpeg','测试的标题','测试aaaa沙箱测试',1,'测试的商品描述',3,2,'广东','佛山','武汉','上海','测试的费用包含','测试的费用不好','需知安全金蛇',1,'CA123',1,'CA213','测试套餐',1,20,9,0.01,10,0),(37,2200784396489,'http://localhost:8080/GreenAppleGraduateDesign/uploadImg/20160607153204.jpeg','测试的标题','测试bbbb沙箱测试',1,'测试的商品描述',3,2,'广东','佛山','武汉','上海','测试的费用包含','测试的费用不好','需知安全金蛇',1,'CA123',1,'CA213','测试套餐',1,20,9,0.01,10,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trade` (
  `id` int(11) NOT NULL,
  `order_id` mediumtext COLLATE utf8_bin,
  `status` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `order_title` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `price` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `count` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `total_price` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `buyer_nick` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `platform` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `session_taobao` varchar(255) DEFAULT NULL,
  `session_yhd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'leyufore','leyufore','610230519f76d16e0f64b6c0974a361eb587c672fb9d7dd3651880377','2cc2238c8c12da12ef05937db0d01f24');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-26 14:51:01
