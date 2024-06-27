<template>
  <div>
    <el-menu
      default-active="1-4-1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      :collapse="isCollapse"
      background-color="#082852"
      text-color="#fff"
      active-text-color="#ffd04b">
      <h3>
        {{isCollapse?'':'deepfake检测系统'}}
      </h3>
      <!-- 单纯的一级菜单-->
      <el-menu-item v-for="item in noChildren"
        :key="item.name"
        :index="item.name"
                    @click = "clickMenu(item)"
      >
        <i :class="`el-icon-${item.icon}`"></i>
        <span slot="title">{{item.label}}</span>
      </el-menu-item>
      <!--有二级菜单的菜单-->
      <el-submenu v-for="item in hasChildren" :key="item.name" :index="item.name">
        <template slot="title">
          <i :class="`el-icon-${item.icon}`"></i>
          <span slot="title">{{item.label}}</span>
        </template>
        <el-menu-item-group >
          <el-menu-item  v-for="subItem in item.children"
                        :key="subItem.name"
                         @click = "clickMenu(subItem)"
                         :index="subItem.name" >{{subItem.label}}
                        </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>

</template>
<style lang = "less" scoped>

  .el-menu{
    height: 200vh;
    border-right: none;
    h3{
      color : #ffffff;
      text-align: center;
      line-height: 48px;
      font-size: 22px;
      font-weight: 400;
      padding: 0 20px;
    }


  }
</style>

<script>
export default {
  name: 'Aside',
  data: function () {
    return {
      //isCollapse: false,
      menuData: [
        {
          path: '/',
          name: 'main',
          label: '首页',
          icon: 's-home',
          url: 'Home/Home'
        },
        {
          label: '可视化检测',
          icon: 'view',
          name: 'viewDetection',
          children: [
            {
              path: '/PictureDetection',
              name: 'PictureDetection',
              label: '图片检测',
              icon: 'picture',
              url: 'viewDetection/PictureDetection'
            },
            {
              path: '/VideoDetection',
              name: 'VideoDetection',
              label: '视频检测',
              icon: 'camera',
              url: 'viewDetection/VideoDetection'
            }
          ]
        },
        {
          label: '音频检测',
          icon: 'microphone',
          name: 'soundDetection',
          children: [
            {
              path: '/IsRealMan',
              name: 'IsRealMan',
              label: '判断是否为真人',
              icon: 'setting',
              url: 'soundDetection/IsRealMan'
            },
            {
              path: '/IsSame',
              name: 'IsSame',
              label: '判断是否为同一人',
              icon: 'setting',
              url: 'soundDetection/IsSame'
            }
          ]
        },
        {
          label: '检测历史',
          icon: 'timer',
          name: 'DetectionHistory',
          path: '/DetectionHistory',
          url: 'DetectionHistory/DetectionHistory'
        },
        {
          label: '个人中心',
          icon: 'user',
          name: 'personalCenter',
          children:[
            {
              path: '/Myinfo',
              name: 'Myinfo',
              label: '个人信息',
              icon: 'setting',
              url: 'personalCenter/Myinfo'
            },
            {
              path: '/Mymessage',
              name: 'Mymessage',
              label: '个人消息',
              icon: 'setting',
              url: 'personalCenter/Mymessage'
            }
          ]
        },
        {
          path: '/Helper',
          name: 'Helper',
          label: '使用帮助',
          icon: 'help',
          url: 'Helper/Helper'
        }
        ,
        {
          label: '管理员',
          icon: 's-custom',
          name: 'AdminPage',
          path: '/AdminPage',
          url: 'AdminPage/AdminPage'
        }
      ]
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    clickMenu(item) {
      //console.log(item);
// $route 当前路由 $router 路由实例
      if(this.$route.path !== item.path && !(this.$route.path === '/FirstPage' &&
        item.path ==='/')){
        this.$router.push(item.path)
      }
      //触发面包屑数据的更新
      this.$store.commit('selectMenu',item)

    }
    },

   computed: {
// 获取只有一级菜单的数据
    noChildren() {
      return this.menuData.filter(item => !item.children)
    }
// 获取只有二级菜单的数据
    , hasChildren() {
      return this.menuData.filter(item => item.children)
    },isCollapse() {
      return this.$store.state.tab.isCollapse
    }
  }
}
</script>
