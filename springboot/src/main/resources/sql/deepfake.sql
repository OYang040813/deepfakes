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

 Date: 02/07/2024 16:47:16
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
  `mes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通知类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, '用户协议与隐私政策变更', '略', '2024-07-02 16:28:33', '2024-07-02 16:28:33', '【政策变更】');
INSERT INTO `announcement` VALUES (2, '预计修复时间', '略', '2024-07-02 16:33:15', '2024-07-02 16:33:15', '【检测服务异常中断】');
INSERT INTO `announcement` VALUES (3, '检测模型恢复正常工作', '略', '2024-07-02 16:34:28', '2024-07-02 16:34:28', '【修复完成】');

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
  `result` double(255, 0) NULL DEFAULT NULL COMMENT '检测结果',
  `style` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检测类型',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检测所用时间',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '检测状态',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `cardnum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡号',
  `donepath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结果路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456789@163.com', '123456', '13421594388', '男', 66, '', '2024-04-20 11:17:40', '2024-07-02 16:25:53', '202404201670051966', 00000000001, 'http://localhost:9090/api/user/download/1719821291200?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNzIwMTgxMjkxfQ.ht77yAPYE6xsowmXSRzTb7hW8PkFjWVqjqHEVx8YVIQ', '2004-10-01');
INSERT INTO `user` VALUES (2, '用户1', 'zhang@163.com', '123456', '13421594386', '男', 22, '西安交通大学', '2024-04-20 11:37:07', '2024-06-29 17:52:48', '20240420443544878', 00000000000, NULL, NULL);
INSERT INTO `user` VALUES (3, '用户2', 'kexu@sb.com', 'ae54a5abf73761b9bd320607721f3509', '13421212121', '男', 18, '', '2024-05-07 11:00:49', '2024-05-08 00:44:54', '202405072054057603', 00000000000, NULL, NULL);
INSERT INTO `user` VALUES (4, '用户3', 'hongzihao@163.com', '123123', '13421334433', '男', 14, '华南理工大学', '2024-05-08 00:45:34', '2024-05-08 16:49:51', '20240508800959075', 00000000000, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
