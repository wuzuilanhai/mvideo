/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50710
 Source Host           : localhost
 Source Database       : mvideo

 Target Server Version : 50710
 File Encoding         : utf-8

 Date: 12/12/2016 12:11:25 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `admin`
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');
COMMIT;

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `parent_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `sub_time` datetime DEFAULT NULL,
  `user_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `configuration`
-- ----------------------------
DROP TABLE IF EXISTS `configuration`;
CREATE TABLE `configuration` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `val` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `configuration`
-- ----------------------------
BEGIN;
INSERT INTO `configuration` VALUES ('1', 'transcoder_vcodec', 'libx264', 'Video encoder', null), ('2', 'transcoder_bv', '500000', 'Video bitrate', null), ('3', 'transcoder_framerate', '25', 'Video frame rate', null), ('4', 'transcoder_acodec', 'libmp3lame', 'Audio encoder', null), ('5', 'transcoder_ba', '64000', 'Audio bitrate', null), ('6', 'transcoder_ar', '22050', 'Audio sample rate', null), ('7', 'transcoder_ab', '64', 'Audio data traffic', null), ('8', 'transcoder_ac', '2', 'Audio sound track', null), ('9', 'transcoder_qscale', '4', 'Video resolution', null);
COMMIT;

-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `record_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `mail`
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `sender_id` int(255) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `real_name` varchar(8) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `video`
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `islive` int(2) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `oriurl` varchar(255) DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `category_id` int(255) DEFAULT NULL,
  `user_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `video_ibfk_1` (`category_id`),
  KEY `video_ibfk_2` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `video_check`
-- ----------------------------
DROP TABLE IF EXISTS `video_check`;
CREATE TABLE `video_check` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `tmp_file_name` varchar(255) DEFAULT NULL,
  `tmp_dir` varchar(255) DEFAULT NULL,
  `current_chunk` varchar(255) DEFAULT NULL,
  `chunk_size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `video_check`
-- ----------------------------
BEGIN;
INSERT INTO `video_check` VALUES ('27', 'upload/那些年，我们一起追的女孩.mp4_tmp_0', null, '1', null), ('28', 'upload/Warcraft3_End.avi_tmp_0', null, '3', null);
COMMIT;

-- ----------------------------
--  Table structure for `video_state`
-- ----------------------------
DROP TABLE IF EXISTS `video_state`;
CREATE TABLE `video_state` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `level` int(255) DEFAULT NULL,
  `video_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `video_state`
-- ----------------------------
BEGIN;
INSERT INTO `video_state` VALUES ('22', '完成', '4', 'upload/那些年，我们一起追的女孩.mp4'), ('23', '完成', '4', 'upload/Warcraft3_End.avi');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
