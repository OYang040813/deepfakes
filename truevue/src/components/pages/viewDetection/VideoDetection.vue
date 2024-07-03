<template>
  <div class="upload-video-container">
    <h1 class="title">
      <i class="fas fa-upload"></i> 视频上传
    </h1>
    <el-upload
      class="upload-demo"
      :action="'http://10.195.154.158:9090/api/video/upload?token=' + this.user.token"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :data="{ userId: this.user.id }"
      :file-list="fileList"
      list-type="text"
      accept="video/*"
    >
      <el-button size="large" type="primary">
        <i class="fas fa-video"></i> 点击上传视频
      </el-button>
      <div slot="tip" class="el-upload__tip">
        <i class="fas fa-info-circle"></i> 只能上传mp4文件，且不超过50MB
      </div>
    </el-upload>
    <el-button size="large" type="primary" @click="startDetection">开始检测</el-button>
    <div class="video-previews">
      <div v-for="file in fileList" :key="file.name" class="video-preview">
        <video :src="file.url" controls></video>
        <p>
          <i class="fas fa-file-video"></i> {{ file.name }}
          <i class="fas fa-trash-alt" @click="removeFile(file)"></i>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import request from "../../../utils/request";

export default {
  data() {
    return {
      user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
      fileList: [],

      sampleFile1: {id:-1, name: 'sample1.mp4', url: 'https://www.w3schools.com/html/mov_bbb.mp4', uid: -1},
      sampleFile2: {id:-2, name: 'sample2.mp4', url: 'https://www.w3schools.com/html/movie.mp4', uid: -2},

    };
  },
  created() {
    this.load()
  },

  methods: {

    load(){
      request.get('/video/list/' + this.user.id).then(res => {
        if(res.code === '200') {
          this.fileList = res.data.map(item => {
            return {
              id: item.id,
              name: item.name,
              url: item.path,
              pid: item.pid,
            };
          });

          if (this.fileList.length === 0) {

            this.fileList.push(this.sampleFile1);
            this.fileList.push(this.sampleFile2);
          }
        } else {
          this.$notify.error(res.msg);
        }
      }).catch(err => {
        console.error("加载视频列表出错", err);
        this.$notify.error("加载视频列表出错");
      });


    },

    handleRemove(file, fileList) {

      if(file.id < 0) {
        this.$notify.error("无法移除示例视频");
      }else {
        console.log('移除文件', file, fileList);
        this.fileList = fileList.filter(item => item.uid !== file.uid);

        // 删除本地视频
        request.delete("/video/deleteFile", {
          params: { url: file.url, name: file.name }  // 将'data'改为'params'以发送查询参数
        }).then(res => {
          if (res.code === '200') {
            console.log("本地视频删除成功");
          } else {
            this.$notify.error(res.msg);
          }
        }).catch(err => {
          console.error("删除本地视频出错", err);
          this.$notify.error("删除本地视频出错");
        });

        //同步删除数据库条目
        request.delete("/video/delete/" + file.id).then(res =>{
          if(res.code === '200'){
            this.$notify.success("删除成功")
            this.load()//调用刷新函数
          }else{
            this.$notify.error(res.msg)
          }
        })
      }

      this.load()
    },

    //预览视频
    handlePreview(file) {
      console.log('预览视频', file);
    },

    //上传成功
    handleSuccess(res) {
      if(res.code === "200") {
        console.log(res.data)
        this.load()
      }else{
        this.$notify.error(res.msg)
      }
    },

    removeFile(file) {
      this.handleRemove(file,this.fileList);
    },

    startDetection() {
      // 获取fileList中的文件ID并过滤掉示例文件的ID
      const fileIds = this.fileList.map(file => file.id).filter(id => id !== -1 && id !== -2);
      const payload = {
        fileIds: fileIds,
        pid: this.user.id
      };
      if(fileIds.length === 0){
        this.$notify.warning("请勿对示例文件执行检测");
        return
      }
      request.post('/detection/createForVideo',payload).then(res => {
        if (res.code === '200') {
          this.$router.push('/ShowResult');
        } else {
          this.$notify.error(res.msg);
        }
      }).catch(err => {
        console.error("检测请求出错", err);
        this.$notify.error("检测请求出错");
      });
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
  background-color: rgba(0, 0, 0, 0.7);
  background-image: url('https://img2.baidu.com/it/u=229796480,2119532825&fm=253&fmt=auto&app=138&f=JPEG?w=1480&h=800');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  text-align: center;
  color: #836666;
  animation: fadeIn 1s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.title {
  font-size: 35px;
  margin-bottom: 30px;
  color: #110518;
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

.video-previews {
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  justify-content: center;
  margin-top: 20px;
}

.video-preview {
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

.video-preview p {
  font-size: 14px;
  color: #333;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.video-previews i.fas.fa-trash-alt {
  cursor: pointer;
  color: #ff4d4f;
  transition: color 0.3s;
}

.video-previews i.fas.fa-trash-alt:hover {
  color: #ff7875;
}
</style>
