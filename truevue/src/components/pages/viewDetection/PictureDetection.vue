<template>
  <div class="upload-container">
    <h1 class="title">图片上传</h1>
    <el-upload
      class="upload-demo"
      :action="'http://localhost:9090/api/image/upload?token=' + this.user.token"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :data="{ userId: this.user.id }"
      :file-list="fileList"
      list-type="picture-card">
      <el-button size="large" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip"><h1>只能上传jpg/png文件，且不超过500kb</h1></div>
    </el-upload>
    <el-button size="large" type="primary" @click="startDetection">开始检测</el-button>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="" />
    </el-dialog>
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

      params:{
        pageNum:1,
        pageSize:7,
        pid:'',
      },

      dialogImageUrl: '',
      dialogVisible: false
    };
  },

  created() {
    this.load();
  },

  methods: {

    load(){

        this.fileList = [];
        request.get('/image/list/' + this.user.id).then(res => {
          if(res.code === '200'){
            this.fileList = res.data.map(item => {
              return {
                id: item.id,
                name: item.name,
                url: item.path,
                pid: item.pid,
              };
            });

          if(this.fileList.length === 0){
            // 添加示例文件
            const sample1 = { id: -1, name: 'fish.jpeg', url: 'https://img2.baidu.com/it/u=2070724841,3828365377&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=375', pid: -1};
            const sample2 = { id: -2, name: 'noodles.jpeg', url: 'https://img1.baidu.com/it/u=655258142,3279121237&fm=253&fmt=auto?w=746&h=532', pid: -2};

            this.fileList.push(sample1);
            this.fileList.push(sample2);
          }

        } else {
          this.$notify.error(res.msg);
        }
      }).catch(err => {
        console.error("加载视频列表出错", err);
        this.$notify.error("加载视频列表出错");
      });

      console.log(this.fileList)
    },

    // 移除图片
    handleRemove(file, fileList) {

      if(file.id < 0){
        this.$notify.error("无法移除示例图片");
      }else{
        console.log('移除文件', file, fileList);
        this.fileList = fileList.filter(item => item.pid !== file.pid);

        // 删除本地图片
        request.delete("/image/deleteFile", {
          params: { url: file.url, name: file.name }  // 将'data'改为'params'以发送查询参数
        }).then(res => {
          if (res.code === '200') {
            console.log("本地图片删除成功");
          } else {
            this.$notify.error(res.msg);
          }
        }).catch(err => {
          console.error("删除本地图片出错", err);
          this.$notify.error("删除本地图片出错");
        });

        //同步删除数据库条目
        request.delete("/image/delete/" + file.id).then(res =>{
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
    // 预览图片
    handlePreview(file) {
      console.log('预览文件',file.name,file.url);
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },

    //上传成功
    handleSuccess(res) {
      if(res.code === "200") {
        console.log(res.data)
        this.load();
      }else{
        this.$notify.error(res.msg)
      }
    },
    // 跳转到/ShowResult路由
    startDetection() {
      const fileIds = this.fileList.map(file => file.pid);
      const payload = {
        fileIds: fileIds,
        pid: this.user.id
      };
      request.post('/detection/createForImage/', payload).then(res => {
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
  animation: fadeIn 1s ease-in-out;
}

@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }

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

.el-button {
  background-color : #3e87a8;
  transition: transform 0.3s;
}

.el-button:hover {
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
