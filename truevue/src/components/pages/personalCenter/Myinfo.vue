<template>
  <div class="profile-container">
    <div class="avatar-section">
      <div class="avatar-wrapper">
        <img :src="avatar" />
      </div>
      <el-upload
        class="avatar-uploader"
        :action="'http://localhost:9090/api/user/upload?token=' + this.user.token"
        :show-file-list="false"
        :on-success="handleSuccess"
        :data="{ userId: this.user.id }"
      >
        <el-button size="small" type="primary">修改头像</el-button>
      </el-upload>
    </div>
    <div class="info-section">
      <h2>个人信息</h2>
      <div class="info-item">
        <label>账户昵称：</label>
        <input v-model="user.name" type="text">
      </div>
      <div class="info-item">
        <label>性别：</label>
        <select v-model="user.sex">
          <option value="male">男</option>
          <option value="female">女</option>
          <option value="other">其他</option>
        </select>
      </div>
      <div class="info-item">
        <label>创建日期：</label>
        <input v-model="user.createtime" type="date">
      </div>
      <div class="info-item">
        <label>电话：</label>
        <input v-model="user.phone" type="tel">
      </div>
      <div class="info-item">
        <label>邮箱：</label>
        <input v-model="user.email" type="email">
      </div>
      <button class="save-btn">保存修改</button>
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
      form: '',
      avatar: require('@/assets/second.png') // default avatar
    }
  },
  created() {
    this.load();
  },
  methods: {

    load(){
      request.get("/user/" + this.user.id).then(res =>{
        this.form = res.data;
        console.log(this.form)
      })
      if(this.form.cover == null){
        this.avatar = require('@/assets/second.png');
      }
      this.avatar = require("../../../../../CoverFiles/1719675808113_1.jpg");
    },

    handleSuccess(res) {
      if(res.code === "200") {
        console.log(res.data)
        this.Path = res.data
        this.load()
      }else{
        this.$notify.error(res.msg)
      }
    },

    handleAvatarChange(file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.avatar = e.target.result;
      };
      reader.readAsDataURL(file.raw);
    }
  }
}
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css');

.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f0f2f5;
  background-image: url("https://img1.baidu.com/it/u=2037523396,980968316&fm=253&fmt=auto&app=138&f=JPEG?w=667&h=500");
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  animation: fadeIn 1s ease-in-out;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

.avatar-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.avatar-wrapper {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  overflow: hidden;
  margin-bottom: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s;
}

.avatar-wrapper img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.avatar-uploader {
  text-align: center;
}

.info-section {
  width: 100%;
  background-color: rgba(255,255,255,0.6);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.info-item {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.info-item label {
  flex-basis: 100px;
  font-weight: bold;
}

.info-item input,
.info-item select {
  flex: 1;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

.info-item input:focus,
.info-item select:focus {
  outline: none;
  border-color: #4CAF50;
  box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
}

.save-btn {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #2196F3;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
  font-size: 16px;
}

.save-btn:hover {
  background-color: #1976D2;
  transform: scale(1.05);
}

h2 {
  color: #333;
  margin-bottom: 20px;
  font-size: 24px;
}

@media (max-width: 768px) {
  .profile-container {
    flex-direction: column;
  }

  .avatar-section {
    margin-bottom: 20px;
  }

  .info-item label {
    display: block;
    margin-bottom: 5px;
  }

  .info-item input,
  .info-item select {
    width: 100%;
  }
}
</style>
