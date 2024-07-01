<template>
  <div class="Home">
    <el-container>
      <el-aside width="auto">
        <Aside></Aside>
      </el-aside>
      <el-container>
        <el-header>
          <Header></Header>
        </el-header>
        <common-tab></common-tab>
        <el-main>
          <!--路由的出口-->
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from '@/components/common/Aside.vue'
import Header from '@/components/common/Header.vue'
import CommonTab from '@/components/common/CommonTab.vue'
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    };
  },
  components:{
    Aside,
    Header,
    CommonTab
  }
}
</script>

<style>
.Home {
  background-image: url('https://img2.baidu.com/it/u=643742406,3544352807&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281');
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
}

.el-header {
  padding: 0 0;
}
</style>
