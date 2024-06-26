<template>
  <div class="upload-audio-container">
    <h1 class="title">
      <i class="fas fa-upload"></i> 音频比对
    </h1>
    <el-upload
      class="upload-demo"
      action="https://jsonplaceholder.typicode.com/posts/"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList1"
      list-type="text"
      accept="audio/*"
      :show-file-list="false">
      <el-button size="large" type="primary">
        <i class="fas fa-microphone"></i> 上传音频1
      </el-button>
      <div slot="tip" class="el-upload__tip">
        <i class="fas fa-info-circle"></i> 只能上传mp3文件，且不超过10MB
      </div>
    </el-upload>
    <div class="audio-previews">
      <div v-for="file in fileList1" :key="file.name" class="audio-preview">
        <audio :src="file.url" controls></audio>
        <p>
          <i class="fas fa-file-audio"></i> {{ file.name }}
          <i class="fas fa-trash-alt" @click="removeFile1(file)"></i>
        </p>
      </div>
    </div>

    <el-upload
      class="upload-demo"
      action="https://jsonplaceholder.typicode.com/posts/"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList2"
      list-type="text"
      accept="audio/*"
      :show-file-list="false">
      <el-button size="large" type="primary">
        <i class="fas fa-microphone"></i> 上传音频2
      </el-button>
      <div slot="tip" class="el-upload__tip">
        <i class="fas fa-info-circle"></i> 只能上传mp3文件，且不超过10MB
      </div>
    </el-upload>
    <div class="audio-previews">
      <div v-for="file in fileList2" :key="file.name" class="audio-preview">
        <audio :src="file.url" controls></audio>
        <p>
          <i class="fas fa-file-audio"></i> {{ file.name }}
          <i class="fas fa-trash-alt" @click="removeFile2(file)"></i>
        </p>
      </div>
    </div>

    <el-button size="large" type="primary" @click="compareAudio">
      <i class="fas fa-check"></i> 比对音频
    </el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fileList1: [],
      fileList2: []
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log('移除音频', file, fileList);
    },
    handlePreview(file) {
      console.log('预览音频', file);
    },
    removeFile1(file) {
      this.fileList1 = this.fileList1.filter(f => f !== file);
    },
    removeFile2(file) {
      this.fileList2 = this.fileList2.filter(f => f !== file);
    },
    compareAudio() {
      console.log('比对音频', this.fileList1, this.fileList2);
      // 在这里添加音频比对逻辑
    }
  }
}
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css');

.upload-audio-container {
  max-width: 900px;
  margin: 50px auto;
  padding: 20px;
  border: 2px solid #ccc;
  border-radius: 12px;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.15);
  background-image: url('https://img1.baidu.com/it/u=1243550238,1440820964&fm=253&fmt=auto&app=120&f=JPEG?w=306&h=172');
  background-color: rgba(0, 0, 0, 0.3);
  background-size: cover;
  background-position: center;
  text-align: center;
  color: #fff;
}

.title {
  font-size: 35px;
  margin-bottom: 30px;
  color: #ffffff;
}

.upload-demo {
  margin-bottom: 20px;
}

.el-upload__tip {
  font-size: 20px;
  color: #fff;
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

.audio-previews {
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  justify-content: center;
  margin-top: 20px;
}

.audio-preview {
  background: rgba(255, 255, 255, 0.8);
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  width: 300px;
  text-align: center;
  transition: transform 0.3s;
  color: #333;
  position: relative;
}

.audio-preview:hover {
  transform: scale(1.05);
}

.audio-preview audio {
  width: 100%;
  height: 50px;
  border-radius: 8px;
  margin-bottom: 10px;
  transition: transform 0.3s;
}

.audio-preview p {
  font-size: 14px;
  color: #333;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.audio-preview i.fas.fa-trash-alt {
  cursor: pointer;
  color: #ff4d4f;
  transition: color 0.3s;
}

.audio-preview i.fas.fa-trash-alt:hover {
  color: #ff7875;
}
</style>
