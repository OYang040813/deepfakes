<template>
  <div class="login_container">
    <div class="login_form">
      <p class="login_title">Deepfake检测系统</p>
      <el-form
        :model="form"
        :rules="rules"
        status-icon
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="账号" prop="username">
          <el-input v-model="form.username" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            type="password"
            show-password
            v-model="form.password"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item class="custom-button-position">
          <el-button type="primary" size="medium">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    return {
      form: {
        username: "",
        password: "",
      },rules:{
        username:[
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],password:[
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    };
  }
};
</script>
<style lang="less" scoped>

.login_container {
  width: 100%;
  height: 100vh;
  background-color: rgba(242, 242, 242, 1);
  background-image: url(../../assets/login.png);
  background-position: center bottom;
  background-repeat: no-repeat;
  background-size: 1380px 660px;
  border: none;
  border-radius: 0px;
  display: flex;
  justify-content: center;
  align-items: center;
  .login_form{
    width: 510px;
    margin: 0px auto;
    padding:0 55px 15px 35px;
    background-color: #fff;
    border: none;
    border-radius: 5px;
    // 添加阴影效果
    box-shadow: 0 0 25px #cac6c6;
    .login_title{
      font-family: '微软雅黑 Bold', '微软雅黑';
      font-weight: 700;
      text-decoration: none;
      color: rgb(0, 121, 254);
      font-size: 32px;
      margin-top:50px;
      margin-bottom: 30px;
      text-align: center;
    }
    .custom-button-position {
      /* 你可以通过调整这些值来设置按钮的位置 */
      margin-top: 20px;
      margin-left: 120px;
    }
  }
}
</style>










