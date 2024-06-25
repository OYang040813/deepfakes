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

 Date: 26/06/2024 00:59:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dectection
-- ----------------------------
DROP TABLE IF EXISTS `dectection`;
CREATE TABLE `dectection`  (
  `id` int(11) NOT NULL COMMENT '检测任务id主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `result` double(255, 0) NULL DEFAULT NULL COMMENT '检测结果',
  `style` int(11) NULL DEFAULT NULL COMMENT '检测类型',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检测所用时间',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

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
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `cardnum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡号',
  `IsAuth` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '权限表示字',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_key`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '', '123456', '', '男', 66, '', '2024-04-20 11:17:40', '2024-04-20 11:17:40', '202404201670051966', 00000000001);
INSERT INTO `user` VALUES (2, '用户1', 'zhang@163.com', '123456', '13421594386', '男', 22, '西安交通大学', '2024-04-20 11:37:07', '2024-04-20 11:37:07', '20240420443544878', 00000000000);
INSERT INTO `user` VALUES (3, '用户2', 'kexu@sb.com', 'ae54a5abf73761b9bd320607721f3509', '13421212121', '男', 18, '', '2024-05-07 11:00:49', '2024-05-08 00:44:54', '202405072054057603', 00000000000);
INSERT INTO `user` VALUES (4, '用户3', 'hongzihao@163.com', '123123', '13421334433', '男', 14, '华南理工大学', '2024-05-08 00:45:34', '2024-05-08 16:49:51', '20240508800959075', 00000000000);

-- ----------------------------
-- Table structure for vidio
-- ----------------------------
DROP TABLE IF EXISTS `vidio`;
CREATE TABLE `vidio`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频id主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频名',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `size` double(100, 0) UNSIGNED NULL DEFAULT NULL COMMENT '大小',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '已检测？未检测？正在检测？',
  PRIMARY KEY (`id`, `status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
