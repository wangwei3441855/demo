/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : webdemo

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-07-01 17:07:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resources_info
-- ----------------------------
DROP TABLE IF EXISTS `resources_info`;
CREATE TABLE `resources_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(100) NOT NULL,
  `resource_type` varchar(100) NOT NULL,
  `resource_content` varchar(200) NOT NULL,
  `resource_desc` varchar(200) NOT NULL,
  `enabled` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `resource_name` (`resource_name`),
  KEY `resource_name_2` (`resource_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources_info
-- ----------------------------
INSERT INTO `resources_info` VALUES ('1', '所有资源', 'requesturl', '/**', '所有页面', '1');
INSERT INTO `resources_info` VALUES ('2', 'user首页', 'requesturl', '/index.html', '首页', '1');
INSERT INTO `resources_info` VALUES ('3', 'user资源', 'requesturl', '/service/test/**', 'user能进入首页', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT 'id',
  `role_name` varchar(50) DEFAULT NULL COMMENT 'name',
  `descn` varchar(50) DEFAULT NULL COMMENT 'descn',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '管理员角色');
INSERT INTO `role` VALUES ('2', 'ROLE_USER', '用户角色');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '1');
INSERT INTO `role_resource` VALUES ('2', '2', '2');
INSERT INTO `role_resource` VALUES ('3', '3', '2');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `a` varchar(64) NOT NULL,
  `b` varchar(64) DEFAULT NULL,
  `c` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`a`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('a', 'b', 'c');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(64) NOT NULL DEFAULT '0' COMMENT 'id',
  `user_name` varchar(64) DEFAULT NULL COMMENT 'username',
  `password` varchar(64) DEFAULT NULL COMMENT 'password',
  `status` int(1) DEFAULT NULL COMMENT 'status',
  `descn` varchar(128) DEFAULT NULL COMMENT 'descd',
  PRIMARY KEY (`user_id`),
  KEY `AK_Key_1` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '1', '管理\r\n员');
INSERT INTO `user_info` VALUES ('2', 'wangwei', '381987d375be5b533fc11198005ab02d', '1', '用户\r\n');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) DEFAULT NULL COMMENT '用户表_id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表_id',
  KEY `FK_FK_USER_ROLE_ROLE` (`role_id`),
  KEY `FK_FK_USER_ROLE_USER` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('1', '2');
INSERT INTO `user_role` VALUES ('2', '2');
