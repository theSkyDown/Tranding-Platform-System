import {createRouter,createWebHashHistory} from 'vue-router'
import Home from '../components/pages/home.vue'
import Echart from '../components/test/echartTest.vue'
import userAdmin from '../components/pages/user_admin.vue'

const router = createRouter({
    history:createWebHashHistory(),
    routes: [
        //主页
        {
          path:'/home',
          name:'home',
          component:Home
        },
        //用户管理页面
        {
          path:'/userAdmin',
          name:'userAdmin',
          component:userAdmin
        },
        //echart测试
        {
          path:'/echartTest',
          name:'echart',
          component: Echart
        },
      ]
})

export default router