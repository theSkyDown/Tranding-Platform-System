import {createRouter,createWebHashHistory} from 'vue-router'
import Home from '../components/pages/home.vue'
import Echart from '../components/test/echartTest.vue'
import userAdmin from '../components/pages/user_admin.vue'
import roleAdmin from '../components/pages/role_admin.vue'
import moduleAdmin from '../components/pages/module_admin.vue'
import permissionAdmin from '../components/pages/permission_admin.vue'

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
        //角色管理页面
        {
          path:"/roleAdmin",
          name:"roleAdmin",
          component:roleAdmin,
        },
        //资源管理页面
        {
          path:"/moduleAdmin",
          name:"moduleAdmin",
          component:moduleAdmin
        },
        //权限管理页面
        {
          path:"/permissionAdmin",
          name:"permissionAdmin",
          component:permissionAdmin
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