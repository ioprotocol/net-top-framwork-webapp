/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.6.30 : Database - shop_sale
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`shop_sale` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shop_sale`;

/*Table structure for table `sys_account` */

DROP TABLE IF EXISTS `sys_account`;

CREATE TABLE `sys_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,标识',
  `account` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登录帐号',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登录密码',
  `status` int(11) DEFAULT '0' COMMENT '帐号状态0:打开 其他：关闭',
  `role_number` int(11) DEFAULT NULL COMMENT '外键,所属角色ID',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `remark` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  KEY `fk_role_number` (`role_number`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='系统账号表';

/*Data for the table `sys_account` */

insert  into `sys_account`(`id`,`account`,`password`,`status`,`role_number`,`name`,`sex`,`mobile`,`email`,`address`,`photo`,`remark`) values (1,'administrator','e10adc3949ba59abbe56e057f20f883e',0,1,'管理员','男','13562970712','xsy870712@163.com','山东临沂',NULL,'');

/*Table structure for table `sys_op_log` */

DROP TABLE IF EXISTS `sys_op_log`;

CREATE TABLE `sys_op_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `resource_number` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `remark` text COLLATE utf8_bin,
  `op_time` datetime DEFAULT NULL,
  `op_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_id` (`account_id`),
  KEY `fk_resource_id` (`resource_number`),
  KEY `idx_log_op_time` (`op_time`)
) ENGINE=MyISAM AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_op_log` */

insert  into `sys_op_log`(`id`,`account_id`,`resource_number`,`remark`,`op_time`,`op_ip`) values (5,1,'系统登出','系统登出','2016-07-15 21:42:25','0:0:0:0:0:0:0:1'),(4,1,'系统登录','系统登录','2016-07-15 21:42:19','0:0:0:0:0:0:0:1'),(6,1,'系统登出','系统登出','2016-07-19 15:30:22','0:0:0:0:0:0:0:1'),(7,1,'系统登出','系统登出','2016-07-19 15:35:22','0:0:0:0:0:0:0:1'),(8,1,'系统登录','系统登录','2016-07-19 15:39:07','0:0:0:0:0:0:0:1'),(9,1,'系统登录','系统登录','2016-07-19 15:54:45','0:0:0:0:0:0:0:1'),(10,1,'系统登录','系统登录','2016-07-19 15:57:00','0:0:0:0:0:0:0:1'),(11,1,'系统登录','系统登录','2016-07-19 16:02:07','0:0:0:0:0:0:0:1'),(12,1,'系统登录','系统登录','2016-07-19 16:09:08','0:0:0:0:0:0:0:1'),(13,1,'系统登录','系统登录','2016-07-19 16:11:45','0:0:0:0:0:0:0:1'),(14,1,'系统登录','系统登录','2016-07-19 16:35:09','0:0:0:0:0:0:0:1'),(15,1,'系统登录','系统登录','2016-07-19 16:36:55','0:0:0:0:0:0:0:1'),(16,1,'系统登录','系统登录','2016-07-19 16:39:08','0:0:0:0:0:0:0:1'),(17,1,'系统登录','系统登录','2016-07-19 16:43:01','0:0:0:0:0:0:0:1'),(18,1,'系统登录','系统登录','2016-07-19 16:44:04','0:0:0:0:0:0:0:1'),(19,1,'系统登录','系统登录','2016-07-19 16:53:06','0:0:0:0:0:0:0:1'),(20,1,'系统登录','系统登录','2016-07-19 16:58:27','0:0:0:0:0:0:0:1'),(21,1,'系统登录','系统登录','2016-07-19 16:59:24','0:0:0:0:0:0:0:1'),(22,1,'系统登录','系统登录','2016-07-19 17:00:13','0:0:0:0:0:0:0:1'),(23,1,'系统登出','系统登出','2016-07-19 17:02:44','0:0:0:0:0:0:0:1'),(24,1,'系统登录','系统登录','2016-07-19 17:17:00','0:0:0:0:0:0:0:1'),(25,1,'系统登录','系统登录','2016-07-19 18:11:29','0:0:0:0:0:0:0:1'),(26,1,'系统登出','系统登出','2016-07-19 18:15:00','0:0:0:0:0:0:0:1'),(27,1,'系统登录','系统登录','2016-07-19 18:15:10','0:0:0:0:0:0:0:1'),(28,1,'系统登录','系统登录','2016-07-19 18:18:22','0:0:0:0:0:0:0:1'),(29,1,'系统登出','系统登出','2016-07-19 18:23:52','0:0:0:0:0:0:0:1'),(30,1,'系统登录','系统登录','2016-07-19 18:24:00','0:0:0:0:0:0:0:1'),(31,1,'系统登录','系统登录','2016-07-19 18:27:34','0:0:0:0:0:0:0:1'),(32,1,'系统登录','系统登录','2016-07-19 18:30:07','0:0:0:0:0:0:0:1'),(33,1,'系统登出','系统登出','2016-07-19 18:32:56','0:0:0:0:0:0:0:1'),(34,1,'系统登录','系统登录','2016-07-19 18:35:52','0:0:0:0:0:0:0:1'),(35,1,'系统登出','系统登出','2016-07-19 18:36:33','0:0:0:0:0:0:0:1'),(36,1,'系统登录','系统登录','2016-07-19 19:24:21','0:0:0:0:0:0:0:1'),(37,1,'系统登出','系统登出','2016-07-19 19:24:36','0:0:0:0:0:0:0:1'),(38,1,'系统登录','系统登录','2016-07-20 21:21:42','0:0:0:0:0:0:0:1'),(39,1,'系统登录','系统登录','2016-07-20 21:32:31','0:0:0:0:0:0:0:1'),(40,1,'系统登录','系统登录','2016-07-20 21:42:13','0:0:0:0:0:0:0:1'),(41,1,'系统登录','系统登录','2016-07-20 21:47:10','0:0:0:0:0:0:0:1'),(42,1,'系统登录','系统登录','2016-07-25 20:10:27','0:0:0:0:0:0:0:1'),(43,1,'系统登录','系统登录','2016-07-25 20:13:21','0:0:0:0:0:0:0:1'),(44,1,'系统登录','系统登录','2016-07-25 20:21:41','0:0:0:0:0:0:0:1'),(45,1,'系统登录','系统登录','2016-07-25 20:31:08','0:0:0:0:0:0:0:1'),(46,1,'系统登录','系统登录','2016-07-25 20:34:00','0:0:0:0:0:0:0:1'),(47,1,'系统登录','系统登录','2016-07-25 20:37:19','0:0:0:0:0:0:0:1'),(48,1,'系统登录','系统登录','2016-07-25 20:47:08','0:0:0:0:0:0:0:1'),(49,1,'系统登录','系统登录','2016-07-25 20:49:16','0:0:0:0:0:0:0:1'),(50,1,'系统登录','系统登录','2016-07-25 21:36:45','0:0:0:0:0:0:0:1'),(51,1,'系统登录','系统登录','2016-07-25 21:46:46','0:0:0:0:0:0:0:1'),(52,1,'系统登录','系统登录','2016-07-25 21:50:57','0:0:0:0:0:0:0:1'),(53,1,'系统登录','系统登录','2016-07-25 21:53:30','0:0:0:0:0:0:0:1'),(54,1,'系统登录','系统登录','2016-07-25 22:04:47','0:0:0:0:0:0:0:1'),(55,1,'系统登录','系统登录','2016-07-28 19:52:26','0:0:0:0:0:0:0:1'),(56,1,'系统登录','系统登录','2016-07-28 20:01:03','0:0:0:0:0:0:0:1'),(57,1,'系统登录','系统登录','2016-07-28 20:01:13','0:0:0:0:0:0:0:1'),(58,1,'系统登录','系统登录','2016-07-28 20:01:19','0:0:0:0:0:0:0:1'),(59,1,'系统登录','系统登录','2016-07-28 20:35:19','0:0:0:0:0:0:0:1'),(60,1,'系统登录','系统登录','2016-07-28 20:37:09','0:0:0:0:0:0:0:1'),(61,1,'系统登录','系统登录','2016-07-28 20:39:35','0:0:0:0:0:0:0:1'),(62,1,'系统登录','系统登录','2016-07-28 20:53:20','0:0:0:0:0:0:0:1'),(63,1,'系统登录','系统登录','2016-07-28 21:19:18','0:0:0:0:0:0:0:1'),(64,1,'系统登录','系统登录','2016-07-28 21:28:09','0:0:0:0:0:0:0:1');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `number` varchar(255) NOT NULL COMMENT '资源编码',
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(255) DEFAULT NULL COMMENT '资源访问路径',
  `is_show` smallint(6) DEFAULT '1' COMMENT '是否在菜单中显示',
  `show_order` int(11) DEFAULT '1' COMMENT '显示顺序',
  `resource_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`number`),
  KEY `fk_resource_type_id` (`resource_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`number`,`name`,`url`,`is_show`,`show_order`,`resource_type_id`) values ('系统角色管理','角色管理','./system/role/list',1,2,1),('系统数据维护','系统维护','./system/mainten/list',1,1,2),('系统资源类型管理','资源类型','./system/resourcetype/list',1,2,2),('系统资源管理','系统资源','./system/resource/list',1,3,2),('系统日志','系统日志','./system/log/list',1,4,2),('修改个人密码','密码修改','./system/password/getSave',1,3,1),('修改个人信息','个人信息','./system/account/view',1,4,1),('系统帐号管理','账号管理','./system/account/list',1,1,1);

/*Table structure for table `sys_resource_type` */

DROP TABLE IF EXISTS `sys_resource_type`;

CREATE TABLE `sys_resource_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `show_order` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_resource_type` */

insert  into `sys_resource_type`(`id`,`name`,`show_order`,`icon`) values (1,'用户管理',100,'leftico08.png'),(2,'系统维护',101,'leftico06.png');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `number` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `remark` text COLLATE utf8_bin,
  PRIMARY KEY (`number`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`number`,`name`,`remark`) values (1,'超级管理员','系统管理员，拥有最高系统权限');

/*Table structure for table `sys_role_popedom` */

DROP TABLE IF EXISTS `sys_role_popedom`;

CREATE TABLE `sys_role_popedom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_number` int(11) NOT NULL,
  `resource_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_number` (`role_number`,`resource_number`),
  KEY `fk_resource_number` (`resource_number`)
) ENGINE=MyISAM AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='系统权限表';

/*Data for the table `sys_role_popedom` */

insert  into `sys_role_popedom`(`id`,`role_number`,`resource_number`) values (74,1,'系统日志'),(73,1,'系统资源管理'),(72,1,'系统资源类型管理'),(71,1,'系统数据维护'),(70,1,'修改个人信息'),(69,1,'修改个人密码'),(68,1,'系统角色管理'),(67,1,'系统帐号管理');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
