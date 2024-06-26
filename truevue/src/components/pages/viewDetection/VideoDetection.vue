<template>
  <div class="upload-video-container">
    <h1 class="title">
      <i class="fas fa-upload"></i> 视频上传
    </h1>
    <el-upload
      class="upload-demo"
      action="https://jsonplaceholder.typicode.com/posts/"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      list-type="text"
      accept="video/*">
      <el-button size="large" type="primary">
        <i class="fas fa-video"></i> 点击上传视频
      </el-button>
      <div slot="tip" class="el-upload__tip">
        <i class="fas fa-info-circle"></i> 只能上传mp4文件，且不超过50MB
      </div>
    </el-upload>
    <div class="video-previews">
      <div v-for="file in fileList" :key="file.name" class="video-preview">
        <video :src="file.url" controls></video>
        <p>
          <i class="fas fa-file-video"></i> {{ file.name }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fileList: [
        { name: 'sample1.mp4', url: 'https://www.w3schools.com/html/mov_bbb.mp4' },
        { name: 'sample2.mp4', url: 'https://www.w3schools.com/html/movie.mp4' }
      ]
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log('移除视频', file, fileList);
    },
    handlePreview(file) {
      console.log('预览视频', file);
    }
  }
}
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css');

.upload-video-container {
  max-width: 900px;
  margin: 50px auto;
  padding: 20px;
  border: 2px solid #ccc;
  border-radius: 12px;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.15);
  background-color: #d6e6ea;
  text-align: center;
}

.title {
  font-size: 35px;
  margin-bottom: 30px;
  color: #333;
}

.upload-demo {
  margin-bottom: 20px;
}

.el-upload__tip {
  font-size: 20px;
  color: #666;
  margin-top: 10px;
}

.el-button {
  background-color: #409EFF;
  transition: transform 0.3s;
}

.el-button:hover {
  background-color: rgb(0, 123, 255);
  transform: scale(1.1);
}

.video-previews {
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  justify-content: center;
  margin-top: 20px;
}

.video-preview {
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  width: 300px;
  text-align: center;
  transition: transform 0.3s;
}

.video-preview:hover {
  transform: scale(1.05);
}

.video-preview video {
  width: 100%;
  height: 200px;
  border-radius: 8px;
  margin-bottom: 10px;
  transition: transform 0.3s;
}

.video-preview video:hover {
  transform: scale(1.1);
}

.video-preview p {
  font-size: 14px;
  color: #333;
}
</style>
