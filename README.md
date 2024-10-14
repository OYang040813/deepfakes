# Deepfakes

关于视频、图像与音频的深度伪造检测平台

## 目录

- [简介](#简介)
- [功能](#功能)
- [技术架构](#技术架构)
- [安装](#安装)
- [使用](#使用)
- [贡献](#贡献)
- [许可证](#许可证)
- [联系信息](#联系信息)

## 简介

本项目是一个用于检测深度伪造内容的平台，能够处理视频、图像和音频数据。旨在帮助用户识别和防止深度伪造技术带来的风险。

## 功能

- **视频检测**：通过深度学习模型分析视频内容，识别其中的伪造部分。
- **图像检测**：提供高精度的图像伪造检测功能，支持多种图像格式。
- **音频检测**：使用先进的音频分析技术，检测音频文件中的伪造片段。
- **报告生成**：自动生成检测报告，提供详细的分析结果和可信度评分。

## 技术架构

本项目采用前后端分离的架构，主要技术栈如下：

- 前端：Vue.js
- 后端：Spring Boot
- 深度学习模型：TensorFlow/PyTorch
- 数据库：MySQL

## 安装

### 环境依赖

- Node.js
- npm
- Maven
- Java 11+
- MySQL

### 安装步骤

```bash
# 克隆仓库
git clone https://github.com/OYang040813/deepfakes.git

# 进入项目目录
cd deepfakes

# 启动项目前端
cd truevue
npm install
npm run dev

# 启动项目后端
cd ../springboot
mvn install
mvn spring-boot:run

# 初始化数据库
在MySQL中创建一个数据库deepfake，并执行数据库初始化脚本
deepfake.sql

# 更改模型路径
因模型大小问题，未在仓库中给出，请先通过联系方式取得模型
于com/example/springboot/service/impl/DetectionServiceImpl.java文件中修改检测模型路径

# 使用根账号登陆系统
账号：admin
密码：123456
```

## 使用

1. 启动前端和后端服务。
2. 通过浏览器访问前端服务，默认地址为 `http://localhost:8080`。
3. 上传视频、图像或音频文件，等待检测结果。
4. 查看和下载检测报告。

## 贡献

欢迎各种形式的贡献！请阅读 [贡献指南](CONTRIBUTING.md) 了解如何参与项目。

1. Fork 这个仓库
2. 创建一个特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开一个 Pull Request

## 许可证

此项目基于 MIT 许可证，请参见 [LICENSE](LICENSE) 文件了解更多信息。

## 联系信息

如果你有任何问题或建议，请通过以下方式联系我们：

- 邮件: swouyangfan@163.com
- GitHub: (https://github.com/OYang040813)

