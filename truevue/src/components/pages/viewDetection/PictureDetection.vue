<template>
  <div class="upload-container">
    <h1 class="title">图片上传</h1>
    <el-upload
      class="upload-demo"
      action="https://jsonplaceholder.typicode.com/posts/"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      list-type="picture-card">
      <el-button size="large" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip"><h1>只能上传jpg/png文件，且不超过500kb</h1></div>
    </el-upload>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fileList: [
        { name: 'fish.jpeg', url: 'https://img2.baidu.com/it/u=2070724841,3828365377&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=375' },
        { name: 'noodles.jpeg', url: 'https://img1.baidu.com/it/u=655258142,3279121237&fm=253&fmt=auto?w=746&h=532' }
      ]
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log('移除文件', file, fileList);
    },
    handlePreview(file) {
      console.log('预览文件', file);
    }
  }
}
</script>

<style scoped>
.upload-container {
  max-width: 800px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #eaeaea;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #e9eee8;
  text-align: center;
  position: relative;
  overflow: hidden;
}

.upload-container::before {
  content: "";
  position: absolute;
  top: -50px;
  left: -50px;
  width: 200px;
  height: 200px;
  background-color: rgba(255, 165, 0, 0.2);
  border-radius: 50%;
  z-index: 0;
}

.upload-container::after {
  content: "";
  position: absolute;
  bottom: -50px;
  right: -50px;
  width: 200px;
  height: 200px;
  background-color: rgba(30, 144, 255, 0.2);
  border-radius: 50%;
  z-index: 0;
}

.title {
  font-size: 28px;
  margin-bottom: 20px;
  color: #504747;
  z-index: 1;
  position: relative;
}

.upload-demo {
  margin-bottom: 20px;
  z-index: 1;
  position: relative;
}

.el-upload__tip {
  font-size: 14px;
  color: #999;
  z-index: 1;
  position: relative;
}

.el-button
{
  background-color : #3e87a8;
  transition: transform 0.3s;
}

.el-button:hover
{
  background-color : #097fa1;
  transform: scale(1.02);
}


h1 {
  color: #3e87a8;
  text-align: center;
  font-size: 15px; /* 调整字体大小 */
  margin-bottom: 30px; /* 增加下方的间距 */
  z-index: 1;
  position: relative;
}
</style>
