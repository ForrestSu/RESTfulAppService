/*
Navicat MySQL Data Transfer

Source Server         : ACMlab
Source Server Version : 50538
Source Host           : 172.28.27.26:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2015-05-25 08:20:15
*/
-- need  create a databses;
CREATE DATABASE test;
use test;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_state
-- ----------------------------
DROP TABLE IF EXISTS `a_state`;
CREATE TABLE `a_state` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `states` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `states` (`states`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_state
-- ----------------------------

-- ----------------------------
-- Table structure for absence
-- ----------------------------
DROP TABLE IF EXISTS `absence`;
CREATE TABLE `absence` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `uid` int(32) DEFAULT NULL,
  `tid` int(32) DEFAULT NULL,
  `sdate` varchar(50) DEFAULT NULL,
  `edate` varchar(50) DEFAULT NULL,
  `reqdate` varchar(50) DEFAULT NULL,
  `reason` text,
  `state` varchar(50) DEFAULT NULL,
  `tmsg` text,
  `reserve` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of absence
-- ----------------------------
INSERT INTO `absence` VALUES ('1', '16', '10', '2015-05-16', '2015-06-16', '2015-05-16 14:26', 'tuii', '已通过', '曾康没有请客', '');
INSERT INTO `absence` VALUES ('6', '16', '13', '2015-04-18', '2015-06-18', '2015-05-18 00:48', 'fhbhj', '未审核', '曾康没有请客', '');
INSERT INTO `absence` VALUES ('7', '16', '13', '2015-04-18', '2015-06-18', '2015-05-18 00:49', 'qwer', '未审核', '曾康没有请客', '');
INSERT INTO `absence` VALUES ('8', '16', '17', '2015-04-18', '2015-06-18', '2015-05-18 00:51', 'dhhjjj', '已通过', '理由不充分', '');
INSERT INTO `absence` VALUES ('9', '14', '12', '2015-04-18', '2015-04-18', '2015-05-18 15:19', '就是任性！！', '审核中', '曾康没有请客', '');
INSERT INTO `absence` VALUES ('18', '16', '17', '2015-10-18', '2015-11-18', '2015-05-18 20:32', '测试backlash', '未审核', '曾康没有请客', '');
INSERT INTO `absence` VALUES ('19', '16', '12', '2015-04-18', '2015-10-14', '2015-05-18 21:26', 'fgujkih', '已通过', '曾康没有请客', '');
INSERT INTO `absence` VALUES ('20', '14', '12', '2015-05-18', '2015-05-18', '2015-05-18 21:27', '5就是请假请假', '未通过', '曾康没有请客', '');
INSERT INTO `absence` VALUES ('21', '14', '11', '2015-04-17', '2015-06-19', '2015-05-18 22:24', '世界这么大，我想去看看', '审核中', '曾康没有请客', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `userType` int(32) DEFAULT NULL,
  `stunumber` varchar(50) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `regdate` varchar(50) DEFAULT NULL,
  `valid` int(32) NOT NULL DEFAULT '0',
  `reserve` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', '李某某', '123456', '0', '201141842101', '18212345678', '男', '2015-05-03 17:05', '0', '');
INSERT INTO `user` VALUES ('8', '王某某', '123456', '0', '201141842102', '18211112222', '男', '2015-05-03 17:07', '0', '');
INSERT INTO `user` VALUES ('9', '李芳', '123456', '0', '201141842103', '18222223333', '男', '2015-05-03 17:08', '0', '');
INSERT INTO `user` VALUES ('10', '黄上上', '123456', '1', '201141842104', '18222224444', '女', '2015-05-03 17:08', '0', '');
INSERT INTO `user` VALUES ('11', 'test', '123456', '1', '201141842105', '18233336666', '男', '2015-05-03 17:09', '0', '');
INSERT INTO `user` VALUES ('12', '钟雄辉', '123456', '1', '20', '18255556666', '女', '2015-05-03 17:10', '0', '');
INSERT INTO `user` VALUES ('13', '沈岳', '123456', '1', '1', '18266664444', '女', '2015-05-04 10:01', '0', '');
INSERT INTO `user` VALUES ('14', 'hd', '123456', '0', '2', '18266665555', '女', '2015-05-04 10:02', '0', '');
INSERT INTO `user` VALUES ('15', 'hui', '123456', '0', '201141842117', '15717411423', '男', '2015-05-15 18:51', '0', '');
INSERT INTO `user` VALUES ('16', 'hh', '12345', '0', '123', '15717411243', '男', '2015-05-15 19:04', '0', '');
INSERT INTO `user` VALUES ('17', 'huihui', '1234', '1', '201141842119', '15717411423', '男', '2015-05-17 10:22', '0', '');
