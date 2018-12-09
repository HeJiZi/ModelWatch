/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : modelwatch

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-23 16:28:43
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
-- Records of administrators
-- ----------------------------
INSERT INTO `administrators` VALUES ('1', '123', '123', '2018-09-11 15:23:43');
INSERT INTO `administrators` VALUES ('2', 'noone', 'noone', '2018-09-11 20:11:14');
INSERT INTO `administrators` VALUES ('3', 'we', '123', '2018-09-13 14:49:10');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `C_id` int(10) NOT NULL AUTO_INCREMENT,
  `C_name` varchar(30) DEFAULT NULL,
  `C_context` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`C_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '角色', '角色模型，多用于cg游戏等领域');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '很好看的灯笼', '2', '1', '2018-08-12 11:58:53', '1', '0', '0');
INSERT INTO `comments` VALUES ('4', '面数太少，细节不够', '2', '2', '2018-08-12 13:08:45', '1', '0', '0');
INSERT INTO `comments` VALUES ('5', '足球做的太光滑了，镜面反射不应该这么强', '3', '3', '2018-09-12 15:38:23', '1', '0', '0');
INSERT INTO `comments` VALUES ('8', '这个方块是干啥的?', '1', '3', '2018-09-12 15:39:25', '0', '0', '0');
INSERT INTO `comments` VALUES ('9', '这个方块也太小了。。', '1', '3', '2018-09-12 15:39:42', '0', '0', '0');
INSERT INTO `comments` VALUES ('10', '我好像在哪里看过这个模型。。', '1', '3', '2018-09-12 15:40:06', '1', '0', '0');
INSERT INTO `comments` VALUES ('11', '太小了', '5', '1', '2018-09-13 14:41:41', '1', '0', '0');
INSERT INTO `comments` VALUES ('12', '很好看的灯笼', '2', '1', '2018-09-14 09:03:31', '1', '0', '0');

-- ----------------------------
-- Table structure for invitation
-- ----------------------------
DROP TABLE IF EXISTS `invitation`;
CREATE TABLE `invitation` (
  `Inv_Time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Inv_state` tinyint(1) unsigned DEFAULT '0',
  `Inv_pid` bigint(20) NOT NULL,
  `Inv_uid` int(10) NOT NULL,
  PRIMARY KEY (`Inv_pid`,`Inv_uid`),
  KEY `Inv_uid` (`Inv_uid`),
  CONSTRAINT `invitation_ibfk_1` FOREIGN KEY (`Inv_uid`) REFERENCES `user` (`U_id`),
  CONSTRAINT `invitation_ibfk_2` FOREIGN KEY (`Inv_pid`) REFERENCES `project` (`P_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invitation
-- ----------------------------
INSERT INTO `invitation` VALUES ('2018-07-04 13:51:18', '1', '1', '2');
INSERT INTO `invitation` VALUES ('2018-09-12 15:42:20', '1', '1', '3');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `L_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `L_context` varchar(255) DEFAULT NULL,
  `L_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `L_pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`L_id`),
  KEY `L_pid` (`L_pid`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`L_pid`) REFERENCES `project` (`P_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '用户testname创建了项目', '2018-07-01 09:14:16', '4');
INSERT INTO `log` VALUES ('2', 'testname对模型1进行了上传', '2018-07-04 09:25:37', '2');
INSERT INTO `log` VALUES ('3', 'testname对模型1进行了上传', '2018-07-04 09:27:15', '2');
INSERT INTO `log` VALUES ('4', 'testname对模型1进行了上传', '2018-07-04 09:29:00', '2');
INSERT INTO `log` VALUES ('5', 'testname对模型1进行了上传', '2018-07-04 10:38:28', '2');
INSERT INTO `log` VALUES ('6', 'testname对模型1进行了上传', '2018-07-04 10:38:53', '2');
INSERT INTO `log` VALUES ('7', 'testname对模型1进行了上传', '2018-07-04 10:38:57', '2');
INSERT INTO `log` VALUES ('8', 'testname对模型1进行了上传', '2018-07-04 11:20:58', '2');
INSERT INTO `log` VALUES ('9', '用户HeJiZi创建了项目', '2018-07-04 11:26:50', '5');
INSERT INTO `log` VALUES ('10', '用户HeJiZi创建了模型：小男孩', '2018-07-04 11:34:57', '5');
INSERT INTO `log` VALUES ('11', 'HeJiZi对模型3进行了上传', '2018-07-04 12:00:54', '5');
INSERT INTO `log` VALUES ('12', 'HeJiZi对模型3进行了上传', '2018-07-04 12:03:22', '5');
INSERT INTO `log` VALUES ('13', 'HeJiZi对模型1进行了上传', '2018-07-04 12:12:34', '2');
INSERT INTO `log` VALUES ('14', 'HeJiZi对模型1进行了上传', '2018-07-04 12:12:58', '2');
INSERT INTO `log` VALUES ('15', 'HeJiZi对模型1进行了上传', '2018-07-04 12:13:23', '2');
INSERT INTO `log` VALUES ('16', 'HeJiZi对模型3进行了上传', '2018-07-04 12:13:51', '5');
INSERT INTO `log` VALUES ('17', 'HeJiZi对模型2进行了上传', '2018-07-04 12:16:46', '1');
INSERT INTO `log` VALUES ('18', 'HeJiZi对模型1进行了上传', '2018-07-04 12:17:40', '2');
INSERT INTO `log` VALUES ('19', 'HeJiZi对模型3进行了上传', '2018-07-04 12:17:55', '5');
INSERT INTO `log` VALUES ('20', 'HeJiZi对模型3进行了上传', '2018-07-04 12:18:04', '5');
INSERT INTO `log` VALUES ('21', 'HeJiZi对模型3进行了上传', '2018-07-04 12:18:15', '5');
INSERT INTO `log` VALUES ('22', 'HeJiZi对模型3进行了上传', '2018-07-04 12:18:24', '5');
INSERT INTO `log` VALUES ('23', 'HeJiZi对模型2进行了上传', '2018-07-04 12:20:51', '1');
INSERT INTO `log` VALUES ('24', '用户HeJiZi创建了模型：fu', '2018-07-04 12:55:13', '2');
INSERT INTO `log` VALUES ('25', 'HeJiZi对模型4进行了上传', '2018-07-04 12:55:39', '2');
INSERT INTO `log` VALUES ('26', 'HeJiZi对模型4进行了上传', '2018-07-04 13:01:09', '2');
INSERT INTO `log` VALUES ('27', 'HeJiZi对模型4进行了上传', '2018-07-04 13:01:50', '2');
INSERT INTO `log` VALUES ('28', 'HeJiZi对模型1进行了上传', '2018-07-04 13:02:04', '2');
INSERT INTO `log` VALUES ('29', 'HeJiZi对模型3进行了上传', '2018-07-04 13:48:11', '5');
INSERT INTO `log` VALUES ('30', '用户HeJiZi创建了模型：测试', '2018-07-04 13:48:35', '5');
INSERT INTO `log` VALUES ('31', 'HeJiZi对模型5进行了上传', '2018-07-04 13:48:50', '5');
INSERT INTO `log` VALUES ('32', 'HeJiZi对模型2进行了上传', '2018-09-12 10:44:12', '1');
INSERT INTO `log` VALUES ('33', '用户HeJiZi创建了项目', '2018-09-14 09:07:20', '6');
INSERT INTO `log` VALUES ('34', '用户HeJiZi创建了项目', '2018-11-05 09:41:31', '7');
INSERT INTO `log` VALUES ('35', '用户HeJiZi创建了模型：asd', '2018-11-05 09:43:01', '7');

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
-- Records of mark
-- ----------------------------
INSERT INTO `mark` VALUES ('2018-11-05 09:15:06', '1', '1');
INSERT INTO `mark` VALUES ('2018-07-01 08:59:35', '2', '1');
INSERT INTO `mark` VALUES ('2018-07-01 08:59:35', '2', '2');
INSERT INTO `mark` VALUES ('2018-09-12 15:39:34', '3', '1');

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `M_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `M_data` varchar(255) DEFAULT NULL,
  `M_preview` varchar(255) DEFAULT NULL,
  `M_name` varchar(30) DEFAULT NULL,
  `M_createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `M_pid` bigint(20) DEFAULT NULL,
  `M_cid` int(11) DEFAULT NULL,
  `m_state` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`M_id`),
  KEY `M_pid` (`M_pid`),
  KEY `M_cid` (`M_cid`),
  CONSTRAINT `model_ibfk_1` FOREIGN KEY (`M_pid`) REFERENCES `project` (`P_id`),
  CONSTRAINT `model_ibfk_2` FOREIGN KEY (`M_cid`) REFERENCES `class` (`C_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of model
-- ----------------------------
INSERT INTO `model` VALUES ('1', '/resources/upload/model/1.json', '/resources/upload/mPreview/1.png', 'logo', '2018-07-01 08:59:35', '2', '1', '1');
INSERT INTO `model` VALUES ('2', '/resources/upload/model/2.json', '/resources/upload/mPreview/2.png', '灯笼', '2018-07-01 08:59:35', '1', '1', '1');
INSERT INTO `model` VALUES ('3', '/resources/upload/model/3.json', '/resources/upload/mPreview/3.png', '篮球', '2018-07-04 11:34:57', '5', '1', '1');
INSERT INTO `model` VALUES ('4', '/resources/upload/model/4.json', '/resources/upload/mPreview/4.png', 'fu', '2018-07-04 12:55:13', '2', '1', '1');
INSERT INTO `model` VALUES ('5', '/resources/upload/model/5.json', '/resources/upload/mPreview/5.png', '测试', '2018-07-04 13:48:35', '5', '1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '2018-07-01 08:59:35', '星火', '星火是一个制作星星与火焰特效模型的项目', '/resources/images/1.jpeg', '1');
INSERT INTO `project` VALUES ('2', '2018-07-01 08:59:35', '流体', '流体是一个制作流体模型的项目', '/resources/images/2.jpeg', '1');
INSERT INTO `project` VALUES ('3', '2018-07-01 08:59:35', '火流体', '火流体是一个制作流体模型的项目', '/resources/images/2.jpeg', '2');
INSERT INTO `project` VALUES ('4', '2018-07-01 09:14:16', 'hello', 'fff', '/resources/upload/pjCover/1-1530407656410.jpeg', '1');
INSERT INTO `project` VALUES ('5', '2018-07-04 11:26:50', '星巴克', '不知道干什么的项目', '/resources/upload/pjCover/1-1530674810162.jpg', '1');
INSERT INTO `project` VALUES ('6', '2018-09-14 09:07:20', '阿萨德', 'waeasd', '/resources/upload/pjCover/1-1536887240308.jpeg', '1');
INSERT INTO `project` VALUES ('7', '2018-11-05 09:41:31', 'asd', 'ad', '/resources/upload/pjCover/1-1541382091669.docx', '1');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `rep_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rep_com_id` int(11) DEFAULT NULL,
  `rep_r_id` bigint(20) DEFAULT NULL COMMENT '此回复针对的是那条回复，如果回复类型为0，被回复编号与评论编号一致',
  `rep_u_id` int(11) DEFAULT NULL COMMENT '编写此回复的用户id',
  `rep_type` tinyint(4) DEFAULT NULL COMMENT '0为评论,1为回复',
  `rep_create_time` datetime DEFAULT NULL COMMENT '回复创建时间',
  `rep_content` varchar(512) DEFAULT NULL COMMENT '回复内容',
  `rep_like_num` int(11) DEFAULT '0' COMMENT '点赞数',
  `rep_dislike_num` int(11) DEFAULT '0' COMMENT '点踩数',
  `rep_state` tinyint(4) DEFAULT '0' COMMENT '0为被屏蔽状态,1为正常状态',
  PRIMARY KEY (`rep_id`),
  KEY `rep_com_id` (`rep_com_id`),
  KEY `rep_r_id` (`rep_r_id`),
  KEY `rep_u_id` (`rep_u_id`),
  CONSTRAINT `rep_com_id` FOREIGN KEY (`rep_com_id`) REFERENCES `comments` (`com_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `rep_r_id` FOREIGN KEY (`rep_r_id`) REFERENCES `reply` (`rep_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `rep_u_id` FOREIGN KEY (`rep_u_id`) REFERENCES `user` (`U_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for subscribe
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe` (
  `Sub_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Sub_pid` bigint(20) NOT NULL,
  `Sub_uid` int(10) NOT NULL,
  PRIMARY KEY (`Sub_pid`,`Sub_uid`),
  KEY `Sub_uid` (`Sub_uid`),
  CONSTRAINT `subscribe_ibfk_1` FOREIGN KEY (`Sub_pid`) REFERENCES `project` (`P_id`),
  CONSTRAINT `subscribe_ibfk_2` FOREIGN KEY (`Sub_uid`) REFERENCES `user` (`U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subscribe
-- ----------------------------
INSERT INTO `subscribe` VALUES ('2018-07-01 08:59:35', '1', '2');
INSERT INTO `subscribe` VALUES ('2018-09-13 14:43:19', '2', '1');
INSERT INTO `subscribe` VALUES ('2018-07-01 08:59:35', '2', '2');
INSERT INTO `subscribe` VALUES ('2018-07-01 08:59:35', '2', '3');
INSERT INTO `subscribe` VALUES ('2018-07-01 08:59:35', '3', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `U_id` int(10) NOT NULL AUTO_INCREMENT,
  `U_username` varchar(30) DEFAULT NULL,
  `U_avater` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'HeJiZi', '/resources/upload/useravater/1.jpeg', '1234', '842236625@qq.com', 'male', '2018-07-05', '太阳照常升起', '2018-07-01 08:59:35', '1');
INSERT INTO `user` VALUES ('2', 'hello', '/resources/upload/useravater/2.png', 'hello', '1160299174@zust.edu.cn', 'UnPublic', '2010-01-05', '你好', '2018-07-01 08:59:35', '1');
INSERT INTO `user` VALUES ('3', 'world', '/resources/upload/useravater/3.jpeg', 'world', '838578094@qq.com', 'UnPublic', '1999-06-08', '真实的我是一个自私又丑陋的小人', '2018-07-01 08:59:35', '1');
