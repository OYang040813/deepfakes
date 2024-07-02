<template>
  <div class="header-container">
  <div class="l-context">
    <el-button  icon="el-icon-menu"
                size = "medium"
                style = "margin-left:20px"
                @click = "handleMenu"

    ></el-button>

    <el-breadcrumb separator="/" style = "padding-left:20px">
      <el-breadcrumb-item v-for ="item in tags" :key = "item.path" :to="{path: item.path}">{{item.label}}</el-breadcrumb-item>

    </el-breadcrumb>
  </div>
    <div class="r-context">
      <el-dropdown>
  <span class="el-dropdown-link">
    <img class = "user_img" :src ='avatar'/>
  </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><div @click="message"><i class="el-icon-s-tools"></i>个人信息</div></el-dropdown-item>
          <el-dropdown-item><div @click="out"><i class="el-icon-switch-button"></i>退出账号</div></el-dropdown-item>

        </el-dropdown-menu>
      </el-dropdown>

  </div>
  </div>
</template>
<script>

import { mapState } from 'vuex'
import Cookies from "js-cookie";
import request from "../../utils/request";
export default {
  name: 'Header',
  data () {
    return {

      avatar: '@/assets/wife.png',
      user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},

    }
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      request.get("/user/" + this.user.id).then(res =>{
        this.user = res.data;
        // console.log(this.form)
        if(this.user.cover == null){
          this.avatar = require('@/assets/second.png'); // default avatar
        }
        this.avatar = this.user.cover;
      })
    },

    out(){
      Cookies.remove('user')
      this.$router.push('/Login')
      this.$notify.success('当前账号已退出')
    },

    handleMenu(){
      console.log()
      // 对菜单进行展开折叠
      this.$store.commit('collapseMenu')
    },

    message(){
      this.$router.push('/Myinfo')
      this.$notify.success('已跳转至个人中心')
      this.load()
    },

  },computed:{
    ...mapState({
      tags: state => state.tab.tabsList
    })

  }

}
</script>

<style lang="less" scoped>
.header-container {
  width: 100%;
  height: 60px;
  background-color: #082852;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

  .r-context{
    padding-right: 20px;
    .user_img{
      width:50px;
      height:50px;
      border-radius:50%;
    }
  }
  .l-context{
    display: flex;
    align-items: center;
    /deep/.el-breadcrumb__item{
      .el-breadcrumb__inner{
        font-weight: normal;
        &.is-link{
          color: #bbbbcc;
        }
      }
      &:last-child{
        .el-breadcrumb__inner{
          color:#d8dce4;
        }
      }
    }
  }
</style>

