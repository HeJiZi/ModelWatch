/*
Navicat MySQL Data Transfer

Source Server         : 47.107.100.245
Source Server Version : 50724
Source Host           : 47.107.100.245:3306
Source Database       : modelwatch

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-01-05 14:11:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrators
-- ----------------------------
DROP TABLE IF EXISTS `administrators`;
CREATE TABLE `administrators` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_username` varchar(255) DEFAULT NULL,
  `a_password` varchar(255) DEFAULT NULL,
  `a_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `C_id` int(15) NOT NULL AUTO_INCREMENT,
  `C_name` varchar(30) DEFAULT NULL,
  `C_context` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`C_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1111111114 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_content` varchar(512) DEFAULT NULL,
  `com_mid` bigint(20) NOT NULL,
  `com_uid` int(10) NOT NULL,
  `com_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `com_state` tinyint(3) unsigned DEFAULT '1' COMMENT '0为被屏蔽状态，1为正常状态',
  `com_like_num` int(11) DEFAULT '0' COMMENT '点赞数',
  `com_dislike_num` int(11) DEFAULT '0' COMMENT '点踩数',
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for invitation
-- ----------------------------
DROP TABLE IF EXISTS `invitation`;
CREATE TABLE `invitation` (
  `Inv_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Inv_state` tinyint(1) unsigned DEFAULT '0' COMMENT '0:默认;1:接受邀请',
  `Inv_pid` bigint(20) NOT NULL,
  `Inv_uid` int(10) NOT NULL,
  PRIMARY KEY (`Inv_pid`,`Inv_uid`),
  KEY `Inv_uid` (`Inv_uid`),
  CONSTRAINT `invitation_ibfk_1` FOREIGN KEY (`Inv_uid`) REFERENCES `user` (`U_id`),
  CONSTRAINT `invitation_ibfk_2` FOREIGN KEY (`Inv_pid`) REFERENCES `project` (`P_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `L_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `L_context` varchar(255) DEFAULT NULL,
  `L_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `L_pid` bigint(20) DEFAULT NULL,
  `L_uid` int(10) DEFAULT NULL,
  `L_mid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`L_id`),
  KEY `L_pid` (`L_pid`),
  KEY `log_ibfk_2` (`L_uid`),
  KEY `log_ibfk_3` (`L_mid`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`L_pid`) REFERENCES `project` (`P_id`) ON DELETE CASCADE,
  CONSTRAINT `log_ibfk_2` FOREIGN KEY (`L_uid`) REFERENCES `user` (`U_id`) ON DELETE SET NULL,
  CONSTRAINT `log_ibfk_3` FOREIGN KEY (`L_mid`) REFERENCES `model` (`M_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mark
-- ----------------------------
DROP TABLE IF EXISTS `mark`;
CREATE TABLE `mark` (
  `Mark_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Mark_uid` int(10) NOT NULL,
  `Mark_mid` bigint(20) NOT NULL,
  PRIMARY KEY (`Mark_uid`,`Mark_mid`),
  KEY `Mark_mid` (`Mark_mid`),
  CONSTRAINT `mark_ibfk_1` FOREIGN KEY (`Mark_mid`) REFERENCES `model` (`M_id`),
  CONSTRAINT `mark_ibfk_2` FOREIGN KEY (`Mark_uid`) REFERENCES `user` (`U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `M_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `M_data` varchar(255) DEFAULT NULL,
  `M_preview` varchar(255) DEFAULT '/resources/images/defaultModel.png',
  `M_name` varchar(30) DEFAULT NULL,
  `M_createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `M_pid` bigint(20) DEFAULT NULL,
  `M_cid` int(11) DEFAULT NULL,
  `m_state` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`M_id`),
  KEY `M_pid` (`M_pid`),
  KEY `M_cid` (`M_cid`),
  CONSTRAINT `model_ibfk_1` FOREIGN KEY (`M_pid`) REFERENCES `project` (`P_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `model_ibfk_2` FOREIGN KEY (`M_cid`) REFERENCES `class` (`C_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `P_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `P_createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `P_name` varchar(30) DEFAULT NULL,
  `P_context` text,
  `P_preview` varchar(255) DEFAULT NULL,
  `P_Uid` int(10) DEFAULT NULL,
  PRIMARY KEY (`P_id`),
  KEY `P_Uid` (`P_Uid`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`P_Uid`) REFERENCES `user` (`U_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `rep_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rep_com_id` int(11) DEFAULT NULL,
  `rep_r_id` bigint(20) DEFAULT NULL COMMENT '此回复针对的是哪条回复，如果回复类型为0（对评论的回复），被回复编号 rep_r_id 为空',
  `rep_u_id` int(11) DEFAULT NULL COMMENT '编写此回复的用户id',
  `rep_type` tinyint(4) DEFAULT NULL COMMENT '0为评论,1为回复',
  `rep_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复创建时间',
  `rep_content` varchar(512) DEFAULT NULL COMMENT '回复内容',
  `rep_like_num` int(11) DEFAULT '0' COMMENT '点赞数',
  `rep_dislike_num` int(11) DEFAULT '0' COMMENT '点踩数',
  `rep_state` tinyint(4) DEFAULT '1' COMMENT '0为被屏蔽状态,1为正常状态',
  PRIMARY KEY (`rep_id`),
  KEY `rep_com_id` (`rep_com_id`),
  KEY `rep_r_id` (`rep_r_id`),
  KEY `rep_u_id` (`rep_u_id`),
  CONSTRAINT `rep_com_id` FOREIGN KEY (`rep_com_id`) REFERENCES `comments` (`com_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `rep_r_id` FOREIGN KEY (`rep_r_id`) REFERENCES `reply` (`rep_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `rep_u_id` FOREIGN KEY (`rep_u_id`) REFERENCES `user` (`U_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for subscribe
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe` (
  `sub_id` int(11) NOT NULL AUTO_INCREMENT,
  `Sub_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Sub_pid` bigint(20) DEFAULT NULL,
  `Sub_uid` int(10) DEFAULT NULL,
  PRIMARY KEY (`sub_id`),
  KEY `Sub_uid` (`Sub_uid`),
  KEY `sub_pid` (`Sub_pid`),
  CONSTRAINT `Sub_uid` FOREIGN KEY (`Sub_uid`) REFERENCES `user` (`U_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `sub_pid` FOREIGN KEY (`Sub_pid`) REFERENCES `project` (`P_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `U_id` int(10) NOT NULL AUTO_INCREMENT,
  `U_username` varchar(30) DEFAULT NULL,
  `U_avater` varchar(255) DEFAULT '/resources/images/defalut.png',
  `U_password` varchar(150) DEFAULT NULL,
  `U_email` varchar(255) DEFAULT NULL,
  `U_sex` varchar(10) DEFAULT 'UnPublic',
  `U_birthday` date DEFAULT NULL,
  `U_signature` varchar(100) DEFAULT NULL,
  `U_createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `U_state` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`U_id`),
  UNIQUE KEY `U_username` (`U_username`),
  UNIQUE KEY `U_email` (`U_email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
