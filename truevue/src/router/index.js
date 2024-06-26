import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/common/Home.vue'
// 导入 自定义的组件
import PictureDetection from '@/components/pages/viewDetection/PictureDetection.vue'
import VideoDetection from '@/components//pages/viewDetection/VideoDetection.vue'
import IsRealMan from '@/components//pages/soundDetection/IsRealMan.vue'
import IsSame from '@/components//pages/soundDetection/IsSame.vue'
import FirstPage from "../components/pages/FirstPage.vue";
import Login from "../components/common/Login.vue";
import Detectionhistory from "../components/pages/Detectionhistory.vue";
import Myinfo from "@/components/pages/personalCenter/Myinfo.vue";
import Mymessage from "@/components/pages/personalCenter/Mymessage.vue";
import Helper from "../components/pages/Helper.vue";
import process from "shelljs";
import Cookies from "js-cookie";

Vue.use(Router)

const routes = [
    {
      path: '/',
      name: 'Home',
      component: Home,
      redirect:'FirstPage',
      children: [
        {
          path: '/FirstPage',
          name: 'FirstPage',
          component: FirstPage
        },
        {
          path: '/PictureDetection',
          name: 'PictureDetection',
          component: PictureDetection
        },
        {
          path: '/VideoDetection',
          name: 'VideoDetection',
          component: VideoDetection
        },
        {
          path: '/IsRealMan',
          name: 'IsRealMan',
          component: IsRealMan
        },
        {
          path: '/IsSame',
          name: 'IsSame',
          component: IsSame
        },
        {
          path: '/Detectionhistory',
          name: 'Detectionhistory',
          component: Detectionhistory
        },
        {
          path: '/Myinfo',
          name: 'Myinfo',
          component: Myinfo
        },
        {
          path: '/Mymessage',
          name: 'Mymessage',
          component: Mymessage
        },
        {
          path: '/Helper',
          name: 'Helper',
          component: Helper
        }
      ]
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login,
    },

  // ==== 404界面 ===
  { path:'*', name: '404', component: () => import('../404.vue')}
]

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next) =>{

  if(to.path === "/Login"){
    next()
  }else{
    let user = Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}

    if(user.isAuth === 1){
      next()
    }else{
      next("/Login")
    }
  }

})

export default router
