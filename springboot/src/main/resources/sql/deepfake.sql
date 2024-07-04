/*
 Navicat Premium Data Transfer

 Source Server         : oy
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : deepfake

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 04/07/2024 00:24:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告唯一id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `mes` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通知类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, '【政策变更】隐私政策的变更', 'Deepfake检测平台（下文简称“我们”）深知隐私对您的重要性，并会尊重您的隐私。请在向Deepfake检测平台提交个人信息之前，阅读、了解官网《隐私政策》（下文简称“本政策”）。本政策适用于显示本隐私政策、或链接至本隐私政策的相关网站和产品、服务。', '2024-07-02 16:28:33', '2024-07-02 16:28:33', '');
INSERT INTO `announcement` VALUES (2, '【维护通知】系统升级维护', '为更好提供信息化服务水平，定于2024年7月2日8:50至次日09:30对Deepfakes检测系统进行停服维护。系统维护期间，将无法正常提供服务，请用户提前做好检测工作安排。维护完成后系统短期会存在运行不稳定性，在使用过程中请及时和我们反馈，由此造成的不便，敬请谅解。', '2024-07-02 16:33:15', '2024-07-02 16:33:15', '');
INSERT INTO `announcement` VALUES (3, '【网站修复通知】排除服务中断故障', '很抱歉地通知您，由于系统维护工作的需要，我们的服务将会在未来一段时间内暂时中断。在此期间，我们将尽快完成维护工作，并恢复到正常的服务状态，以给您提供更好的体验。为了确保您了解到此次服务中断的原因和影响，我们特此发出此项通知。我们深表歉意给您带来的不便，并感谢您的理解与耐心。', '2024-07-02 16:34:28', '2024-07-02 16:34:28', '');
INSERT INTO `announcement` VALUES (4, '【重要通知】本平台将新增音频检测功能', 'Deepfake平台依旧将以每周迭代的速度进行更新，继续与用户同步。本次通知将预告今年8月份即将推出的核心功能——音频检测功能', '2024-07-02 16:52:54', '2024-07-02 16:52:54', NULL);
INSERT INTO `announcement` VALUES (5, '【维护通知】系统升级', '亲爱的用户：Deepfake检测系统定于2024年7月5日上午8:50至2024年7月5日12:15进行升级维护，维护期间系统访问会受到限制，请大家安排好访问时间。', '2024-07-02 16:58:25', '2024-07-02 16:58:25', NULL);

-- ----------------------------
-- Table structure for audio
-- ----------------------------
DROP TABLE IF EXISTS `audio`;
CREATE TABLE `audio`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '音频id主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '音频名',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `size` double(100, 0) UNSIGNED NULL DEFAULT NULL COMMENT '大小',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '已检测？未检测？正在检测？',
  `localpath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '本地路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for detection
-- ----------------------------
DROP TABLE IF EXISTS `detection`;
CREATE TABLE `detection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '检测任务id主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `result` int(255) NULL DEFAULT NULL COMMENT '检测结果',
  `style` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检测类型',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检测所用时间',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检测状态',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `cardnum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡号',
  `donepath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果路径',
  `localpath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '本地路径，用于提供检测路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detection
-- ----------------------------
INSERT INTO `detection` VALUES (29, '1.jpg', '2024-07-03 23:50:53', '2024-07-03 23:50:53', NULL, '图像检测', NULL, 1, '等待检测', 'http://10.195.154.158:9090/api/image/download/1720021850713?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNzIwNzQxODUwfQ.hxxnjHp6xjpDY_3ZpnOzNIorN1cNS5zp6CYqNGiAXcY', '202407031530483156', NULL, NULL);

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片名',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `size` double(100, 0) UNSIGNED NULL DEFAULT NULL COMMENT '大小',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '已检测？未检测？正在检测？',
  `localpath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '本地路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信息唯一id',
  `style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系统？安全？检测？',
  `mes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isread` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否已读',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '系统通知', '您的账户已成功激活', '2024-06-27 11:50:48', '2024-06-27 11:50:48', '0', 1);
INSERT INTO `message` VALUES (2, '安全提醒', '请定期更改您的密码以确保账户安全', '2024-06-27 11:51:46', '2024-06-29 23:59:01', '1', 1);
INSERT INTO `message` VALUES (3, '系统通知', '您的最新图像检测已完成', '2024-06-27 12:36:23', '2024-06-27 13:02:24', '1', 1);
INSERT INTO `message` VALUES (4, '系统通知', '您的最新视频检测已完成', '2024-06-27 13:03:04', '2024-06-27 21:29:36', '1', 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `keynum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话号码',
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `cardnum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡号',
  `IsAuth` int(11) UNSIGNED ZEROFILL NULL DEFAULT 00000000000 COMMENT '权限表示字',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_key`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456789@163.com', '123456', '13421594388', '男', 66, '', '2024-04-20 11:17:40', '2024-07-03 23:00:58', '202404201670051966', 00000000001, '', '2004-10-01');
INSERT INTO `user` VALUES (2, '用户1', 'zhang@163.com', '123456', '13421594386', '男', 22, '西安交通大学', '2024-04-20 11:37:07', '2024-07-03 12:09:50', '20240420443544878', 00000000000, '', NULL);
INSERT INTO `user` VALUES (3, '用户2', 'kexu@sb.com', '123456', '13421212121', '男', 18, '', '2024-05-07 11:00:49', '2024-07-03 12:10:21', '202405072054057603', 00000000000, '', NULL);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频id主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频名',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `size` double(100, 0) UNSIGNED NULL DEFAULT NULL COMMENT '大小',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '已检测？未检测？正在检测？',
  `localpath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '本地路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
