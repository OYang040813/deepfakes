<template>
  <div class="register_container">
    <div class="register_form">
      <p class="register_title">注册</p>
      <el-form
        :model="form"
        :rules="rules"
        ref="registerForm"
        status-icon
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="name">
          <el-input v-model="form.name" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="keynum">
          <el-input
            type="password"
            show-password
            v-model="form.keynum"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmKeynum">
          <el-input
            type="password"
            show-password
            v-model="form.confirmKeynum"
            placeholder="请确认密码"
          ></el-input>
        </el-form-item>
        <el-form-item class="custom-button-position">
          <el-button type="primary" size="medium" @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "Register",
  data() {
    return {
      form: {
        phone: "",
        name: "",
        keynum: "",
        confirmKeynum: ""
      },
      rules: {
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^[1][3,4,5,7,8][0-9]{9}$/, message: '手机号格式不正确', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        keynum: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirmKeynum: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if (value !== this.form.keynum) {
                callback(new Error('两次输入密码不一致'));
              } else {
                callback();
              }
            }, trigger: 'blur'
          }
        ]
      }
    };
  },
  methods: {
    register() {
      this.$refs["registerForm"].validate((valid) => {
        if (valid) {
          request.post('/user/save', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('注册成功');
              this.$router.push('/Login');
            } else {
              this.$notify.error(res.msg);
            }
          });
        } else {
          this.$message.error('请正确填入表单内容！');
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.register_container {
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
  .register_form {
    width: 510px;
    margin: 0px auto;
    padding: 0 55px 15px 35px;
    background-color: #fff;
    border: none;
    border-radius: 5px;
    box-shadow: 0 0 25px #cac6c6;
    .register_title {
      font-family: '微软雅黑 Bold', '微软雅黑';
      font-weight: 700;
      text-decoration: none;
      color: rgb(0, 121, 254);
      font-size: 32px;
      margin-top: 50px;
      margin-bottom: 30px;
      text-align: center;
    }
    .custom-button-position {
      margin-top: 20px;
      margin-left: 120px;
    }
  }
}
</style>
