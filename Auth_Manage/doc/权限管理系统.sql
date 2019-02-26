/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.0.41-community-nt : Database - autho_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`autho_manage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `autho_manage`;

/*Table structure for table `auth_info` */

DROP TABLE IF EXISTS `auth_info`;

CREATE TABLE `auth_info` (
  `auth_id` int(11) NOT NULL auto_increment,
  `parent_id` int(11) default NULL COMMENT '父id为空或为0，表示一级权限',
  `auth_name` varchar(100) default NULL,
  `auth_desc` varchar(300) default NULL,
  `auth_grade` int(11) default NULL,
  `auth_type` char(1) default NULL COMMENT '1 模块 、2  列表、 3  按钮',
  `auth_url` varchar(100) default NULL,
  `auth_code` varchar(100) default NULL,
  `auth_order` int(11) default NULL,
  `auth_state` char(1) default '1' COMMENT '1 启用 、0 禁用',
  `create_by` int(11) default NULL,
  `create_time` datetime default NULL,
  `update_by` int(11) default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `auth_info` */

insert  into `auth_info`(`auth_id`,`parent_id`,`auth_name`,`auth_desc`,`auth_grade`,`auth_type`,`auth_url`,`auth_code`,`auth_order`,`auth_state`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,0,'用户管理','用户管理',1,'1',NULL,NULL,0,'1',1,'2017-11-14 16:54:13',1,'2017-11-14 16:54:17'),(2,1,'用户列表','用户列表',2,'2','/user/list.action',NULL,0,'1',1,'2017-11-14 16:56:11',1,'2017-11-14 16:56:15'),(3,2,'添加用户','添加用户',3,'3','/user/addUser.action','user-addUser',0,'1',1,'2017-11-14 16:56:59',1,'2017-11-14 16:57:02'),(4,0,'角色管理','角色管理',1,'1',NULL,NULL,0,'1',1,'2017-11-14 16:57:48',1,'2017-11-14 16:57:52'),(5,4,'角色列表','角色列表',2,'2','/role/list.action',NULL,0,'1',1,'2017-11-14 16:58:47',1,'2017-11-14 16:58:50'),(6,5,'添加角色','添加角色',3,'3',NULL,'role-addRole',0,'1',1,'2017-11-14 16:59:25',1,'2017-11-14 16:59:29'),(7,0,'权限管理','权限管理',1,'1',NULL,NULL,0,'1',1,'2017-11-14 17:00:13',1,'2017-11-14 17:00:18'),(8,7,'权限树','权限树',2,'2','/auth/list.action',NULL,0,'1',1,'2017-11-14 17:02:11',1,'2017-11-14 17:02:14'),(9,8,'添加权限','添加权限',3,'3',NULL,'auth-addAuth',0,'1',1,'2017-11-14 17:03:48',1,'2017-11-14 17:03:52'),(36,2,'删除用户','deleteUser',NULL,'3','user/deleteUser','user-deleteUser',NULL,'1',NULL,NULL,NULL,NULL),(37,2,'查询用户','selectUser',NULL,'3','user/findUser','selectUser',NULL,'1',NULL,NULL,NULL,NULL),(38,2,'修改用户','uodateUser',NULL,'3','user/updateUser','user-updateUser',NULL,'1',NULL,NULL,NULL,NULL),(39,2,'分配角色','assignRole',NULL,'3','user/assignRole','user-assignRole',NULL,'1',NULL,NULL,NULL,NULL),(40,5,'角色禁用','Role-forbidden',NULL,'3','Role-forbidden','Role-forbidden',NULL,'1',NULL,NULL,NULL,NULL),(41,5,'角色启用','角色启用',NULL,'3','RoleStart','RoleStart',NULL,'1',NULL,NULL,NULL,NULL),(42,2,'分配权限','assignAuth',NULL,'3','role/assignAuth','user-assignAuth',NULL,'1',NULL,NULL,NULL,NULL),(43,5,'分配权限','分配权限',NULL,'3','role-assignAuth','role-assignAuth',NULL,'1',NULL,NULL,NULL,NULL),(55,0,'用户组管理','用户组',NULL,'1','','',NULL,'1',NULL,NULL,NULL,NULL),(57,55,'用户组','用户组',NULL,'2','/user/grouplist.action','',NULL,'1',NULL,NULL,NULL,NULL),(58,2,'导出表格','导出表格',NULL,'3','exportexcel-user','exportexcel-user',NULL,'1',NULL,NULL,NULL,NULL),(60,2,'用户启用','用户启用',NULL,'3','startusing','startusing',NULL,'1',NULL,NULL,NULL,NULL),(61,2,'用户禁用','用户禁用',NULL,'3','forbidden','forbidden',NULL,'1',NULL,NULL,NULL,NULL),(62,2,'分配部门','分配部门',NULL,'3','user-assigngroup','user-assigngroup',NULL,'1',NULL,NULL,NULL,NULL),(63,2,'重置密码','重置密码',NULL,'3','user-resetpwd','user-resetpwd',NULL,'1',NULL,NULL,NULL,NULL),(64,5,'导出表格','导出表格',NULL,'3','role-exportExcel','role-exportExcel',NULL,'1',NULL,NULL,NULL,NULL),(65,8,'删除权限','删除权限',NULL,'3','auth-deleteAuth','auth-deleteAuth',NULL,'1',NULL,NULL,NULL,NULL),(66,8,'修改权限','修改权限',NULL,'3','auth-updateAuth','auth-updateAuth',NULL,'1',NULL,NULL,NULL,NULL),(67,8,'恢复权限','恢复权限',NULL,'3','auth-recoverAuth','auth-recoverAuth',NULL,'1',NULL,NULL,NULL,NULL),(68,57,'添加用户组','添加用户组',NULL,'3','group-addUserGroup','group-addUserGroup',NULL,'1',NULL,NULL,NULL,NULL),(69,57,'用户组禁用','用户组禁用',NULL,'3','group-forbiddenUserGroup','group-forbiddenUserGroup',NULL,'1',NULL,NULL,NULL,NULL),(70,57,'用户组启用','用户组启用',NULL,'3','group-startUserGroup','group-startUserGroup',NULL,'1',NULL,NULL,NULL,NULL),(71,57,'分配角色','分配角色',NULL,'3','group-assignRole','group-assignRole',NULL,'1',NULL,NULL,NULL,NULL),(109,0,'用户管理','dddd',NULL,'1','','',NULL,'0',NULL,NULL,NULL,NULL),(110,109,'fffffff','dddd',NULL,'2','ddddddd','',NULL,'0',NULL,NULL,NULL,NULL),(111,110,'用户管理1','fff',NULL,'3','ddddddd','auth-addAuthqq',NULL,'0',NULL,NULL,NULL,NULL),(112,109,'ff','ww',NULL,'2','ww','',NULL,'0',NULL,NULL,NULL,NULL),(113,109,'www','www',NULL,'2','www','',NULL,'0',NULL,NULL,NULL,NULL),(114,113,'ddddd','dddddddddd',NULL,'3','dddddddd','ddddddd',NULL,'0',NULL,NULL,NULL,NULL),(115,110,'aaa','aaa',NULL,'3','aaa','aa',NULL,'0',NULL,NULL,NULL,NULL);

/*Table structure for table `group_role` */

DROP TABLE IF EXISTS `group_role`;

CREATE TABLE `group_role` (
  `goup_role_id` int(11) NOT NULL auto_increment,
  `group_id` int(11) default NULL,
  `role_id` int(11) default NULL,
  PRIMARY KEY  (`goup_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组角色关系表';

/*Data for the table `group_role` */

insert  into `group_role`(`goup_role_id`,`group_id`,`role_id`) values (52,4,2),(53,4,3),(54,4,9),(55,5,3),(70,12,3),(71,3,2),(74,6,2),(75,1,9),(76,2,2);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL auto_increment,
  `role_name` varchar(100) default NULL,
  `role_desc` varchar(300) default NULL,
  `role_code` varchar(100) default NULL,
  `role_state` char(1) default '1' COMMENT '1 启用 0 禁用',
  `create_by` int(11) default NULL,
  `create_time` datetime default NULL,
  `update_by` int(11) default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`role_id`,`role_name`,`role_desc`,`role_code`,`role_state`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'超级管理员','超级管理员','supper_manage','1',1,'2017-11-14 16:49:12',1,'2017-11-14 16:49:20'),(2,'管理员','管理员','manage','1',NULL,NULL,NULL,NULL),(3,'普通用户','普通用户','common','1',NULL,NULL,NULL,NULL),(9,'项目主管','project','project','1',NULL,NULL,NULL,NULL);

/*Table structure for table `role_auth` */

DROP TABLE IF EXISTS `role_auth`;

CREATE TABLE `role_auth` (
  `role_auth_id` int(20) NOT NULL auto_increment,
  `role_id` int(15) default NULL,
  `auth_id` int(30) default NULL,
  PRIMARY KEY  (`role_auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_auth` */

insert  into `role_auth`(`role_auth_id`,`role_id`,`auth_id`) values (44,7,4),(45,7,5),(46,7,6),(77,5,1),(78,5,2),(79,5,3),(80,5,4),(81,5,5),(82,5,6),(83,5,7),(84,5,8),(85,5,9),(176,6,1),(177,6,2),(178,6,3),(179,6,36),(180,6,37),(181,6,38),(182,6,58),(251,8,1),(252,8,2),(253,8,3),(349,28,1),(350,28,2),(351,28,37),(472,1,1),(473,1,2),(474,1,3),(475,1,36),(476,1,37),(477,1,38),(478,1,39),(479,1,58),(480,1,60),(481,1,61),(482,1,62),(483,1,63),(484,1,4),(485,1,5),(486,1,6),(487,1,40),(488,1,41),(489,1,42),(490,1,43),(491,1,64),(492,1,7),(493,1,8),(494,1,9),(495,1,65),(496,1,66),(497,1,67),(498,1,55),(499,1,57),(500,1,68),(501,1,69),(502,1,70),(503,1,71),(630,9,55),(631,9,57),(632,9,68),(633,9,69),(634,9,70),(635,9,71),(636,3,4),(637,3,5),(638,3,6),(639,2,7),(640,2,8),(641,2,9);

/*Table structure for table `user_auth` */

DROP TABLE IF EXISTS `user_auth`;

CREATE TABLE `user_auth` (
  `user_auth_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL,
  `auth_id` int(11) default NULL,
  PRIMARY KEY  (`user_auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';

/*Data for the table `user_auth` */

insert  into `user_auth`(`user_auth_id`,`user_id`,`auth_id`) values (1,1,1),(2,1,2),(3,1,3),(19,1,4),(20,1,5),(21,1,6),(22,1,7),(23,1,8),(24,1,9),(25,1,10),(26,1,55),(27,1,57),(150,30,1),(151,30,2),(152,30,37),(153,27,1),(154,27,2),(155,27,37),(156,28,1),(157,28,2),(158,28,37),(159,34,1),(160,34,2),(161,34,37),(171,35,1),(172,35,2),(173,35,37),(174,36,1),(175,36,2),(176,36,37),(201,2,1),(202,2,2),(203,2,3),(204,2,36),(205,2,37),(206,2,38),(207,2,39),(208,2,42),(209,2,58),(210,2,60),(211,2,61),(212,2,62),(213,2,63),(223,37,1),(224,37,2),(225,37,37),(226,20,1),(227,20,2),(228,20,37),(239,16,1),(240,16,2),(241,16,37),(255,39,1),(256,39,2),(257,39,37),(274,40,1),(275,40,2),(276,40,37),(277,19,1),(278,19,2),(279,19,37),(283,41,1),(284,41,2),(285,41,3),(286,41,37),(287,41,38),(288,41,58),(293,42,1),(294,42,2),(295,42,37),(296,43,1),(297,43,2),(298,43,3),(338,17,1),(339,17,2),(340,17,3),(341,17,36),(342,17,37),(343,17,38),(344,17,39),(345,17,42),(346,17,58),(347,17,60),(348,17,61),(349,17,62),(350,17,63),(351,22,1),(352,22,2),(353,22,37);

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `group_id` int(11) NOT NULL auto_increment,
  `group_name` varchar(100) default NULL,
  `group_code` varchar(30) default NULL,
  `group_desc` varchar(200) default NULL,
  `group_state` char(1) default '1',
  PRIMARY KEY  (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组';

/*Data for the table `user_group` */

insert  into `user_group`(`group_id`,`group_name`,`group_code`,`group_desc`,`group_state`) values (1,'财务部','financial','财务组','1'),(2,'市场部','market','市场部','1'),(3,'人事部','personel','人事部','1'),(4,'后勤部','logistics','后勤部','0'),(5,'法务部','law','法务部','1'),(6,'营销部','sales','营销部','0');

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL auto_increment,
  `group_id` int(11) default NULL,
  `nick_name` varchar(50) default NULL,
  `user_name` varchar(50) default NULL,
  `user_pwd` varchar(100) default NULL,
  `user_type` char(1) default NULL COMMENT '1 超级管理员 、 2  管理员 、 3 普通用户',
  `user_state` char(1) default '0' COMMENT '0 未审核 、1 已审核',
  `is_delete` char(1) default '0' COMMENT '0 正常、 1 已删除',
  `create_by` int(11) default NULL,
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `update_by` int(11) default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `user_info` */

insert  into `user_info`(`user_id`,`group_id`,`nick_name`,`user_name`,`user_pwd`,`user_type`,`user_state`,`is_delete`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,2,'admin','admin','e10adc3949ba59abbe56e057f20f883e','1','1','0',1,'2017-11-14 12:16:53',1,'2018-08-25 00:00:00'),(2,6,'刘智龙','liuzhilong','e10adc3949ba59abbe56e057f20f883e','1','1','0',1,'2018-02-01 19:01:27',1,NULL),(14,4,'babycolor','babycolor','e10adc3949ba59abbe56e057f20f883e','3','0','0',1,'2018-08-19 20:24:33',1,'2018-08-19 00:00:00'),(15,2,'Aberdeen','Aberdeen','e10adc3949ba59abbe56e057f20f883e','3','1','0',1,'2017-12-12 20:26:02',NULL,NULL),(16,2,'Elizabeth','Elizabeth','e10adc3949ba59abbe56e057f20f883e','2','1','0',2,'2018-08-19 20:26:46',1,'2018-08-19 00:00:00'),(17,4,'Charles','Charles','e10adc3949ba59abbe56e057f20f883e','2','1','0',1,'2018-06-13 20:27:09',NULL,NULL),(18,4,'George','George','e10adc3949ba59abbe56e057f20f883e','3','1','0',2,'2018-08-19 20:27:33',1,'2018-08-21 00:00:00'),(19,6,'Thomas	','Thomas','e10adc3949ba59abbe56e057f20f883e','3','1','0',1,'2018-04-11 20:32:26',2,'2018-08-18 00:00:00'),(20,3,'Michelle','Michelle','e10adc3949ba59abbe56e057f20f883e','3','1','0',2,'2018-08-19 20:33:13',1,'2018-08-16 00:00:00'),(21,6,'Michael','Michael','e10adc3949ba59abbe56e057f20f883e','2','1','0',1,'2018-06-12 20:33:41',NULL,NULL),(22,4,'Benjamin	','Benjamin','e10adc3949ba59abbe56e057f20f883e','2','0','0',2,'2018-08-19 20:34:10',1,'2018-08-24 00:00:00'),(23,6,'Harrison','Harrison','e10adc3949ba59abbe56e057f20f883e','2','1','0',1,'2017-04-13 20:34:41',NULL,NULL),(43,2,'你好','test1','e10adc3949ba59abbe56e057f20f883e',NULL,'1','0',1,'2018-08-28 14:41:09',NULL,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL auto_increment,
  `role_id` int(11) default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `user_role` */

insert  into `user_role`(`user_role_id`,`role_id`,`user_id`) values (1,1,1),(3,3,1),(6,3,3),(16,1,26),(17,2,26),(34,1,18),(35,8,18),(37,3,28),(38,3,27),(39,3,30),(49,3,34),(51,3,35),(52,3,36),(53,2,2),(59,3,37),(62,3,22),(63,3,16),(64,3,39),(68,3,40),(69,3,41),(71,3,42),(73,3,17),(75,3,43),(76,3,20);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
