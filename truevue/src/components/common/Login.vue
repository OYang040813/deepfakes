<template>
  <div class="login_container">
    <div class="login_form">
      <p class="login_title">Deepfake检测系统</p>
      <el-form
        :model="form"
        :rules="rules"
        ref="loginForm"
        status-icon
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="账号" prop="name">
          <el-input v-model="form.name" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="keynum">
          <el-input
            type="password"
            show-password
            v-model="form.keynum"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item class="custom-button-position">
          <el-button type="primary" size="medium" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import request from "../../utils/request";
import Cookies from "js-cookie";

export default {
  name: "Login",
  data() {
    return {
      form: {
        name: "",
        keynum: "",
      },

      rules: {
        name: [
          {required: true, message: '请输入账号', trigger: 'blur'}
        ], keynum: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    }
  },

  methods: {
    login(){
      this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          request.post('/user/login', this.form).then(res => {
            if (res.code === '200') {
              Cookies.set('user', JSON.stringify(res.data))
              this.$notify.success('登陆成功')
              this.$router.push('/FirstPage')
            } else {
              this.$notify.error(res.msg)
            }
          })
        } else {
          this.$message.error('请正确填入用户名与密码！')
        }
      });

    },
  }


}




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










