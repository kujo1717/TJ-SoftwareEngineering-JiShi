/*
 Navicat Premium Data Transfer

 Source Server         : localMySQL
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : jishi

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 30/11/2022 22:15:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` bigint(0) NOT NULL,
  `title_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `detail_text` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `summary` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hit_num` int(0) NULL DEFAULT NULL,
  `limit_capacity` tinyint(1) NULL DEFAULT NULL,
  `capacity` int(0) NULL DEFAULT NULL,
  `participant_num` int(0) NULL DEFAULT NULL,
  `repeat_interval` int(0) NULL DEFAULT NULL,
  `creator_id` bigint(0) NULL DEFAULT NULL,
  `state` tinyint(0) NULL DEFAULT 0,
  `mark` int(0) NULL DEFAULT NULL,
  `applicant_num` int(0) NULL DEFAULT NULL,
  `address_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1597567450432405506, '6 create', '6 create', '6 create', '2022-11-17 00:00:00', NULL, '2022-11-29 20:25:36', '6 create', NULL, 0, 1, 2, 1, NULL, 6, 2, NULL, 0, NULL);
INSERT INTO `activity` VALUES (1597817835638226946, '地图测试', '地图测试', '地图测试', '2022-11-17 00:00:00', NULL, '2022-11-30 13:00:32', '上海市静安区南京西路街道南京西路1204号', NULL, 0, 1, 2, 1, NULL, 6, 0, NULL, 0, NULL);

-- ----------------------------
-- Table structure for activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `activity_apply`;
CREATE TABLE `activity_apply`  (
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `apply_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_apply
-- ----------------------------

-- ----------------------------
-- Table structure for activity_invite
-- ----------------------------
DROP TABLE IF EXISTS `activity_invite`;
CREATE TABLE `activity_invite`  (
  `activity_id` bigint(0) NOT NULL,
  `invitee_id` bigint(0) NOT NULL,
  `state` tinyint(1) NULL DEFAULT NULL,
  `invite_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`, `invitee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_invite
-- ----------------------------

-- ----------------------------
-- Table structure for activity_mark
-- ----------------------------
DROP TABLE IF EXISTS `activity_mark`;
CREATE TABLE `activity_mark`  (
  `mark_id` bigint(0) NOT NULL,
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `mark` int(0) NULL DEFAULT NULL,
  `mark_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`mark_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_mark
-- ----------------------------
INSERT INTO `activity_mark` VALUES (1597568588397412354, 1597567450432405506, 6, 4, '2022-11-29 20:30:07');
INSERT INTO `activity_mark` VALUES (1597568605615030273, 1597567450432405506, 1146, 2, '2022-11-29 20:30:11');
INSERT INTO `activity_mark` VALUES (1597907267590660098, 1597906550960267266, 1146, 4, '2022-11-30 18:55:54');
INSERT INTO `activity_mark` VALUES (1597907363573112833, 1597906550960267266, 6, 3, '2022-11-30 18:56:17');

-- ----------------------------
-- Table structure for activity_participate
-- ----------------------------
DROP TABLE IF EXISTS `activity_participate`;
CREATE TABLE `activity_participate`  (
  `activity_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  PRIMARY KEY (`activity_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_participate
-- ----------------------------
INSERT INTO `activity_participate` VALUES (1597566700876726274, 1145);
INSERT INTO `activity_participate` VALUES (1597567450432405506, 6);
INSERT INTO `activity_participate` VALUES (1597567450432405506, 1146);
INSERT INTO `activity_participate` VALUES (1597817835638226946, 6);

-- ----------------------------
-- Table structure for activity_tag
-- ----------------------------
DROP TABLE IF EXISTS `activity_tag`;
CREATE TABLE `activity_tag`  (
  `activity_id` bigint(0) NOT NULL,
  `tag_id` bigint(0) NOT NULL,
  PRIMARY KEY (`activity_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_tag
-- ----------------------------

-- ----------------------------
-- Table structure for activity_work
-- ----------------------------
DROP TABLE IF EXISTS `activity_work`;
CREATE TABLE `activity_work`  (
  `activity_id` bigint(0) NOT NULL,
  `work_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`activity_id`, `work_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_work
-- ----------------------------

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` bigint(20) UNSIGNED ZEROFILL NOT NULL,
  `address_formatted` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `longitude` double NULL DEFAULT NULL,
  `latitude` double NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `township` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `street` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `classification_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`classification_title`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES ('c1', 1, '2022-11-23 19:15:36');
INSERT INTO `classification` VALUES ('c2', 1, '2022-11-23 20:05:56');
INSERT INTO `classification` VALUES ('c3', 1, '2022-11-23 20:09:57');
INSERT INTO `classification` VALUES ('默认分组', 1, '2022-11-24 22:06:15');

-- ----------------------------
-- Table structure for item_notice
-- ----------------------------
DROP TABLE IF EXISTS `item_notice`;
CREATE TABLE `item_notice`  (
  `item_notice_id` bigint(0) NOT NULL COMMENT '个人事项通知',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '所属用户ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `item_id` bigint(0) NULL DEFAULT NULL COMMENT '事项ID',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '1为已读，0为未读',
  PRIMARY KEY (`item_notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_notice
-- ----------------------------
INSERT INTO `item_notice` VALUES (1, 1, '事项开始提醒111', '您的事项要开始了', '2022-11-28 18:06:20', 1, 1);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `notice_id` bigint(0) NOT NULL,
  `activity_id` bigint(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '1为系统通知，2为活动通知\r\n',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, NULL, '您的账号被勒索了', 'All your data was backed up from your server. You need to email us at rambler+2vdlm@onionmail.org to recover your data. If you dont contact us we will reach the General Data Protection Regulation, GDPR,\r\nand notify them that you store user data in an open form that is not safe. Under the rules of the law, you face a heavy fine or arrest and your database dump will be deleted from our server forever!', '2022-11-28 11:09:22', 1);
INSERT INTO `notice` VALUES (2, 1, '活动开始提醒', '您的活动要开始了，请安排好您的时间', '2022-11-28 16:38:48', 2);

-- ----------------------------
-- Table structure for poll
-- ----------------------------
DROP TABLE IF EXISTS `poll`;
CREATE TABLE `poll`  (
  `activity_id` bigint(0) NOT NULL,
  `poll_id` bigint(0) NOT NULL,
  `topic_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deadline` datetime(0) NULL DEFAULT NULL,
  `multiple_choice` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`poll_id`, `activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of poll
-- ----------------------------

-- ----------------------------
-- Table structure for relativetask
-- ----------------------------
DROP TABLE IF EXISTS `relativetask`;
CREATE TABLE `relativetask`  (
  `task_id` bigint(0) NOT NULL,
  `relative_task_id` bigint(0) NOT NULL,
  PRIMARY KEY (`task_id`, `relative_task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relativetask
-- ----------------------------
INSERT INTO `relativetask` VALUES (1597049239446257666, 1597115538444771330);
INSERT INTO `relativetask` VALUES (1597049239446257666, 1597116493529649153);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` bigint(0) NOT NULL,
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `task_id` bigint(0) NOT NULL,
  `task_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `task_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `task_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `classification_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `priority` tinyint(0) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `is_parent` tinyint(0) NULL DEFAULT NULL,
  `is_in_dustbin` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `real_finish_time` datetime(0) NULL DEFAULT NULL,
  `tag` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1597049239446257666, '父事项', '', '0', '', 1, '2022-11-16 00:00:00', '2022-11-17 00:00:00', 1, '0', 1, NULL, NULL, '2022-11-29 10:44:33');
INSERT INTO `task` VALUES (1597792158671200258, 'string', 'string', '0', 'string', 0, NULL, NULL, 0, '0', 1, NULL, 'string', NULL);
INSERT INTO `task` VALUES (1597792607419785217, '8', NULL, '0', '默认分组', 0, NULL, NULL, 1, '0', 1, NULL, NULL, NULL);
INSERT INTO `task` VALUES (1597792808670879746, '0', '', '0', '', 0, NULL, NULL, 1, '0', 1, NULL, NULL, NULL);
INSERT INTO `task` VALUES (1597793663394557953, '6', '', '0', '', 0, '2022-11-10 00:00:00', '2022-11-11 00:00:00', 1, '0', 1, NULL, NULL, '1970-01-20 15:49:39');
INSERT INTO `task` VALUES (1597794384445829121, '7', '', '0', '', 0, '2022-11-11 00:00:00', '2022-11-12 00:00:00', 1, '2022-11-30T03:27:36.325Z', 1, NULL, NULL, '1970-01-20 15:49:39');
INSERT INTO `task` VALUES (1597794824507891713, '5', '', '0', '', 0, '2022-11-09 00:00:00', '2022-11-10 00:00:00', 1, '0', 1, NULL, NULL, '1970-01-20 15:49:39');
INSERT INTO `task` VALUES (1597795136580898817, '9', '', '0', '', 0, '2022-11-17 00:00:00', '2022-11-18 00:00:00', 1, '0', 1, NULL, NULL, '1970-01-20 15:49:39');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名字',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码,MD5加密',
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `age` int(0) NULL DEFAULT NULL,
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'http://localhost:8081/api/static/th.jpg' COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1146 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'yy', 'yy657826', '657826973@qq.com', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (111, '111N', '111', '111', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (222, '222N', '222', '222', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (1145, '1145N', '1145', '1145', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');
INSERT INTO `user` VALUES (1146, 'yy2', 'yy657826', 'm13958790203@163.com', NULL, NULL, 'http://localhost:8081/api/static/th.jpg');

-- ----------------------------
-- Table structure for user_mark
-- ----------------------------
DROP TABLE IF EXISTS `user_mark`;
CREATE TABLE `user_mark`  (
  `activity_id` bigint(0) NOT NULL,
  `marker_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `mark` int(0) NOT NULL,
  `mark_time` datetime(0) NULL DEFAULT NULL,
  `mark_id` bigint(0) NOT NULL,
  PRIMARY KEY (`mark_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_mark
-- ----------------------------

-- ----------------------------
-- Table structure for user_notice
-- ----------------------------
DROP TABLE IF EXISTS `user_notice`;
CREATE TABLE `user_notice`  (
  `user_id` bigint(0) NOT NULL,
  `notice_id` bigint(0) NOT NULL,
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '通知是否已读：1为已读，0为未读',
  PRIMARY KEY (`user_id`, `notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_notice
-- ----------------------------
INSERT INTO `user_notice` VALUES (1, 1, 0);
INSERT INTO `user_notice` VALUES (1, 2, 0);

-- ----------------------------
-- Table structure for vote_option
-- ----------------------------
DROP TABLE IF EXISTS `vote_option`;
CREATE TABLE `vote_option`  (
  `option_id` bigint(0) NOT NULL,
  `option_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `poll_id` bigint(0) NULL DEFAULT NULL,
  `vote_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`option_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote_option
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
