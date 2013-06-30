/*
SQLyog Community v11.01 (64 bit)
MySQL - 5.1.68-community : Database - contactsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`contactsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `contactsystem`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(2) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

/*Table structure for table `company_type` */

DROP TABLE IF EXISTS `company_type`;

CREATE TABLE `company_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `company_type` */

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int(11) unsigned NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone_number` varchar(50) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `site` varchar(400) DEFAULT NULL,
  `company_name` varchar(1000) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `company_type_id` int(11) unsigned DEFAULT NULL,
  `company_contact` varchar(100) DEFAULT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `zip_code` varchar(100) DEFAULT NULL,
  `frequency` varchar(50) DEFAULT NULL,
  `business` text,
  `sub_company_id` int(11) unsigned DEFAULT NULL,
  `remark` text,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contact` */

/*Table structure for table `sub_company` */

DROP TABLE IF EXISTS `sub_company`;

CREATE TABLE `sub_company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sub_company_name` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `sub_company` */

insert  into `sub_company`(`id`,`sub_company_name`) values (1,'宝盛投资股份有限公司'),(2,'浙江宝盛建设集团有限公司'),(3,'浙江宝盛旅业投资有限公司'),(4,'宝盛置业管理中心'),(5,'投资、一站式管理中心'),(6,'矿业'),(7,'浙江宝盛建设集团有限公司'),(8,'浙江宝盛酒店管理有限公司'),(9,'杭州萧山宝盛宾馆有限公司'),(10,'泰安肥城宝盛大酒店有限公司'),(11,'杭州宝盛水博园大酒店有限公司'),(12,'杭州宝盛集团美国休斯顿公司'),(13,'浙江宝盛置业有限公司'),(14,'天台县鑫远矿业有限公司');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
