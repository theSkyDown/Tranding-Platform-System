import {createRouter,createWebHashHistory} from 'vue-router'
import Home from '../components/pages/home.vue'
import Echart from '../components/test/echartTest.vue'

const router = createRouter({
    history:createWebHashHistory(),
    routes: [
        //主页
        {
          path:'/home',
          name:'home',
          component:Home
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