/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50710
 Source Host           : localhost
 Source Database       : mvideo

 Target Server Version : 50710
 File Encoding         : utf-8

 Date: 01/02/2017 18:44:01 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `category`
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES ('1', 'Movies', null, null), ('2', 'English', null, '1'), ('3', 'Chinese', null, '1'), ('4', 'Hindi', null, '1'), ('5', 'Sports', null, null), ('6', 'Football', null, '5'), ('7', 'Basketball', null, '5'), ('8', 'Tennis', null, '5'), ('9', 'Songs', null, null), ('10', 'News', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `has_child` int(2) DEFAULT NULL,
  `sub_time` datetime DEFAULT NULL,
  `user_id` int(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `video_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `comment`
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES ('6', 'haha', '1', '2017-01-02 15:04:17', '1', '游客1', '53'), ('7', '123123123', '1', '2017-01-02 16:57:23', '1', '游客1', '53'), ('8', 'shabi', '0', '2017-01-02 18:34:31', '1', '游客1', '53'), ('9', '313123123', '0', '2017-01-02 18:36:11', '1', '游客1', '53'), ('10', '313123123131', '1', '2017-01-02 18:36:14', '1', '游客1', '53'), ('11', '313123123131', '1', '2017-01-02 18:36:17', '1', '游客1', '53'), ('12', '313123123131', '1', '2017-01-02 18:36:19', '1', '游客1', '53'), ('13', '313123123131', '0', '2017-01-02 18:36:21', '1', '游客1', '53'), ('14', '313123123131', '1', '2017-01-02 18:36:23', '1', '游客1', '53'), ('15', '312312312', '0', '2017-01-02 18:40:35', '1', '游客1', '33'), ('16', '31312124141', '0', '2017-01-02 18:40:54', '1', '游客1', '29'), ('17', '那些年我们一起追的女孩', '0', '2017-01-02 18:41:36', '1', '游客1', '51'), ('18', '鸿沟', '0', '2017-01-02 18:42:19', '1', '游客1', '52');
COMMIT;

-- ----------------------------
--  Table structure for `comment_child`
-- ----------------------------
DROP TABLE IF EXISTS `comment_child`;
CREATE TABLE `comment_child` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `parent_id` int(255) DEFAULT NULL,
  `user_id` int(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `sub_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `comment_child`
-- ----------------------------
BEGIN;
INSERT INTO `comment_child` VALUES ('1', 'aa', '6', '1', '游客1', '2017-01-02 16:48:36'), ('2', '312', '7', '1', '游客1', '2017-01-18 18:30:53'), ('3', '321213123', '7', '1', '游客1', null), ('4', 'nishishabima', '6', '1', '游客1', null), ('5', '4123123', '11', '1', '游客1', null), ('6', '4123123', '11', '1', '游客1', null), ('7', '41231', '12', '1', '游客1', null), ('8', '31231', '10', '1', '游客1', null), ('9', '321', '11', '1', '游客1', null), ('10', '111', '14', '1', '游客1', null);
COMMIT;

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
  `login_email` varchar(255) DEFAULT NULL,
  `login_phone` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `real_name` varchar(8) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '123@qq.com', '18814092579', '21232f297a57a5a743894a0e4a801fc3', '游客1', null, null, null, null), ('8', '321@qq.com', '18814092578', 'caf1a3dfb505ffed0d024130f58c5cfa', '游客2', null, null, '2017-01-01 16:52:44', '2017-01-01 16:52:44');
COMMIT;

-- ----------------------------
--  Table structure for `video`
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `intro` longtext,
  `last_update_time` datetime DEFAULT NULL,
  `islive` int(2) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `oriurl` varchar(255) DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `real_url` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `views` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `category_id` int(255) DEFAULT NULL,
  `user_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `video_ibfk_1` (`category_id`),
  KEY `video_ibfk_2` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `video`
-- ----------------------------
BEGIN;
INSERT INTO `video` VALUES ('28', '北广传媒移动电视', '北广传媒移动电视始终坚持正确舆论导向，坚持“服务政府公共管理”、“服务市民精彩生活”的媒体定位，积极与政府“委、办、局”开展多层合作，充分发挥了党管新闻媒体喉舌功能、阵地作用。同时，又依据大多数乘客收视需求精心设计节目结构与编排方式。目前，基本形成以市民服务为主，新闻资讯、文体娱乐为辅的节目风格，为观众第一时间呈现最新的资讯、实时的路况、及时的突发事件报道、精彩赛事节目播报，全面展现“新闻性、资讯性、娱乐性、服务性”特色。在2008年抗震救灾、奥运报道、“神七”发射、全国及北京“两会”等重点宣传工作中突显出媒体的及时性和权威性，成为首都市民喜闻乐见的超级户外电视新媒体。', '2015-02-16 15:30:19', '1', 'rtmp://www.bj-mobiletv.com:8000/live/live1', null, 'thumbnails/12.jpg', 'http://localhost:8080/thumbnails/12.jpg', '审核通过', null, '0', '北广传媒移动电视', null, null), ('29', '香港电视台', 'hks香港卫视国际传媒是一家集卫星电视、网络电视、影视剧投资为一体的国际传媒集团。hks香港卫视为国家重点新闻网站，是集新闻直播、娱乐、服务等为一体的具有视听互动特色的综合性门户网站。', '2015-02-16 15:32:08', '1', 'rtmp://live.hkstv.hk.lxdns.com/live/hks', null, 'thumbnails/13.jpg', 'http://localhost:8080/thumbnails/13.jpg', '审核通过', null, '0', '香港电视台', null, null), ('30', '东莞电视台', '东莞广播电视台成立于2005年3月28日，整合了东莞人民广播电台、东莞电视台、东莞阳光网三大媒体，是中共东莞市委宣传部领导下的正处级事业单位，是南方广播影视传媒集团的成员单位。', '2015-02-16 15:34:13', '1', 'rtmp://ftv.sun0769.com/dgrtv1/mp4:b1', null, 'thumbnails/14.jpg', 'http://localhost:8080/thumbnails/14.jpg', '审核通过', null, '0', '东莞电视台', null, null), ('31', '看看新闻网', '看看新闻网（www.kankanews.com）是上海广播电视台旗下的网络新闻媒体，是依托上海广播电视台强大的内容资源和制播能力支撑，凭借传统媒体背景，结合网络科技创新技术的新媒体平台。看看新闻网由上海看看牛视网络传播有限公司运营。看看新闻网打破传统电视传播与网络传播的界限，建设以视频新闻为特色的，网民积极参与的，具有公信力、影响力的互动传播网站，构建最具品牌价值的网络新闻互动平台。', '2015-02-16 16:02:21', '1', 'rtmp://live.kksmg.com:80/live/mp4:Stream_1', null, 'thumbnails/15.jpg', 'http://localhost:8080/thumbnails/15.jpg', '审核通过', null, '0', '看看新闻网', null, null), ('32', '绍兴新闻综合', '绍兴网络电视台是绍兴广播电视总台主办的,以互联网节目传播载体的新形态广播电视播出机构。', '2015-02-16 16:22:16', '1', 'rtmp://www.scbtv.cn/live/new', null, 'thumbnails/16.jpg', 'http://localhost:8080/thumbnails/16.jpg', '审核通过', null, '0', '绍兴新闻综合', null, null), ('33', '央广购物', '央广购物（CNRmall) 系中央人民广播电台倾力打造的专业居家购物公司，是以电视购物频道为主体，辅助以广播、网络、手机app、型录等全通路电子商务平台。2009年6月，经由国家广电总局审批，获得全国电视网络落地覆盖牌照。', '2015-02-16 16:45:45', '1', 'rtmp://wx.cnrmall.com/live/flv', null, 'thumbnails/17.jpg', 'http://localhost:8080/thumbnails/17.jpg', '审核通过', null, '0', '央广购物', null, null), ('34', '亚太卫视', '亚太卫视是一家立足香港、覆盖亚洲及太平洋地区64个国家（地区）的国际新锐电视台,其节目信号通过香港亚太卫视已持有的卫星电视运营牌照、完善的卫星上行播出系统（亚太六号卫星C波段转发器）等资源与深圳的播控中心及亚太卫视网站，形成完整高效的传播平台，每天24小时滚动向亚洲及太平洋地区播出。', '2015-02-16 17:46:32', '1', 'rtmp://58.61.150.198:1935/live/Livestream', null, 'thumbnails/19.jpg', 'http://localhost:8080/thumbnails/19.jpg', '审核通过', null, '0', '亚太卫视', null, null), ('35', 'CCTV中学生', '《CCTV 中学生》频道是经国家广电总局批准，中央电视台开办的面向全国中学生的数字电视付费频道，是全世界首个以中学生教育成长服务为定位的专业电视频道。', '2015-02-16 18:02:50', '1', 'rtmp://ams.studytv.cn/livepkgr/264', null, 'thumbnails/20.jpg', 'http://localhost:8080/thumbnails/20.jpg', '审核通过', null, '0', 'CCTV中学生', null, null), ('36', '广州综合', 'Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem. Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem.', '2015-02-16 18:44:49', '1', 'rtmp://116.199.115.228/live/gztv_tv', null, 'thumbnails/22.jpg', 'http://localhost:8080/thumbnails/22.jpg', '审核通过', null, '0', '广州综合', null, null), ('37', '睛彩广州', 'Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem. Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem.', '2015-02-16 18:46:03', '1', 'rtmp://116.199.115.228/live/cmmb', null, 'thumbnails/23.jpg', 'http://localhost:8080/thumbnails/23.jpg', '审核通过', null, '0', '睛彩广州', null, null), ('38', '深圳娱乐', 'Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem. Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem.', '2015-02-16 18:48:46', '1', 'rtmp://tv.sznews.com:1935/live/live_233_mc43', null, 'thumbnails/24.jpg', 'http://localhost:8080/thumbnails/24.jpg', '审核通过', null, '0', '深圳娱乐', null, null), ('39', '南阳新闻综合', 'Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem. Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem.', '2015-02-16 18:50:33', '1', 'rtmp://61.136.113.35/tslsChannelLive/zyys888/live', null, 'thumbnails/25.jpg', 'http://localhost:8080/thumbnails/25.jpg', '审核通过', null, '0', '南阳新闻综合', null, null), ('40', '大庆综合', 'Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem. Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem.', '2015-02-16 18:52:58', '1', 'rtmp://live1.baihuwang.com:1935/live/zh', null, 'thumbnails/26.jpg', 'http://localhost:8080/thumbnails/26.jpg', '审核通过', null, '0', '大庆综合', null, null), ('41', '温州新闻综合', 'Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem. Morbi eget malesuada nunc. Nullam ac congue ipsum, non tristique orci. Sed placerat porta justo, at sollicitudin velit. Curabitur sed hendrerit justo, eu vehicula lorem.', '2015-02-16 18:58:05', '1', 'rtmp://livetv.dhtv.cn:1935/live/news', null, 'thumbnails/27.jpg', 'http://localhost:8080/thumbnails/27.jpg', '审核通过', null, '0', '温州新闻综合', null, null), ('51', '那些年，我们一起追的女孩.mp4', '那些年，我们一起追的女孩.mp4', '2017-01-01 19:15:46', '0', 'http://localhost:8080/converts/10526722c0126ef9bf4a09c2d4ea23c1.mp4', 'upload/那些年，我们一起追的女孩.mp4', 'thumbnails/10526722c0126ef9bf4a09c2d4ea23c1.jpg', 'http://localhost:8080/thumbnails/10526722c0126ef9bf4a09c2d4ea23c1.jpg', '待审核', '00:30', '0', '那些年，我们一起追的女孩', null, '1'), ('52', '中国合伙人.flv', '中国合伙人.flv', '2017-01-01 19:15:46', '0', 'http://localhost:8080/converts/49036c4803515a870d53afe3db6c1eb0.mp4', 'upload/中国合伙人.flv', 'thumbnails/49036c4803515a870d53afe3db6c1eb0.jpg', 'http://localhost:8080/thumbnails/49036c4803515a870d53afe3db6c1eb0.jpg', '待审核', '00:30', '0', '中国合伙人', null, '1'), ('53', 'ForrestGumpIMAX.mp4', 'ForrestGumpIMAX.mp4', '2017-01-01 19:15:46', '0', 'http://localhost:8080/converts/67d62ed3e3b28a124dd8d48cc156e1a2.mp4', 'upload/ForrestGumpIMAX.mp4', 'thumbnails/67d62ed3e3b28a124dd8d48cc156e1a2.jpg', 'http://localhost:8080/thumbnails/67d62ed3e3b28a124dd8d48cc156e1a2.jpg', '待审核', '00:31', '0', 'ForrestGumpIMAX', null, '1'), ('54', 'Warcraft3_End.avi', 'Warcraft3_End.avi', '2017-01-01 19:15:46', '0', 'http://localhost:8080/converts/73c52a17aff466310a787dfee92977c0.mp4', 'upload/Warcraft3_End.avi', 'thumbnails/73c52a17aff466310a787dfee92977c0.jpg', 'http://localhost:8080/thumbnails/73c52a17aff466310a787dfee92977c0.jpg', '待审核', '00:39', '0', 'Warcraft3_End', null, '1');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `video_check`
-- ----------------------------
BEGIN;
INSERT INTO `video_check` VALUES ('75', 'upload/那些年，我们一起追的女孩.mp4_tmp_0', null, '1', null), ('76', 'upload/中国合伙人.flv_tmp_0', null, '3', null), ('77', 'upload/ForrestGumpIMAX.mp4_tmp_0', null, '3', null), ('78', 'upload/Warcraft3_End.avi_tmp_0', null, '3', null);
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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `video_state`
-- ----------------------------
BEGIN;
INSERT INTO `video_state` VALUES ('74', '完成', '4', 'upload/那些年，我们一起追的女孩.mp4'), ('75', '完成', '4', 'upload/中国合伙人.flv'), ('76', '完成', '4', 'upload/ForrestGumpIMAX.mp4'), ('77', '完成', '4', 'upload/Warcraft3_End.avi');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
